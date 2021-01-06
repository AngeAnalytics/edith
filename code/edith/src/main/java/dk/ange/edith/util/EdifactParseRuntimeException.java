package dk.ange.edith.util;

/**
 * Parse error. Done as {@link RuntimeException} in order to maintain backwards compatibility.
 */
public class EdifactParseRuntimeException extends RuntimeException {

    public EdifactParseRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EdifactParseRuntimeException(final String message) {
        super(message);
    }

    public EdifactParseRuntimeException(final Throwable cause) {
        super(cause);
    }

}
