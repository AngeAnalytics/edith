package org.stowbase.client.impl;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;
import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;

/**
 * Struct with a Reference and its {@link StowbaseObject}
 */
public class ReferenceObject {

    /**
     * The Reference
     */
    @JsonProperty
    public Reference reference;

    /**
     * The {@link StowbaseObject}
     */
    @JsonProperty
    public StowbaseObject object;

    /**
     * Empty constructor
     */
    public ReferenceObject() {
        // Do nothing
    }

    /**
     * Construct object that references to itself.
     * 
     * @param object
     */
    public ReferenceObject(final StowbaseObject object) {
        this(object.getReference(), object);
    }

    /**
     * Set values constructor
     * 
     * @param reference
     * @param object
     */
    public ReferenceObject(final Reference reference, final StowbaseObject object) {
        this.reference = reference;
        this.object = object;
    }

    /**
     * For deserialiseing into StowbaseHashMap
     * 
     * @param stowbaseHashMap
     */
    @JsonSetter("object")
    void jsonSetObject(final StowbaseHashMap stowbaseHashMap) {
        this.object = stowbaseHashMap;
    }

    @Override
    public String toString() {
        return "ReferenceObject(" + reference.toFullPath() + ":" + object + ")";
    }

}
