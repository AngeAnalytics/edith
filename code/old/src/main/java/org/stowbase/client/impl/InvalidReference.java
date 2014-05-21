package org.stowbase.client.impl;

import org.stowbase.client.Reference;

/**
 * The invalid Reference
 */
public final class InvalidReference implements Reference {

    /**
     * The single instance of an invalid {@link Reference}
     */
    public static final InvalidReference INSTANCE = new InvalidReference();

    private InvalidReference() {
        // Do nothing
    }

    private RuntimeException exception() {
        return new UnsupportedOperationException(InvalidReference.class.getSimpleName() + " can not do anything");
    }

    @Override
    public String getObjectId() {
        throw exception();
    }

    @Override
    public String getServerUniqueId() {
        throw exception();
    }

    @Override
    public String toFullPath() {
        throw exception();
    }

    @Override
    public String getServerUri() {
        throw exception();
    }

    @Override
    public String toString() {
        return InvalidReference.class.getSimpleName();
    }

}
