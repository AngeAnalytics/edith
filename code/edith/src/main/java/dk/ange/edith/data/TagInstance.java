package dk.ange.edith.data;

/**
 * Tag is the three letter EDIFACT tag used to identify each segment type, instance is used when the same tag is used
 * multiple times in a group, the first use is instance 1 the second 2 etc.
 */
public class TagInstance {

    private final String tag;

    private final int instance;

    TagInstance(final String tag, final int instance) {
        if (tag == null) {
            throw new NullPointerException("tag == null");
        }
        this.tag = tag;
        this.instance = instance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + instance;
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
        final TagInstance other = (TagInstance) obj;
        if (instance != other.instance) {
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
        return "TagInstance(" + tag + "/" + instance + ")";
    }

}
