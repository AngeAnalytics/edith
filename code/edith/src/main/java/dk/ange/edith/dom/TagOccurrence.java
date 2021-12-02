package dk.ange.edith.dom;

/**
 * Tag is the three letter EDIFACT tag used to identify each segment type, occurrence is used when the same tag is used
 * multiple times in a group, the first use is occurrence 1 the second 2 etc.
 */
// Used in Group and SegmentGrouper
public class TagOccurrence {

    private final String tag;

    private final int occurrence;

    public TagOccurrence(final String tag, final int occurrence) {
        if (tag == null) {
            throw new NullPointerException("tag == null");
        }
        this.tag = tag;
        this.occurrence = occurrence;
    }

    public static TagOccurrence fromIdentifier(final String identifier) {
        final String[] split = identifier.split("/", 2);
        if (split.length == 1) {
            return new TagOccurrence(identifier, 1);
        } else { // split.length == 2
            return new TagOccurrence(split[0], Integer.parseInt(split[1]));
        }
    }

    public String getTag() {
        return tag;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + occurrence;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TagOccurrence other = (TagOccurrence) obj;
        if (occurrence != other.occurrence) {
            return false;
        }
        if (tag == null) {
            if (other.tag != null) {
                return false;
            }
        } else if (!tag.equals(other.tag)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TagOccurrence(" + tag + "/" + occurrence + ")";
    }

    public String asIdentifier() {
        if (occurrence == 1) {
            return tag;
        } else {
            return tag + "/" + occurrence;
        }
    }

}
