package org.stowbase.client.export;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseObjectWrappedMap;
import org.stowbase.client.StowbaseValue;
import org.stowbase.client.impl.AbsoluteReference;
import org.stowbase.client.impl.ReferenceObject;
import org.stowbase.client.remote.JSON;

/**
 * Collect data in memory and write it out to a Writer on flush.
 */
public class WriterExporter implements StowbaseObjectExporter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WriterExporter.class);

    private final List<ReferenceObject> referenceObjects = new ArrayList<ReferenceObject>();

    /**
     * The Writer to write to, will be used in flush()
     */
    protected Writer writer;

    /**
     * @param writer
     */
    public WriterExporter(final Writer writer) {
        this.writer = writer;
    }

    @Override
    public StowbaseObjectFactory stowbaseObjectFactory() {
        return new ListStowbaseObjectFactory();
    }

    @Override
    public void flush(final String fileName) {
        try {
            JSON.writeValue(referenceObjects, writer);
            writer.flush();
            log.debug("Wrote {} objects to '{}'", referenceObjects.size(), fileName);
            referenceObjects.clear();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final class ListStowbaseObjectFactory implements StowbaseObjectFactory {
        int objectCounter = 0;

        @Override
        public StowbaseObject create(final String group) {
            final StowbaseObject object = new StowbaseTreeObject(objectCounter++);
            object.put("_group", group);
            referenceObjects.add(new ReferenceObject(object));
            return object;
        }

        @Override
        public void flush() {
            // Do nothing
        }
    }

    private static final class StowbaseTreeObject extends StowbaseObjectWrappedMap {
        int objectNumber;

        public StowbaseTreeObject(final int objectNumber) {
            super(new TreeMap<String, StowbaseValue>());
            this.objectNumber = objectNumber;
        }

        @Override
        public Reference getReference() {
            return new AbsoluteReference(null, Integer.toString(objectNumber));
        }
    }
}
