package org.stowbase.client.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseBaseObject;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseValue;

/**
 * A {@link StowbaseObject} that wraps around an other {@link StowbaseObject} that holds the data
 */
public class StowbaseObjectWrapped extends StowbaseBaseObject implements StowbaseObject {

    /**
     * The {@link StowbaseObject} that holds the data
     */
    protected final StowbaseObject inner;

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    public StowbaseObjectWrapped(final StowbaseObject inner) {
        if (inner == null) {
            throw new NullPointerException("inner == null");
        }
        this.inner = inner;
    }

    // Auto generated delegate methods:

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
    public Collection<StowbaseValue> values() {
        return inner.values();
    }

    @Override
    public Reference getReference() {
        return inner.getReference();
    }

}
