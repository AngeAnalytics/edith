package dk.ange.edith.baplie;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import dk.ange.edith.data.Group;
import dk.ange.edith.data.GroupBuilder;
import dk.ange.edith.data.Segment;
import dk.ange.edith.stream.EdifactEventReader;

/**
 * Example of a BAPLIE reader
 */
public class BaplieReader {

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
        final GroupBuilder builder = new GroupBuilder();
        // UNH
        final Segment unh = eventReader.peek();
        if (!acceptsUnh(unh)) {
            eventReader.report("Bad UNH segment");
            return builder.build();
        }
        eventReader.next();
        builder.add(unh);
        final int state = 0;
        final Table<Integer, String, Integer> stateTransitions = new ImmutableTable.Builder<Integer, String, Integer>()
                .build();
        while (eventReader.hasNext()) {
            final Segment segment = eventReader.next();
            final String tag = segment.getTag();
            if (tag.equals("UNT")) {
                builder.add(segment);
                break;
            }
            @SuppressWarnings("unused")
            final Integer nextState = stateTransitions.get(state, tag);
            // TODO Add more handing of state changes, need to redraw the paper I did from the BAPLIE rules.
        }
        return builder.build();
    }

    @SuppressWarnings("unused")
    private boolean acceptsUnh(final Segment unh) {
        // TODO Auto-generated method stub
        return true;
    }

    // TODO add acceptsUnh(segment) or identifiersAccepted() method

}
