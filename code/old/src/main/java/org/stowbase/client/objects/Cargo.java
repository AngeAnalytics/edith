package org.stowbase.client.objects;

import java.util.ArrayList;
import java.util.List;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseURN;
import org.stowbase.client.impl.StowbaseObjectWrapped;

/**
 * Represents a single unit of cargo on a trip from the original arrival to the final destination. It can eg. be a
 * container or break bulk.
 */
public class Cargo extends StowbaseObjectWrapped implements StowbaseObject {

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected Cargo(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Makes the given factory create an object for the group "/cargo".
     * 
     * @param mapFactory
     *            The returned object is created by this factory.
     * @return The created object.
     */
    protected static StowbaseObject createInner(final StowbaseObjectFactory mapFactory) {
        return mapFactory.create("cargo");
    }

    /**
     * Updates the default field.
     * 
     * @param grossWeightInKg
     *            The total weight of the cargo including packaging, in kg
     */
    public void setGrossWeightInKg(final double grossWeightInKg) {
        inner.put("grossWeightInKg", grossWeightInKg);
    }

    /**
     * Updates the default field.
     * 
     * @param heightInM
     *            The height of the container, in meters
     */
    public void setHeightInM(final double heightInM) {
        inner.put("heightInM", heightInM);
    }

    /**
     * @param dangerousGoodsList
     */
    public void setDangerousGoods(final List<DangerousGoods> dangerousGoodsList) {
        final List<String> strings = new ArrayList<String>(dangerousGoodsList.size());
        for (final DangerousGoods dangerousGoods : dangerousGoodsList) {
            strings.add(StowbaseURN.dangerousGoods(dangerousGoods));
        }
        inner.put("dangerousGoods", strings);
    }

}
