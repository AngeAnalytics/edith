package org.stowbase.client.objects;

import java.net.URI;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseURI;
import org.stowbase.client.StowbaseURN;
import org.stowbase.client.StowbaseURN.FallbackCommand;
import org.stowbase.client.StowbaseURN.ThrowFallbackCommand;
import org.stowbase.client.impl.StowbaseObjectWrapped;

/**
 * Represents a single move of a piece of cargo.
 */
public class Move extends StowbaseObjectWrapped implements StowbaseObject {

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected Move(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Creates a new Move object backed by the given factory.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static Move create(final StowbaseObjectFactory mapFactory) {
        return new Move(mapFactory.create("move"));
    }

    /**
     * Utility method for creating a new move and setting many fields in one call. Create a load move, i.e. a move from
     * a terminal to a vessel.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @param vesselImo
     *            The IMO code associated with the vessel's hull.
     * @param bay
     *            The bay on which the container was loaded.
     * @param row
     *            The row on which the container was loaded.
     * @param tier
     *            The tier on which the container was loaded.
     * @param portCode
     *            The code of the port from which the container was loaded.
     * @return The builder for the created move. You can set more field on this if you need.
     */
    public static Move createLoadMove(final StowbaseObjectFactory mapFactory, final String portCode,
            final String vesselImo, final Integer bay, final Integer row, final Integer tier) {
        final Move move = create(mapFactory);
        move.setFromPort(portCode);
        move.setToSlot(vesselImo, bay, row, tier);
        return move;
    }

    /**
     * Utility method for creating a new move and setting many fields in one call. Creates a discharge move, i.e. a move
     * from a vessel to a terminal.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @param vesselImo
     *            The IMO code associated with the vessel's hull.
     * @param bay
     *            The bay from which the container was discharged.
     * @param row
     *            The row from which the container was discharged.
     * @param tier
     *            The tier from which the container was discharged.
     * @param portCode
     *            The code of the port to which the container was discharged.
     * @return The builder for the created move. You can set more field on this if you need.
     */
    public static Move createDischargeMove(final StowbaseObjectFactory mapFactory, final String vesselImo,
            final int bay, final int row, final int tier, final String portCode) {
        final Move move = create(mapFactory);
        move.setFromSlot(vesselImo, bay, row, tier);
        move.setToPort(portCode);
        return move;
    }

    /**
     * Specifies where the container came from.
     * 
     * @param locationPath
     *            Use {@link StowbaseURI} to create this.
     */
    public void setFrom(final URI locationPath) {
        inner.put("from", locationPath.toString());
    }

    /**
     * Specifies where the container came from.
     * 
     * @param unLocode
     */
    public void setFromPort(final String unLocode) {
        if (unLocode == null) {
            throw new NullPointerException("unLocode == null");
        }
        final String key = "from";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_PORT_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.port(unLocode, nonStandardCommand).toString());
    }

    /**
     * Specifies where the container came from.
     * 
     * @param vesselImo
     * @param bay
     * @param row
     * @param tier
     */
    public void setFromSlot(final String vesselImo, final int bay, final int row, final int tier) {
        if (vesselImo == null) {
            throw new NullPointerException("vesselImo == null");
        }
        final String key = "from";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string, final int i1, final int i2, final int i3) {
                inner.put(keyNonStandard, String.format("%s,%d,%d,%d", string, i1, i2, i3));
                return StowbaseURN.NON_STANDARD_VESSEL_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.slot(vesselImo, bay, row, tier, nonStandardCommand).toString());
    }

    /**
     * Specifies where the container came from.
     * 
     * @param vesselImo
     */
    public void setFromVessel(final String vesselImo) {
        if (vesselImo == null) {
            throw new NullPointerException("vesselImo == null");
        }
        final String key = "from";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_VESSEL_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.vessel(vesselImo, nonStandardCommand).toString());
    }

    /**
     * Specifies where the container was moved to.
     * 
     * @param locationPath
     *            Use {@link StowbaseURI} to create this.
     */
    public void setTo(final URI locationPath) {
        inner.put("to", locationPath.toString());
    }

    /**
     * Specifies where the container was moved to.
     * 
     * @param unLocode
     */
    public void setToPort(final String unLocode) {
        if (unLocode == null) {
            throw new NullPointerException("unLocode == null");
        }
        final String key = "to";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_PORT_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.port(unLocode, nonStandardCommand).toString());
    }

    /**
     * Specifies where the container was moved to.
     * 
     * @param vesselImo
     * @param bay
     * @param row
     * @param tier
     */
    public void setToSlot(final String vesselImo, final int bay, final int row, final int tier) {
        if (vesselImo == null) {
            throw new NullPointerException("vesselImo == null");
        }
        final String key = "to";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string, final int i1, final int i2, final int i3) {
                inner.put(keyNonStandard, String.format("%s,%d,%d,%d", string, i1, i2, i3));
                return StowbaseURN.NON_STANDARD_VESSEL_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.slot(vesselImo, bay, row, tier, nonStandardCommand).toString());
    }

    /**
     * Specifies where the container was moved to.
     * 
     * @param vesselImo
     */
    public void setToVessel(final String vesselImo) {
        if (vesselImo == null) {
            throw new NullPointerException("vesselImo == null");
        }
        final String key = "to";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_VESSEL_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.vessel(vesselImo, nonStandardCommand).toString());
    }

    /**
     * Specifies which piece of cargo this Move moves.
     * 
     * @param cargo
     */
    public void setCargo(final Cargo cargo) {
        inner.put("cargo", cargo.getReference());
    }

}
