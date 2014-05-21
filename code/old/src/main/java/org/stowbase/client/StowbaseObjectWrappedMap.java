package org.stowbase.client;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * A StowbaseObject that wraps a Map and is missing the implementation of getReference.
 */
@JsonDeserialize(contentAs = StowbaseValue.class)
public abstract class StowbaseObjectWrappedMap extends StowbaseBaseObject implements StowbaseObject {

    private final Map<String, StowbaseValue> inner;

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link Map} that holds the data
     */
    public StowbaseObjectWrappedMap(final Map<String, StowbaseValue> inner) {
        if (inner == null) {
            throw new NullPointerException("inner == null");
        }
        this.inner = inner;
    }

    // Auto generated delegate methods on inner:

    @Override
    public void clear() {
        inner.clear();
    }

    @Override
    public boolean containsKey(final Object key) {
        return inner.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return inner.containsValue(value);
    }

    @Override
    public Set<java.util.Map.Entry<String, StowbaseValue>> entrySet() {
        return inner.entrySet();
    }

    @Override
    public boolean equals(final Object o) {
        return inner.equals(o);
    }

    @Override
    public StowbaseValue get(final Object key) {
        return inner.get(key);
    }

    @Override
    public int hashCode() {
        return inner.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return inner.keySet();
    }

    @Override
    public StowbaseValue put(final String key, final StowbaseValue value) {
        return inner.put(key, value);
    }

    @Override
    public void putAll(final Map<? extends String, ? extends StowbaseValue> m) {
        inner.putAll(m);
    }

    @Override
    public StowbaseValue remove(final Object key) {
        return inner.remove(key);
    }

    @Override
    public int size() {
        return inner.size();
    }

    @Override
    public String toString() {
        return inner.toString();
    }

    @Override
    public Collection<StowbaseValue> values() {
        return inner.values();
    }

}
