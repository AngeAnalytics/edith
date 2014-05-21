package org.stowbase.client.impl;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;

/**
 * {@link StowbaseHashMap} that knows its own {@link Reference}.
 */
public class ReferencedStowbaseHashMap extends StowbaseHashMap {

    private final WrappedReference reference;

    /**
     * Create empty map
     */
    public ReferencedStowbaseHashMap() {
        this.reference = new WrappedReference();
    }

    /**
     * Copy constructor
     * 
     * @param map
     */
    public ReferencedStowbaseHashMap(final StowbaseObject map) {
        super(map);
        this.reference = new WrappedReference();
    }

    /**
     * @return the reference
     */
    @Override
    public WrappedReference getReference() {
        return reference;
    }

}
