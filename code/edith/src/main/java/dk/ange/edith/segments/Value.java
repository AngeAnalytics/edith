package dk.ange.edith.segments;

/*
 * This class could perhaps be extended to also contain a type given by the document structure, but it is not
 * obvious that we would gain anything from such an extension.
 */
/**
 * The value of a component data element or of a simple data element. Will either be non-present or contain a data value
 * that is a non-empty String.
 * <p>
 * Immutable.
 */
public final class Value {

    /**
     * The non-present data element. Contains null.
     */
    public static final Value NON_PRESENT = new Value(null);

    // Will never be the empty string
    private final String data;

    /**
     * @param data
     *            data to wrap in value, use null if the data element is non-present. If the empty string is given it
     *            will be translated to null.
     * @return value representing the given data
     */
    public static Value valueOf(final String data) {
        if (data == null || data.isEmpty()) {
            return NON_PRESENT;
        } else {
            return new Value(data);
        }
    }

    private Value(final String data) {
        assert data == null || !data.isEmpty();
        this.data = data;
    }

    /**
     * @return true if the data element is present
     */
    public boolean isPresent() {
        return data != null;
    }

    private void throwIfNonPresent() {
        if (!isPresent()) {
            throw new IllegalStateException("Value of non-present data element");
        }
    }

    /**
     * @return the data of the value as a string
     * @throws IllegalStateException
     *             if the data element is non-present
     */
    public String asString() {
        throwIfNonPresent();
        return data;
    }

    /**
     * @param default_
     *            value to return if the data element is non-present
     * @return the data of the value as a string or the default value
     */
    public String asString(final String default_) {
        return isPresent() ? data : default_;
    }

    /**
     * @return the data of the value as a double
     * @throws IllegalStateException
     *             if the data element is non-present
     * @throws NumberFormatException
     *             if the data element does not contain a parsable double
     */
    public double asDouble() {
        throwIfNonPresent();
        return Double.parseDouble(data);
    }

    /**
     * @param default_
     *            value to return if the data element is non-present
     * @return the data of the value as a double or the default value
     * @throws NumberFormatException
     *             if the data element does not contain a parsable double
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
        final Value other = (Value) obj;
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
        return "\"" + data + "\"";
    }

}
