package org.stowbase.client.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseBaseObject;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseValue;

/**
 * A {@link StowbaseObject} that can not do anything
 */
public class StowbaseObjectUnsupported extends StowbaseBaseObject implements StowbaseObject {

    private String getMessage() {
        return "This operation is not supported by " + this.getClass().getSimpleName() + ", message is from "
                + StowbaseObjectUnsupported.class.getSimpleName();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public boolean containsKey(final Object key) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public Set<java.util.Map.Entry<String, StowbaseValue>> entrySet() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public StowbaseValue get(final Object key) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public StowbaseValue put(final String key, final StowbaseValue value) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public void putAll(final Map<? extends String, ? extends StowbaseValue> m) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public StowbaseValue remove(final Object key) {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public Collection<StowbaseValue> values() {
        throw new UnsupportedOperationException(getMessage());
    }

    @Override
    public Reference getReference() {
        throw new UnsupportedOperationException(getMessage());
    }

}
