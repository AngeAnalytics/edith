package dk.ange.edith.segments;

import java.util.ArrayList;
import java.util.List;

/**
 * A segment, i.e. a tag and some {@link Value}s.
 * <p>
 * Immutable.
 */
public final class Segment {

    private final String tag; // Create Tag class ?

    private final List<List<Value>> composites;

    private Segment(final Builder builder) {
        this.tag = builder.tag;
        composites = new ArrayList<>(builder.composites.size());
        for (final List<String> components : builder.composites) {
            final List<Value> newComponents = new ArrayList<>(builder.composites.size());
            for (final String component : components) {
                newComponents.add(Value.valueOf(component));
            }
            composites.add(newComponents);
        }
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param compositePosition
     *            1 based position
     * @param componentPosition
     *            1 based sub position
     * @return the data element, if there is no data present the non-present data element will be returned
     */
    public Value getData(final int compositePosition, final int componentPosition) {
        // FIXME check for IndexOutOfBoundsException and return empty element
        return composites.get(compositePosition - 1).get(componentPosition - 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((composites == null) ? 0 : composites.hashCode());
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
        if (composites == null) {
            if (other.composites != null) {
                return false;
            }
        } else if (!composites.equals(other.composites)) {
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
        return "Segment(\"" + tag + "\", " + composites + ")";
    }

    /**
     * Builder for Segment
     */
    public static class Builder {

        private final String tag;

        private final List<List<String>> composites;

        /**
         * @param tag
         */
        public Builder(final String tag) {
            this.tag = tag;
            composites = new ArrayList<>();
            composites.add(new ArrayList<String>());
        }

        /**
         * Add a value to the current open data element
         *
         * @param value
         * @return this for chaining
         */
        public Builder addValue(final String value) {
            composites.get(composites.size() - 1).add(value);
            return this;
        }

        /**
         * Skip to the next data element
         *
         * @return this for chaining
         */
        public Builder nextDataElement() {
            composites.add(new ArrayList<String>());
            return this;
        }

        /**
         * @return a new Segment
         */
        public Segment build() {
            return new Segment(this);
        }

    }

}
