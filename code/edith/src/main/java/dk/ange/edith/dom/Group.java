package dk.ange.edith.dom;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;

import dk.ange.edith.segments.Segment;

/**
 * The DOM like representation of an EDIFACT message. Used both for the entire message and for the groups inside it.
 * Will not contain the UNA, UNB or UNZ segments as that is part of the interchange headers that is outside the message.
 * <p>
 * Immutable.
 */
public class Group {

    private static String NEWLINE = System.getProperty("line.separator");

    // Perhaps group should track its own number?

    private final ListMultimap<TagOccurrence, Segment> segments;

    private final ListMultimap<Integer, Group> groups;

    private Group(final Builder builder) {
        this.segments = Multimaps.unmodifiableListMultimap(ArrayListMultimap.create(builder.segments));
        this.groups = Multimaps.unmodifiableListMultimap(buildGroups(builder.groups));
    }

    private static ListMultimap<Integer, Group> buildGroups(final ListMultimap<Integer, Builder> groupBuilders) {
        final ListMultimap<Integer, Group> groups = ArrayListMultimap.create();
        for (final Entry<Integer, Builder> entry : groupBuilders.entries()) {
            groups.put(entry.getKey(), entry.getValue().build());
        }
        return groups;
    }

    /**
     * @param tag
     * @param occurrence
     * @return the list of all segments for the given tag+occurrence combo
     */
    public List<Segment> getSegmentList(final String tag, final int occurrence) {
        final List<Segment> list = segments.get(new TagOccurrence(tag, occurrence));
        return list == null ? Collections.<Segment>emptyList() : list;
    }

    /**
     * @param tag
     * @return the list of all segments for the first given tag
     */
    public List<Segment> getSegmentList(final String tag) {
        return getSegmentList(tag, 1);
    }

    /**
     * @param tag
     * @param occurrence
     * @return the single segment for the given tag+occurrence combo, null if there is no such segment
     * @throws IllegalStateException
     *             if there is more than one segment
     */
    public Segment getSegment(final String tag, final int occurrence) {
        final List<Segment> list = getSegmentList(tag, occurrence);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IllegalStateException("More than one segment, list.size()=" + list.size());
        }
    }

    /**
     * @param tag
     * @return the single segment for the first given tag, null if there is no such segment
     * @throws IllegalStateException
     *             if there is more than one segment
     */
    public Segment getSegment(final String tag) {
        return getSegment(tag, 1);
    }

    // create a better way to handle segments (and groups?) that appears exactly 1 times ?

    /**
     * @param groupNumber
     * @return the list of all groups for the given group number
     */
    public List<Group> getGroupList(final int groupNumber) {
        // throw errors if we ask for groups that does not exist ? (this is made a little difficult by multimap)
        final List<Group> list = groups.get(groupNumber);
        return list == null ? Collections.<Group>emptyList() : list;
    }

    /**
     * @param groupNumber
     * @return the single group for the group number, null if there is no such group
     * @throws IllegalStateException
     *             if there is more than one group
     */
    public Group getGroup(final int groupNumber) {
        final List<Group> list = getGroupList(groupNumber);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IllegalStateException("More than one group, list.size()=" + list.size());
        }
    }

    /**
     * @return A multipage debug string representing the content of this Group
     */
    public String toDebugString() {
        return toDebugString("");
    }

    private String toDebugString(final String indent) {
        final StringBuilder builder = new StringBuilder();
        for (final Entry<TagOccurrence, Segment> entry : segments.entries()) {
            builder.append(indent).append(entry.getKey()).append(": ").append(entry.getValue()).append(NEWLINE);
        }
        for (final Entry<Integer, Group> entry : groups.entries()) {
            builder.append(indent).append("Group ").append(entry.getKey()).append(":").append(NEWLINE);
            builder.append(entry.getValue().toDebugString(indent + "  "));
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Group(" + segments.size() + " segments, " + groups.size() + " groups)";
    }

    /**
     * A builder for the {@link Group}.
     * <p>
     * Mutable.
     */
    public static class Builder {

        private final ListMultimap<TagOccurrence, Segment> segments = ArrayListMultimap.create();

        private final ListMultimap<Integer, Builder> groups = ArrayListMultimap.create();

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
        public void add(final int groupNumber, final Builder group) {
            groups.put(groupNumber, group);
        }

        /**
         * @return a Group build from the data in the builder
         */
        public Group build() {
            return new Group(this);
        }

    }

}
