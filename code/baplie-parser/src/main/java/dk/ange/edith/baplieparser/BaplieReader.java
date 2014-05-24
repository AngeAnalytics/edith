package dk.ange.edith.baplieparser;

import java.util.ArrayDeque;
import java.util.Deque;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;
import com.google.common.collect.Table;

import dk.ange.edith.data.Group;
import dk.ange.edith.data.Segment;
import dk.ange.edith.stream.EdifactEventReader;

/**
 * Example of a BAPLIE reader
 */
public class BaplieReader {

    private static final Table<String, Integer, Transition> STATE_TRANSITIONS = createStateTransitions();

    private static Table<String, Integer, Transition> createStateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        builder.put("TDT", 0, Transition.to(1));
        builder.put("TDT", -10, Transition.to(1).pop(1));
        builder.put("DTM", 1, Transition.to(-10));
        builder.put("LOC", -10, Transition.to(2).pop(1));
        builder.put("LOC", -20, Transition.to(-20).occurrence(2));
        builder.put("LOC", -21, Transition.to(2).pop(1));
        builder.put("LOC", 3, Transition.to(2).pop(2));
        builder.put("LOC", 4, Transition.to(2).pop(2));
        builder.put("MEA", 2, Transition.to(-20));
        builder.put("RFF", -20, Transition.to(-21));
        builder.put("EQD", -21, Transition.to(3));
        builder.put("EQD", 3, Transition.to(3).pop(1));
        builder.put("DGS", -21, Transition.to(4));
        builder.put("DGS", 3, Transition.to(4).pop(1));
        builder.put("DGS", 4, Transition.to(4).pop(1));
        return builder.build();
    }

    /**
     * Will read a BAPLIE message from the event reader.
     * <p>
     * Expects the next event to be a UNH with the correct identifier. If the next event is wrong an error will
     * reported, the method will return an empty Group and no event will be consumed. If the BAPLIE reading start it
     * will continue until the message is ended with an UNT or the event reader has no more events.
     *
     * @param eventReader
     * @return the entire BAPLIE as a Group
     */
    public Group read(final EdifactEventReader eventReader) {
        final Group.Builder group0Builder = new Group.Builder();

        // UNH
        final Segment unh = eventReader.peek();
        if (!acceptsUnh(unh)) {
            eventReader.report("Bad UNH segment");
            return group0Builder.build();
        }
        eventReader.next(); // pop UNH that was peeked
        group0Builder.add(unh);

        // Rest of message
        final Deque<Group.Builder> stack = new ArrayDeque<>();
        stack.push(group0Builder);
        int state = 0;
        while (eventReader.hasNext()) {
            final Segment segment = eventReader.next();
            final String tag = segment.getTag();
            if (tag.equals("UNT")) {
                group0Builder.add(segment);
                break;
            }
            if (STATE_TRANSITIONS.contains(tag, state)) {
                final Transition transition = STATE_TRANSITIONS.get(tag, state);
                for (int i = 0; i < transition.pop; ++i) {
                    stack.pop();
                }
                if (transition.createNewGroup()) {
                    final Group.Builder newGroupBuilder = new Group.Builder();
                    stack.getFirst().add(transition.groupNumber(), newGroupBuilder);
                    stack.push(newGroupBuilder);
                }
                state = transition.state;
                stack.getFirst().add(segment, transition.occurrence);
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

    private static class Transition {

        final int state;

        final int pop;

        final int occurrence;

        static Transition to(final int state) {
            return new Transition(state, 0, 1);
        }

        private Transition(final int state, final int pop, final int occurrence) {
            this.state = state;
            this.occurrence = occurrence;
            this.pop = pop;
        }

        Transition pop(final int newPop) {
            return new Transition(state, newPop, occurrence);
        }

        Transition occurrence(final int newOccurrence) {
            return new Transition(state, pop, newOccurrence);
        }

        boolean createNewGroup() {
            return state > 0;
        }

        int groupNumber() {
            return state;
        }

    }

}
