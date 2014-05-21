package org.stowbase.client;

/**
 * Exception used when bad input data causes problems. It is made to be able to catch when the user gives bad data in
 * the JSON.
 */
public class BadFormatException extends RuntimeException {

    /**
     * @param string
     */
    public BadFormatException(final String string) {
        super(string);
    }

}
