package org.stowbase.client.export;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.remote.RemoteStowbase;

/**
 * Export {@link StowbaseObject}s to a stowbase server.
 */
public class StowbaseServerExporter implements StowbaseObjectExporter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StowbaseServerExporter.class);

    private final StowbaseObjectFactory stowbaseObjectFactory;

    private Reference reference;

    /**
     * @param serverUri
     */
    public StowbaseServerExporter(final String serverUri) {
        log.info("Connect to stowbase...");
        final RemoteStowbase stowbase = RemoteStowbase.forBaseUrl(serverUri);
        stowbase.configAbsoluteReference();
        stowbaseObjectFactory = new LastReferenceStowbaseObjectFactory(stowbase.bundle());
    }

    @Override
    public StowbaseObjectFactory stowbaseObjectFactory() {
        return stowbaseObjectFactory;
    }

    @Override
    public void flush(final String fileName) {
        stowbaseObjectFactory.flush();
        log.info("To extract data from stowbase, use: python/query/src/tree.py -s {} > {}", reference.getObjectId(),
                fileName);
    }

    private final class LastReferenceStowbaseObjectFactory implements StowbaseObjectFactory {
        private final StowbaseObjectFactory inner;

        LastReferenceStowbaseObjectFactory(final StowbaseObjectFactory stowbaseObjectFactory) {
            this.inner = stowbaseObjectFactory;
        }

        @Override
        public StowbaseObject create(final String group) {
            final StowbaseObject stowbaseObject = inner.create(group);
            reference = stowbaseObject.getReference();
            return stowbaseObject;
        }

        @Override
        public void flush() {
            inner.flush();
        }
    }

}
