package org.stowbase.client.objects;

import java.util.Collection;

import org.stowbase.client.References;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;

/**
 * Represents a position on a vessel on which containers can be stacked. Note: This kind of object is referred to as a
 * "stack", but it should not be confused with an actual stack of containers.
 */
public class VesselStack extends PositionedVesselObject implements StowbaseObject {

    /**
     * Does nothing except calling super()
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected VesselStack(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Factory method for creating a VesselStack. Creates a new VesselStack object backed by the given factory.'
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static VesselStack create(final StowbaseObjectFactory mapFactory) {
        final VesselStack stack = new VesselStack(mapFactory.create("vesselStack"));
        return stack;
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            The maximal length of a container that can be stacked in this row, unless the bay is turned
     *            "the wrong way".
     */
    public void setMaxLengthAlongshipInM(final double meters) {
        checkIsDistance(meters);
        inner.put("maxLengthAlongshipInM", meters);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            The maximal width of a container that can be stacked in this row, unless the bay is turned
     *            "the wrong way".
     */
    public void setMaxLengthAthwartshipInM(final double meters) {
        checkIsDistance(meters);
        inner.put("maxLengthAthwartshipInM", meters);
    }

    /**
     * Updates the default field.
     * 
     * @param meters
     *            The maximal height of the container stack that can be stacked in this row.
     */
    public void setMaxHeightInM(final double meters) {
        checkIsDistance(meters);
        inner.put("maxHeightInM", meters);
    }

    /**
     * Updates the default field.
     * 
     * @param rowName
     *            The name of the row, e.g. "2" - not numeric because you should not by default assume you can use
     *            arithmetics on it
     */
    public void setRowName(final String rowName) {
        inner.put("rowName", rowName);
    }

    /**
     * 
     * @return The name of the row, e.g. "2" - not numeric because you should not by default assume you can use
     *         arithmetics on it
     */
    public String getRowName() {
        return inner.get("rowName").getAsString();

    }

    /**
     * Updates the default field.
     * 
     * @param vesselStackSupports
     *            The range of container sizes/positions that can be loaded in this stack. Very often this list will
     *            contain three items: One 40-foot support object and two 20-foot ones.
     */
    public void setVesselStackSupports(final VesselStackSupport... vesselStackSupports) {
        final References refs = new References(vesselStackSupports.length);
        for (final VesselStackSupport item : vesselStackSupports) {
            refs.add(item.getReference());
        }
        inner.put("vesselStackSupports", refs);
    }

    /**
     * Updates the default field.
     * 
     * @param vesselStackSupports
     *            The range of container sizes/positions that can be loaded in this stack. Very often this list will
     *            contain three items: One 40-foot support object and two 20-foot ones.
     */
    public void setVesselStackSupports(final Collection<VesselStackSupport> vesselStackSupports) {
        final References refs = new References(vesselStackSupports.size());
        for (final VesselStackSupport item : vesselStackSupports) {
            refs.add(item.getReference());
        }
        inner.put("vesselStackSupports", refs);
    }

    /**
     * If this stack supports twinlifting. It can happen that a stack below deck does not support twinlifting because it has some guiding systems preventing this.
     * Russian stows may also make it impossible to do twinlifting above deck.
     * 
     * @param twinlifting
     */
    public void setTwinliftStatus(final boolean twinlifting) {
        inner.put("supportTwinlifting", twinlifting);
    }
}
