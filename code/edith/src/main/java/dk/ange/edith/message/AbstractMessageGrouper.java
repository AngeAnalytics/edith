package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.PeekingIterator;
import dk.ange.edith.dom.Group;
import dk.ange.edith.dom.SegmentGrouper;
import dk.ange.edith.dom.Transition;
import dk.ange.edith.segments.Segment;

/**
 * The skeleton for a grouper that can group the segments for a particular message format
 */
public abstract class AbstractMessageGrouper {

    private final SegmentGrouper segmentGrouper;

    /**
     * Construct grouper using the table of state transitions from {@link AbstractMessageGrouper#stateTransitions()}
     */
    public AbstractMessageGrouper() {
        this.segmentGrouper = new SegmentGrouper(stateTransitions());
    }

    /**
     * @return the table of state transitions defining this message format
     */
    protected abstract ImmutableTable<String, Integer, Transition> stateTransitions();

    /**
     * @param segmentReader
     * @return a group created from the segments read out of the segment reader
     */
    public Group group(final PeekingIterator<Segment> segmentReader) {
        return segmentGrouper.group(segmentReader);
    }

}
