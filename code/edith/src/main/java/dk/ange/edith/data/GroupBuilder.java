package dk.ange.edith.data;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

/**
 * The DOM like representation of an EDIFACT message. Used both for the entire message and for the groups inside it.
 * Will not contain the UNA, UNB or UNZ segments as that is part of the interchange headers that is outside the message.
 * <p>
 * Mutable.
 */
public class GroupBuilder {

    private final ListMultimap<TagOccurrence, Segment> segments = MultimapBuilder.hashKeys().arrayListValues().build();

    private final ListMultimap<Integer, GroupBuilder> groups = MultimapBuilder.hashKeys().arrayListValues().build();

    /**
     * @return a Group build from the data in the builder
     */
    public Group build() {
        return new Group(this);
    }

    /**
     * @param segment
     * @param occurrence
     */
    public void add(final Segment segment, final int occurrence) {
        segments.put(new TagOccurrence(segment.getTag(), occurrence), segment);
    }

    /**
     * Adds segment as the first occurrence
     *
     * @param segment
     */
    public void add(final Segment segment) {
        add(segment, 1);
    }

    /**
     * @param groupNumber
     * @param group
     */
    public void add(final int groupNumber, final GroupBuilder group) {
        groups.put(groupNumber, group);
    }

}
