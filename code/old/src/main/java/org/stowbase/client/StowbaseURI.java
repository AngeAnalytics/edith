package org.stowbase.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * This file contains recipes for constructing URIs that encode "real-world" names of important entities like vessels,
 * slots, and ports.
 * 
 * The methods generally handle invalid input data by creating a dummy URI, or a URI that encodes the main part of the
 * good data, i.e. a "best effort". This is useful when a client just needs to pass on the best data it's got. If you
 * need a different behaviour, for example if you want Exceptions thrown, it is recommended to extend this class.
 * 
 * TODO: We should validate input much more, e.g. compare port names with a list of registered ones.
 */
public abstract class StowbaseURI {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StowbaseURI.class);

    /**
     * UN location codes for ports, e.g. "NLRTM" or "nlRTM" allowed. Lower case in input will be transformed to upper
     * case in uri.
     */
    private static final Pattern PORT_PATTERN = Pattern.compile("[a-zA-Z2-9]{5}");

    /** Standard foot length of a container, e.g. "40" or "20". */
    private static final Pattern FOOT_LENGTH_PATTERN = Pattern.compile("(20)|(40)|(45)");

    /**
     * Encodes a port identified by its UN location code.
     * 
     * TODO: Add support for specifying a terminal in the port.
     * 
     * @param unLocationCode
     *            The UN location code associated with the port, e.g. "NLRTM". Lower case in input will be transformed
     *            to upper case.
     * @return The URI encoding the input.
     */
    public static URI forPort(final String unLocationCode) {
        if ((null != (unLocationCode)) && (PORT_PATTERN.matcher(unLocationCode).matches())) {
            return uri("urn:stowbase.org:port:unlocode=%s", unLocationCode.toUpperCase());
        } else {
            return uri("urn:stowbase.org:port:unknown");
        }
    }

    /**
     * Encodes a standard length in feet of a container, e.g. "20" or "40".
     * 
     * @param footLength
     *            Standard foot length of a container, e.g. "40" or "20"
     * @return The URI encoding the input.
     */
    public static URI forFootLength(final String footLength) {
        if ((null != (footLength)) && (FOOT_LENGTH_PATTERN.matcher(footLength).matches())) {
            return uri("urn:stowbase.org:container:length=%s", footLength);
        } else {
            return uri("urn:stowbase.org:container:length=unknown");
        }
    }

    /**
     * Creates a URI from the string String.format(formatString,objects)
     * 
     * @param formatString
     *            A format string that must yield a valid URI, e.g. "org.stowbase.foo://bar/%s"
     * @param objects
     *            An array of parameters that forms a valid set of parameters to formatString, e.g. {"42"}
     * @return The created URI. In case the result of String.format is NOT a valid URI, returns a general URI that
     *         indicates badly formed data.
     */
    protected static URI uri(final String formatString, final Object... objects) {
        try {
            return new URI(String.format(formatString, objects));
        } catch (final URISyntaxException e) {
            return badDataURI();
        }
    }

    /**
     * @return A general URI that indicates badly formed data
     */
    protected static URI badDataURI() {
        try {
            return new URI("urn:ERROR");
        } catch (final URISyntaxException dummyURINotAccepted) {
            // The string up there is not a URI?? Throw an Exception
            log.debug("This method's dummy URI had a wrong syntax", dummyURINotAccepted);
            throw new RuntimeException("This method's dummy URI had a wrong syntax", dummyURINotAccepted);
        }
    }

}
