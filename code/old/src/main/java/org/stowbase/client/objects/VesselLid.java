/**
 * 
 */
package org.stowbase.client.objects;

import java.util.Collection;

import org.stowbase.client.References;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;

/**
 * Represents a lid onto and beneath which stacks of containers might be placed
 */
public class VesselLid extends PositionedVesselObject implements StowbaseObject {

    /**
     * Does nothing except calling super()
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    public VesselLid(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Factory method for creating a VesselLid. Creates a new VesselLid object backed by the given factory.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static VesselLid create(final StowbaseObjectFactory mapFactory) {
        final VesselLid lid = new VesselLid(mapFactory.create("vesselLid"));
        return lid;
    }

    /**
     * Updates the default field.
     * 
     * @param vesselStacks
     *            The range of stacks beneath this lid.
     */
    public void setVesselStacksBeneathLid(final VesselStack... vesselStacks) {
        final References refs = new References(vesselStacks.length);
        for (final VesselStack stack : vesselStacks) {
            refs.add(stack.getReference());
        }
        inner.put("vesselStacksBeneathLid", refs);
    }

    /**
     * Updates the default field.
     * 
     * @param vesselStacks
     *            The range of stacks beneath this lid.
     */
    public void setVesselStacksBeneathLid(final Collection<VesselStack> vesselStacks) {
        final References refs = new References(vesselStacks.size());
        for (final VesselStack stack : vesselStacks) {
            refs.add(stack.getReference());
        }
        inner.put("vesselStacksBeneathLid", refs);
    }

    /**
     * Updates the default field.
     * 
     * @param vesselStacks
     *            The range of stacks on top this lid.
     */
    public void setVesselStacksOnTopLid(final VesselStack... vesselStacks) {
        final References refs = new References(vesselStacks.length);
        for (final VesselStack stack : vesselStacks) {
            refs.add(stack.getReference());
        }
        inner.put("vesselStacksOnTopLid", refs);
    }

    /**
     * Updates the default field.
     * 
     * @param vesselStacks
     *            The range of stacks on top this lid.
     */
    public void setVesselStacksOnTopLid(final Collection<VesselStack> vesselStacks) {
        final References refs = new References(vesselStacks.size());
        for (final VesselStack stack : vesselStacks) {
            refs.add(stack.getReference());
        }
        inner.put("vesselStacksOnTopLid", refs);
    }

}
