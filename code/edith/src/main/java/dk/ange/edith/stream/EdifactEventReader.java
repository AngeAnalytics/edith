package dk.ange.edith.stream;

import java.util.Iterator;

import dk.ange.edith.data.Segment;

/**
 * EDIFACT reader inspired by the StAX interface.
 */
public interface EdifactEventReader extends Iterator<Segment>, AutoCloseable {

    /**
     * @return the next segment without reading it from the stream, will return null if at EOF
     */
    public Segment peek();

    /**
     * Report a non fatal parse error
     *
     * @param message
     */
    // TODO add categories? DATA_LOSS_IMPORTANT, DATA_LOSS_UNKNOWN, DATA_LOSS_MINOR, EXTRA_DATA
    public void report(String message);

    @Override
    public void close();

}
