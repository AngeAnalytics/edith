package org.stowbase.client.objects;

import static org.stowbase.client.objects.Units.FOOT;

import java.net.URI;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseURI;
import org.stowbase.client.StowbaseURN;
import org.stowbase.client.StowbaseURN.FallbackCommand;
import org.stowbase.client.StowbaseURN.ThrowFallbackCommand;

/**
 * Represents a single container on a trip from the original arrival to the final destination.
 */
public class Container extends Cargo implements StowbaseObject {

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected Container(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Creates a new Container object backed by the given factory.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static Container create(final StowbaseObjectFactory mapFactory) {
        return new Container(createInner(mapFactory));
    }

    /**
     * Utility method for creating a named Container object.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @param containerId
     * @return The created object
     */
    public static Container createWithContainerId(final StowbaseObjectFactory mapFactory, final String containerId) {
        final Container container = create(mapFactory);
        container.setContainerId(containerId);
        return container;
    }

    /**
     * Sets the container id of the container, it will wrap the container id in urn.
     * 
     * @param containerId
     */
    public void setContainerId(final String containerId) {
        if (containerId == null) {
            throw new NullPointerException("containerId == null");
        }
        final String key = "containerId";
        final String keyNonStandard = key + "NonStandard";
        final FallbackCommand nonStandardCommand = new ThrowFallbackCommand() {
            @Override
            public URI call(final String string) {
                inner.put(keyNonStandard, string);
                return StowbaseURN.NON_STANDARD_CONTAINER_ID_URN;
            }
        };
        // inner.remove(keyNonStandard); not supported in RemoteObject yet
        inner.put(key, StowbaseURN.container(containerId, nonStandardCommand).toString());
    }

    /**
     * Sets the length to 20'
     */
    public void setLength20() {
        inner.put("lengthInM", 20 * FOOT);
        inner.put("lengthName", StowbaseURI.forFootLength("20").toString());
    }

    /**
     * Sets the length to 40'
     */
    public void setLength40() {
        inner.put("lengthInM", 40 * FOOT);
        inner.put("lengthName", StowbaseURI.forFootLength("40").toString());
    }

    /**
     * Sets the length to 45'
     */
    public void setLength45() {
        inner.put("lengthInM", 45 * FOOT);
        inner.put("lengthName", StowbaseURI.forFootLength("45").toString());
    }

    /**
     * Sets the height to 8½', the height of a normal container
     */
    public void setHeightDC() {
        inner.put("heightInM", 8.5 * FOOT);
        inner.put("heightName", StowbaseURN.height("DC", null).toString());
    }

    /**
     * Sets the height to 9½', the height of a high cube container
     */
    public void setHeightHC() {
        inner.put("heightInM", 9.5 * FOOT);
        inner.put("heightName", StowbaseURN.height("HC", null).toString());
    }

    /**
     * Updates the default field.
     * 
     * @param isLiveReefer
     *            True if this is a reefer container that currently containers cargo that needs cooling.
     */
    public void setLiveReefer(final boolean isLiveReefer) {
        inner.put("isLiveReefer", isLiveReefer);
    }

    /**
     * Updates the default field.
     * 
     * @param isEmpty
     *            True if this is an empty container.
     */
    public void setIsEmpty(final boolean isEmpty) {
        inner.put("isEmpty", isEmpty);
    }

    /**
     * @param overHeight the amount this container is overhigh.
     */
    public void setOverHeight(final double overHeight) {
        inner.put("oogTopCm", overHeight);
    }

    /**
     * @param overWide amount this container is overwide to the right.
     */
    public void setOverWideRight(final double overWide) {
        inner.put("oogRightCm", overWide);
    }

    /**
     * @param overWide amount this container is overwide to the left.
     */
    public void setOverWideLeft(final double overWide) {
        inner.put("oogLeftCm", overWide);
    }

    /**
     * @param overLong amount this container is overlong at the front.
     */
    public void setOverLongFront(final double overLong) {
        inner.put("oogFrontCm", overLong);
    }

    /**
     * @param overLong amount this container is overlong at the back.
     */
    public void setOverLongBack(final double overLong) {
        inner.put("oogBackCm", overLong);
    }

}
