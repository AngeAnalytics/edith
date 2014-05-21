package org.stowbase.client.objects;

import static org.stowbase.client.objects.Units.FOOT;

import java.util.List;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;

/**
 * Represents a position on a vessel where containers of a certain size can be stacked.
 */
public class VesselStackSupport extends PositionedVesselObject implements StowbaseObject {

    /**
     * Does nothing except calling super()
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected VesselStackSupport(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Factory method for creating objects of this class. The width/maxLengthAthwartship is set to 8 feet because this
     * is almost always the case (the exception being athwartship stacks).
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @param centerToTheForeInM
     *            aka "LCG": container center position in meters
     * @param bottomAboveInM
     *            aka "VCG": container bottom position in meters
     * @param centerToThePortInM
     *            aka "TCG": container center position in meters
     * @return The constructed RemoteObject
     */
    public static VesselStackSupport create(final StowbaseObjectFactory mapFactory, final double centerToTheForeInM,
            final double bottomAboveInM, final double centerToThePortInM) {
        final VesselStackSupport item = new VesselStackSupport(mapFactory.create("vesselStackSupport"));
        item.setCenterToTheForeInM(centerToTheForeInM);
        item.setBottomAboveInM(bottomAboveInM);
        item.setCenterToThePortInM(centerToThePortInM);
        item.setLengthAthwartshipInM(8.0 * FOOT);
        return item;
    }

    /**
     * Updates the default field.
     * 
     * @param dcTiersFromBelow
     *            The list of tier names - WHEN STACKING DCs or something equivalent - starting from the bottom ones.
     *            Don't use this if you're stacking many flatracks in one DC slot.
     */
    public void setDcTiersFromBelow(final List<String> dcTiersFromBelow) {
        inner.put("dcTiersFromBelow", dcTiersFromBelow);
    }
    
    /**
     * Updates the default field.
     * 
     * @param dcReeferTiersFromBelow
     *            The list of reefer tier names - WHEN STACKING DCs or something equivalent - starting from the bottom ones.
     *            Don't use this if you're stacking many flatracks in one DC slot.
     */
    public void setDcReeferTiersFromBelow(final List<String> dcReeferTiersFromBelow) {
        inner.put("dcReeferTiersFromBelow", dcReeferTiersFromBelow);
    }

    /**
     * Updates the default field.
     * 
     * @param dcFourtyFiveTiersFromBelow
     *            The list of 45 tier names - WHEN STACKING DCs or something equivalent - starting from the bottom ones.
     *            Don't use this if you're stacking many flatracks in one DC slot.
     */
    public void setDcFourtyFiveTiersFromBelow(final List<String> dcFourtyFiveTiersFromBelow) {
        inner.put("dcFourtyFiveTiersFromBelow", dcFourtyFiveTiersFromBelow);
    }

    /**
     * Updates the default field.
     *
     * @param bayName
     *            The name of the bay used for containers stacked in this position, e.g. "39" or "40".
     */
    public void setBayName(final String bayName) {
        inner.put("bayName", bayName);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            The length of a container that can be stacked here, (unless the bay is turned "the wrong way").
     */
    public void setLengthAlongshipInM(final double meters) {
        checkIsDistance(meters);
        inner.put("lengthAlongshipInM", meters);
    }

    /**
     * Utility method for calling setLengthAlongship() with 40 feet as parameter.
     */
    public void setFeuBayLength() {
        setLengthAlongshipInM(40.0 * FOOT);
    }

    /**
     * Utility method for calling setLengthAlongship() with 20 feet as parameter.
     */
    public void setTeuBayLength() {
        setLengthAlongshipInM(20.0 * FOOT);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            The width of a container that can be stacked here, (unless the bay is turned "the wrong way").
     */
    public void setLengthAthwartshipInM(final double meters) {
        checkIsDistance(meters);
        inner.put("lengthAthwartshipInM", meters);
    }

    /**
     * Set the position of the top of the stack. The max height of the stack is the difference between the top and the
     * bottom, if there is no top then the height is unlimited.
     * 
     * @param meters
     */
    public void setTopAboveInM(final double meters) {
        checkIsDistance(meters);
        inner.put("topAboveInM", meters);
    }

    /**
     * The maximum weight this vessel stack support can carry. If this is a FEU stack that also is put 20' containers in
     * then half their weight is also counted against this limit.
     * 
     * @param weight
     */
    public void setMaxWeightInKg(final double weight) {
        inner.put("maxWeightInKg", weight);
    }

    /**
     * Forbid Dangerous Goods in this stack.
     */
    public void forbidImo() {
        inner.put("imoForbidden", true);
    }

}
