package org.stowbase.client;

import java.util.ArrayList;
import java.util.List;

import org.stowbase.client.impl.AbsoluteReference;

/**
 * 
 */
public class References extends ArrayList<Reference> {

    /**
     * Construct empty References
     */
    public References() {
        super();
    }

    /**
     * Construct empty References, prepared to contain size elements
     * 
     * @param size
     */
    public References(final int size) {
        super(size);
    }

    /**
     * Copy constructor
     * 
     * @param list
     */
    public References(final List<? extends Reference> list) {
        super(list);
    }

    /**
     * @param strings
     *            Strings in {@link Reference} form
     * @return new References object with Reference objects created from all the Strings in strings
     */
    public static References fromFullPathStrings(final String... strings) {
        final References list = new References(strings.length);
        for (final String string : strings) {
            list.add(new AbsoluteReference(string));
        }
        return list;
    }

    /**
     * @param stringList
     *            List of Strings in {@link Reference} form
     * @return new References object with Reference objects created from all the Strings in the stringList
     */
    public static References fromFullPathList(final List<String> stringList) {
        final References list = new References(stringList.size());
        for (final String string : stringList) {
            list.add(new AbsoluteReference(string));
        }
        return list;
    }

    /**
     * @param serverUniqueId
     *            serverUniqueId of all references
     * @param objectIds
     *            list of objectIds to create references to
     * @return new References object with Reference objects created from all the Strings in the stringList
     */
    public static References fromObjectIds(final String serverUniqueId, final List<String> objectIds) {
        final References list = new References(objectIds.size());
        for (final String objectId : objectIds) {
            list.add(new AbsoluteReference(serverUniqueId, objectId));
        }
        return list;
    }

    @Override
    public String toString() {
        return "References(" + super.toString() + ")";
    }

}
