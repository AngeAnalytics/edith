package dk.ange.edith.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The DOM like representation of an EDIFACT message. Used both for the entire message and for the groups inside it.
 * Will not contain the UNA, UNB or UNZ segments as that is part of the interchange headers that is outside the message.
 * <p>
 * Immutable.
 */
// TODO @SuppressWarnings("unused")
@SuppressWarnings("unused")
public class Group {

    // TODO perhaps group should track its own number?

    /*-
     * The data this is representing is the expanded tree of a message:
     * UNH
     * AAA
     * BBB
     * Grp1
     *   CCC
     *   DDD
     * Grp1
     *   CCC
     *   EEE
     *   Grp2
     *     FFF
     *   CCC,2
     * AAA,2
     * UNT
     */

    private final Map<TagOccurrence, List<Segment>> segments;

    private final Map<Integer, List<Group>> groups;

    /**
     * @param groupBuilder
     */
    public Group(final GroupBuilder groupBuilder) {
        // TODO copy data, make immutable
        this.segments = null;
        this.groups = null;
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

    // TODO create a better way to handle segments (and groups?) that appears exactly 1 times ?

    // TODO throw errors if we ask for groups that does not exist ? (this is made a little difficult by multimap)

    /**
     * @param groupNumber
     * @return the list of all groups for the given group number
     */
    public List<Group> getGroupList(final int groupNumber) {
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

}
