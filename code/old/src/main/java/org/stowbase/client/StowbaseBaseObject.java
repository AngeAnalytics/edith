package org.stowbase.client;

import java.util.Date;
import java.util.List;

/**
 * The base object that most StowbaseObjects should extend
 */
public abstract class StowbaseBaseObject implements StowbaseObject {

    @Override
    public StowbaseValue put(final String key, final double value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final Double value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final String value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final boolean value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final Boolean value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final Date value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final References value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final Reference value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final List<String> value) {
        return put(key, new StowbaseValue(value));
    }

    @Override
    public StowbaseValue put(final String key, final String[] value) {
        return put(key, new StowbaseValue(value));
    }

}
