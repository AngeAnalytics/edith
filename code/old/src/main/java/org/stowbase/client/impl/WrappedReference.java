package org.stowbase.client.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.stowbase.client.Reference;

/**
 * A {@link Reference} that wraps an other {@link Reference}
 */
public final class WrappedReference implements Reference {

    private static final AtomicLong atomicLong = new AtomicLong(0);

    private Reference reference;

    /**
     * Create a reference that points to a unique fake reference.
     */
    public WrappedReference() {
        reference = new AbsoluteReference(null, Long.toString(atomicLong.incrementAndGet()));
    }

    /**
     * Create a reference with the wrapped reference pre set.
     * 
     * @param reference
     */
    public WrappedReference(final Reference reference) {
        this.reference = reference;
    }

    /**
     * @param reference2
     *            the reference to set
     */
    public void setReference(final Reference reference2) {
        this.reference = reference2;
    }

    @Override
    public String getObjectId() {
        return reference.getObjectId();
    }

    @Override
    public String getServerUniqueId() {
        return reference.getServerUniqueId();
    }

    @Override
    public String getServerUri() {
        return reference.getServerUri();
    }

    @Override
    public String toFullPath() {
        return reference.toFullPath();
    }

    @Override
    public String toString() {
        return reference.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return reference.equals(obj);
    }

    @Override
    public int hashCode() {
        return reference.hashCode();
    }

}
