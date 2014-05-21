package org.stowbase.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import org.stowbase.client.objects.DangerousGoods;

/**
 * This file contains recipes for constructing URNs that encode "real-world" names of important entities like vessels,
 * slots, and ports.
 * 
 * The methods generally handle invalid input data by calling the ExceptionObject.
 */
public final class StowbaseURN {

    /*
     * The code in this class is ordered in an unconventional way. static fields can be found all over the class in top
     * of the sections where they are used.
     */

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StowbaseURN.class);

    private StowbaseURN() {
        throw new UnsupportedOperationException("Do not instantiate");
    }

    // Section: tools

    /**
     * Creates a URI from the string String.format(formatString,objects)
     * 
     * @param formatString
     *            A format string that must yield a valid URI, e.g. "urn:stowbase.org:type:data=%s,data2=%d"
     * @param objects
     *            An array of parameters that forms a valid set of parameters to formatString, e.g. {"42"}
     * @return The created URI.
     * @throw IllegalArgumentException if the result of String.format is not a valid URI.
     */
    private static URI uri(final String formatString, final Object... objects) {
        try {
            return new URI(String.format(formatString, objects));
        } catch (final URISyntaxException e) {
            log.debug("Didn't expect this", e);
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Object to call into if input is in non standard format
     */
    public interface FallbackCommand {
        /**
         * @param string
         * @return the alternative URN
         */
        public abstract URI call(String string);

        /**
         * @param string
         * @param i1
         * @param i2
         * @param i3
         * @return the alternative URN
         */
        public abstract URI call(String string, int i1, int i2, int i3);
    }

    /**
     * This {@link FallbackCommand} will return null on any call
     */
    public static final FallbackCommand NULL_FALLBACK_COMMAND = new NullFallbackCommand();

    private static final class NullFallbackCommand implements FallbackCommand {
        @Override
        public URI call(final String string) {
            return null;
        }

        @Override
        public URI call(final String string, final int i1, final int i2, final int i3) {
            return null;
        }
    }

    /**
     * This {@link FallbackCommand} will throw {@link FallbackCommandException} on any call
     */
    public static final FallbackCommand THROW_FALLBACK_COMMAND = new ThrowFallbackCommand();

    /**
     * This {@link FallbackCommand} will throw {@link FallbackCommandException} on any call
     * <p>
     * It can be used as a base for implementing own {@link FallbackCommand}s.
     */
    public static class ThrowFallbackCommand implements FallbackCommand {
        @Override
        public URI call(final String string) {
            throw new FallbackCommandException(String.format("'%s' is non standard data", string));
        }

        @Override
        public URI call(final String string, final int i1, final int i2, final int i3) {
            throw new FallbackCommandException(String.format("'%s', %d, %d, %d is non standard data", string, i1, i2,
                    i3));
        }
    }

    /**
     * Will be thrown by {@link ThrowFallbackCommand} on any call
     */
    public static final class FallbackCommandException extends RuntimeException {
        /**
         * Constructor
         * 
         * @param message
         */
        public FallbackCommandException(final String message) {
            super(message);
        }
    }

    // Section: port

    // TODO Add support for specifying a terminal in the port?

    /**
     * UN/LOCODE for port, e.g. "NLRTM" allowed. See: http://en.wikipedia.org/wiki/UN/LOCODE
     */
    private static final Pattern PORT_PATTERN = Pattern.compile("[A-Z]{2}[A-Z2-9]{3}");

    /**
     * URN for the non standard port, if this is used the non standard data should be in xxxNonStandard
     */
    public static final URI NON_STANDARD_PORT_URN;
    static {
        try {
            NON_STANDARD_PORT_URN = new URI("urn:stowbase.org:port:non-standard");
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encodes a port identified by its UN/LOCODE.
     * 
     * @param unLocode
     *            The UN/LOCODE associated with the port, e.g. "NLRTM".
     * @param fallbackCommand
     * @return The URI encoding the input
     */
    public static URI port(final String unLocode, final FallbackCommand fallbackCommand) {
        if (unLocode != null && PORT_PATTERN.matcher(unLocode).matches()) {
            return uri("urn:stowbase.org:port:unlocode=%s", unLocode);
        } else {
            return fallbackCommand.call(unLocode);
        }
    }

    // Section: vessel

    /** IMO code for vessel hull, e.g. "1000000" or "1234567" allowed. */
    private static final Pattern IMO_CODE_PATTERN = Pattern.compile("[0-9]{7}");

    /**
     * URN for the non standard port, if this is used the non standard data should be in xxxNonStandard
     */
    public static final URI NON_STANDARD_VESSEL_URN;
    static {
        try {
            NON_STANDARD_VESSEL_URN = new URI("urn:stowbase.org:vessel:non-standard");
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encodes a vessel
     * 
     * @param imoCode
     *            The IMO code associated with the hull of the vessel.
     * @param fallbackCommand
     * @return The URI encoding the input.
     */
    public static URI vessel(final String imoCode, final FallbackCommand fallbackCommand) {
        if (imoCode != null && IMO_CODE_PATTERN.matcher(imoCode).matches()) {
            return uri("urn:stowbase.org:vessel:imo=%s", imoCode);
        } else {
            return fallbackCommand.call(imoCode);
        }
    }

    /**
     * Encodes a slot on a vessel
     * 
     * @param imoCodeOnHull
     *            The IMO code associated with the hull of the vessel.
     * @param bay
     * @param row
     * @param tier
     * @param fallbackCommand
     * @return The URI encoding the input.
     */
    public static URI slot(final String imoCodeOnHull, final int bay, final int row, final int tier,
            final FallbackCommand fallbackCommand) {
        if (imoCodeOnHull != null && IMO_CODE_PATTERN.matcher(imoCodeOnHull).matches()) {
            return uri("urn:stowbase.org:vessel:imo=%s,bay=%d,row=%d,tier=%d", imoCodeOnHull, bay, row, tier);
        } else {
            return fallbackCommand.call(imoCodeOnHull);
        }
    }

    // Section: height DC/HC

    /**
     * Known heights
     */
    private static final Pattern HEIGHT_PATTERN = Pattern.compile("DC|HC");

    /**
     * URN for the non standard port, if this is used the non standard data should be in xxxNonStandard
     */
    public static final URI NON_STANDARD_HEIGHT_URN;
    static {
        try {
            NON_STANDARD_HEIGHT_URN = new URI("urn:stowbase.org:height:non-standard");
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encodes a height that is either DC or HC.
     * 
     * @param height
     *            The height as either "DC" or "HC"
     * @param fallbackCommand
     * @return The URI encoding the input
     */
    public static URI height(final String height, final FallbackCommand fallbackCommand) {
        if (height != null && HEIGHT_PATTERN.matcher(height).matches()) {
            return uri("urn:stowbase.org:height:dchc=%s", height);
        } else {
            return fallbackCommand.call(height);
        }
    }

    // Section: container ID

    /** Container ID, e.g. "ANGE1234565" or "FOOB7654321" */
    private static final Pattern CONTAINER_ID_PATTERN = Pattern.compile("[A-Z]{4}[0-9]{7}");

    /**
     * URN for the non standard container id, if this is used the non standard data should be in xxxNonStandard
     */
    public static final URI NON_STANDARD_CONTAINER_ID_URN;
    static {
        try {
            NON_STANDARD_CONTAINER_ID_URN = new URI("urn:stowbase.org:container:non-standard");
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encodes a container ID according to ISO 6346, http://en.wikipedia.org/wiki/ISO_6346.
     * 
     * @param containerId
     *            Any container ID; must be four upper case letters followed by seven digits.
     * @param fallbackCommand
     * @return The URI encoding the input.
     */
    public static URI container(final String containerId, final FallbackCommand fallbackCommand) {
        if (containerId != null && CONTAINER_ID_PATTERN.matcher(containerId).matches()) {
            return uri("urn:stowbase.org:container:id=%s", containerId);
        } else {
            return fallbackCommand.call(containerId);
        }
    }

    // Section: DangerousGoods

    /**
     * @param dangerousGoods
     * @return String like "urn:stowbase.org:dg:unNumber=2907,imdgClass=4.1"
     */
    public static String dangerousGoods(final DangerousGoods dangerousGoods) {
        return String.format("urn:stowbase.org:dg:unNumber=%s,imdgClass=%s", dangerousGoods.unNumber,
                dangerousGoods.imdgClass);
    }

}
