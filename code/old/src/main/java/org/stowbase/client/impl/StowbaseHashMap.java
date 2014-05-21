package org.stowbase.client.impl;

import java.util.HashMap;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectWrappedMap;
import org.stowbase.client.StowbaseValue;

/**
 * The general {@link StowbaseObject} implemented as a HashMap
 */
public class StowbaseHashMap extends StowbaseObjectWrappedMap implements StowbaseObject {

    /**
     * Create empty map
     */
    public StowbaseHashMap() {
        super(new HashMap<String, StowbaseValue>());
    }

    /**
     * Copy constructor
     * 
     * @param map
     */
    public StowbaseHashMap(final StowbaseObject map) {
        super(new HashMap<String, StowbaseValue>(map));
    }

    @Override
    public Reference getReference() {
        return InvalidReference.INSTANCE;
    }

}
