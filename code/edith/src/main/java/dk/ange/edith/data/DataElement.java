package dk.ange.edith.data;

/**
 * A simple data element. Will either be non-present or contain a data value that is a non-empty String.
 * <p>
 * Immutable.
 * <p>
 * This class could perhaps be extended to also contain a type given by the document structure, but it is not obvious
 * that we would gain anything from such an extension.
 */
public final class DataElement {

    /**
     * The non-present data element. Contains null.
     */
    public static final DataElement NON_PRESENT = new DataElement(null);

    // Will never be the empty string
    private final String data;

    /**
     * @param data
     *            data to wrap in element, null if the element is not present. If the empty string is given it will be
     *            translated to null.
     * @return A data element representing the given data
     */
    public static DataElement valueOf(final String data) {
        if (data == null || data.isEmpty()) {
            return NON_PRESENT;
        } else {
            return new DataElement(data);
        }
    }

    private DataElement(final String data) {
        assert data == null || !data.isEmpty();
        this.data = data;
    }

    /**
     * @return true if the element contains no data
     */
    public boolean isPresent() {
        return data == null;
    }

    private void throwIfNonPresent() {
        if (!isPresent()) {
            throw new IllegalStateException("DataElement is not present");
        }
    }

    /**
     * @return the data in the element as a string
     * @throws IllegalStateException
     *             if the element is non-present
     */
    public String asString() {
        throwIfNonPresent();
        return data;
    }

    /**
     * @param default_
     *            value to return if the element is not present
     * @return the data in the element as a string or the default value
     */
    public String asString(final String default_) {
        return isPresent() ? data : default_;
    }

    /**
     * @return the data in the element as a double
     * @throws IllegalStateException
     *             if the element is non-present
     * @throws NumberFormatException
     *             if the element does not contain a parsable double
     */
    public double asDouble() {
        throwIfNonPresent();
        return Double.parseDouble(data);
    }

    /**
     * @param default_
     *            value to return if the element is not present
     * @return the data in the element as a double or the default value
     * @throws NumberFormatException
     *             if the element does not contain a parsable double
     */
    public double asDouble(final double default_) {
        return isPresent() ? Double.parseDouble(data) : default_;
    }

    // TODO add some more data converters like asInt and asTimestamp

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
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
        final DataElement other = (DataElement) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataElement(" + data + ")";
    }

}
