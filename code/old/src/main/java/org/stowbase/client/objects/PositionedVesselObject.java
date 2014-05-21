package org.stowbase.client.objects;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.impl.StowbaseObjectWrapped;

/**
 * Super class for objects that are tied to a specific position on a specific vessel.
 */
abstract class PositionedVesselObject extends StowbaseObjectWrapped implements StowbaseObject {

    protected PositionedVesselObject(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            How many meters the center of the row is in front of the vessel's origo.
     */
    public void setCenterToTheForeInM(final double meters) {
        inner.put("centerToTheForeInM", meters);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            How many meters the center of the row is to the port side of the vessel's origo.
     */
    public void setCenterToThePortInM(final double meters) {
        inner.put("centerToThePortInM", meters);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            How many meters the bottom of the row is above the vessel's origo.
     */
    public void setBottomAboveInM(final double meters) {
        checkIsDistance(meters);
        inner.put("bottomAboveInM", meters);
    }

    /**
     * @param meters
     *            An input distance in meters
     * @throws RuntimeException
     *             if meters is negative or not a number.
     */
    protected void checkIsDistance(final double meters) {
        if (meters < 0) {
            throw new RuntimeException(meters + " is a distance and should therefore be a positive number");
        }
    }

}
