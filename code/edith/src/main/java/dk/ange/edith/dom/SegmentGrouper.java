package dk.ange.edith.dom;

import java.util.ArrayDeque;
import java.util.Deque;

import com.google.common.collect.ImmutableTable;

import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;

/**
 * A class that will group the list of segments read out of a {@link EdifactSegmentReader}
 */
public class SegmentGrouper {

    private final ImmutableTable<String, Integer, Transition> stateTransitions;

    /**
     * @param stateTransitions
     */
    public SegmentGrouper(final ImmutableTable<String, Integer, Transition> stateTransitions) {
        this.stateTransitions = stateTransitions;
    }

    /**
     * Will read a BAPLIE message from the event reader.
     * <p>
     * Expects the next event to be a UNH with the correct identifier. If the next event is wrong an error will
     * reported, the method will return an empty Group and no event will be consumed. If the BAPLIE reading start it
     * will continue until the message is ended with an UNT or the event reader has no more events.
     *
     * @param segmentReader
     * @return the entire BAPLIE as a Group
     */
    public Group group(final EdifactSegmentReader segmentReader) {
        final Group.Builder group0Builder = new Group.Builder();

        // UNH
        final Segment unh = segmentReader.peek();
        if (!acceptsUnh(unh)) {
            throw new RuntimeException("Bad UNH segment");
        }
        segmentReader.next(); // pop UNH that was peeked
        group0Builder.add(unh);

        // Rest of message
        final Deque<Group.Builder> stack = new ArrayDeque<>();
        stack.push(group0Builder);
        int state = 0;
        while (segmentReader.hasNext()) {
            final Segment segment = segmentReader.next();
            final String tag = segment.getTag();
            if (tag.equals("UNT")) {
                group0Builder.add(segment);
                break;
            }
            if (stateTransitions.contains(tag, state)) {
                final Transition transition = stateTransitions.get(tag, state);
                for (int i = 0; i < transition.getPop(); ++i) {
                    stack.pop();
                }
                if (transition.createNewGroup()) {
                    final Group.Builder newGroupBuilder = new Group.Builder();
                    stack.getFirst().add(transition.groupNumber(), newGroupBuilder);
                    stack.push(newGroupBuilder);
                }
                state = transition.getState();
                stack.getFirst().add(segment, transition.getOccurrence());
            } else {
                stack.getFirst().add(segment);
            }
        }

        assert !stack.isEmpty();
        assert stack.getLast() == group0Builder;
        return group0Builder.build();
    }

    // Make method public?
    private boolean acceptsUnh(final Segment unh) {
        // Expand to check identifier
        // Add and use identifiersAccepted() method?
        return unh.getTag().equals("UNH");
    }

}
