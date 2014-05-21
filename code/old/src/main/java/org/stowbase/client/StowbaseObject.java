package org.stowbase.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * An object in the stowbase.
 */
public interface StowbaseObject extends Map<String, StowbaseValue> {

    /**
     * @return The reference to this object's representation in the Stowbase.
     */
    public abstract Reference getReference();

    /**
     * Insert a double into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final double value);

    /**
     * Insert a double into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final Double value);

    /**
     * Insert a String into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final String value);

    /**
     * Insert a boolean into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final boolean value);

    /**
     * Insert a boolean into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final Boolean value);

    /**
     * Insert a Date into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final Date value);

    /**
     * Insert References into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final References value);

    /**
     * Insert a single Reference as References into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final Reference value);

    /**
     * Insert a List<String> into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final List<String> value);

    /**
     * Insert a List<String> into the StowbaseObject
     * 
     * @param key
     * @param value
     * @return the previous value associated with key, see {@link Map#put(Object, Object)}
     */
    public abstract StowbaseValue put(final String key, final String[] value);

}
