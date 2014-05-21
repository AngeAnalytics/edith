package dk.ange.edith.data;

import java.util.List;

/**
 * A segment, i.e. a tag and some {@link DataElement}s.
 * <p>
 * Immutable.
 */
public final class Segment {

    private final String tag; // TODO create Tag class ?

    private final List<List<DataElement>> data;

    /**
     * @param tag
     * @param data
     */
    public Segment(final String tag, final List<List<DataElement>> data) {
        this.tag = tag;
        this.data = data; // TODO copy lists and check for nulls, make sure equals work
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param position
     *            1 based position
     * @param compositePosition
     *            1 based sub position
     * @return the data element, if there is no data present the non-present data element will be returned
     */
    public DataElement getData(final int position, final int compositePosition) {
        // FIXME check for IndexOutOfBoundsException and return empty element
        return data.get(position - 1).get(compositePosition - 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
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
        final Segment other = (Segment) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
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
        return "Segment('" + tag + "', " + data + ")";
    }

}
