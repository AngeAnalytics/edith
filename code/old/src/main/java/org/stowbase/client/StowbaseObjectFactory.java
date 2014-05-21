package org.stowbase.client;

/**
 * Factory object that creates new {@link StowbaseObject}s. Every map must be associated with a group, e.g. "cargo".
 */
public interface StowbaseObjectFactory {

    /**
     * Creates a new {@link StowbaseObject} under the given group.
     * 
     * @param group
     *            e.g. "cargo"
     * @return The created {@link StowbaseObject}
     */
    public StowbaseObject create(final String group);

    /**
     * Flushes all objects this factory has created to permanent storage.
     */
    void flush();

}
