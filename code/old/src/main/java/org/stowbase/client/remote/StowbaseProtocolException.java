package org.stowbase.client.remote;

/**
 * Base Exception used for problems relating to org.stowbase-communication.
 */
public class StowbaseProtocolException extends RuntimeException {

    /**
     * Registers the given Exception as a cause
     * 
     * @param cause
     *            The cause of this Exception
     */
    public StowbaseProtocolException(final Throwable cause) {
        super(cause);
    }

}
