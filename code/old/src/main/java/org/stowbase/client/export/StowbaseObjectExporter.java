package org.stowbase.client.export;

import org.stowbase.client.StowbaseObjectFactory;

/**
 * Export stowbase objects.
 * <p>
 * Call first stowbaseObjectFactory(), then flush().
 */
public interface StowbaseObjectExporter {

    /**
     * @return StowbaseObjectFactory
     */
    public StowbaseObjectFactory stowbaseObjectFactory();

    /**
     * Flush all data at the end of a single upload.
     * 
     * @param fileName
     */
    public void flush(String fileName);

}
