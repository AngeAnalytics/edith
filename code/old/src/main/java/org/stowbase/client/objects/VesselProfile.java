package org.stowbase.client.objects;

import java.net.URI;
import java.util.Collection;

import org.stowbase.client.References;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseURN;
import org.stowbase.client.StowbaseURN.FallbackCommand;
import org.stowbase.client.StowbaseURN.ThrowFallbackCommand;
import org.stowbase.client.impl.StowbaseObjectWrapped;

/**
 * Represents a profile of vessel (i.e. a container ship).
 */
public class VesselProfile extends StowbaseObjectWrapped implements StowbaseObject {
    /**
     * Does nothing except calling super()
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected VesselProfile(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Creates a new VesselProfile object backed by the given factory.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static VesselProfile create(final StowbaseObjectFactory mapFactory) {
        return new VesselProfile(mapFactory.create("vesselProfile"));
    }

    /**
     * Updates the default field.
     * 
     * @param imoCode
     *            The 7-digit IMO code assigned to this vessel's hull, e.g. "1000000".
     */
    public void setImoCode(final String imoCode) {
        if (imoCode == null) {
            throw new NullPointerException("hullImoCode == null");
        }
        final String key = "imoCode";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_VESSEL_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.vessel(imoCode, nonStandardCommand).toString());
    }

    /**
     * Set all stacks on vessel, uses the key "vesselStacks"
     * 
     * @param references
     */
    public void setVesselStacks(final References references) {
        inner.put("vesselStacks", references);
    }

    /**
     * Set all stacks on vessel, uses the key "vesselStacks"
     * 
     * @param vesselStacks
     */
    public void setVesselStacks(final Collection<VesselStack> vesselStacks) {
        final References references = new References(vesselStacks.size());
        for (final VesselStack vesselStack : vesselStacks) {
            references.add(vesselStack.getReference());
        }
        setVesselStacks(references);
    }

    /**
     * Set all lids on vessel, uses the key "vesselLids"
     * 
     * @param references
     */
    public void setVesselLids(final References references) {
        inner.put("vesselLids", references);
    }

    /**
     * Set all lids on vessel, uses the key "vesselLids"
     * 
     * @param vesselLids
     */
    public void setVesselLids(final Collection<VesselLid> vesselLids) {
        final References references = new References(vesselLids.size());
        for (final VesselLid vesselLid : vesselLids) {
            references.add(vesselLid.getReference());
        }
        setVesselLids(references);
    }

}
