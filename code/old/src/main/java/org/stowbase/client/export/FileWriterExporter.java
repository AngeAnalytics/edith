package org.stowbase.client.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Collect data in memory and write it out to a file on flush.
 */
public class FileWriterExporter extends WriterExporter implements StowbaseObjectExporter {

    /**
     * Create new FileWriterExporter
     */
    public FileWriterExporter() {
        super(null);
    }

    @Override
    public void flush(final String fileName) {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            super.flush(fileName);
            writer.write("\n");
            writer.close();
            writer = null;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
