package org.stowbase.client.remote;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.stowbase.client.Reference;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.StowbaseValue;
import org.stowbase.client.impl.AbsoluteReference;
import org.stowbase.client.impl.ServerList;
import org.stowbase.client.impl.StowbaseHashMap;
import org.stowbase.client.impl.StowbaseObjectUnsupported;

/**
 * A RemoteStowbase implements {@link StowbaseObjectFactory} by sending all data to a stowbase.org server.
 * 
 * This class defines all client-side interaction with a stowbase.org server.
 */
public class RemoteStowbase implements StowbaseObjectFactory {

    private static final String OBJECT_PATH = "/object";

    /** The object handling low-level interaction with the remote server */
    private final Endpoint endpoint;

    /**
     * Registers the given URL and contacts the Stowbase server to retrieve its serverUniqueId.
     * 
     * @param baseUrl
     *            Base URL for the stowbase server instance, e.g. "http://myhost:9090".
     */
    protected RemoteStowbase(final String baseUrl) {
        this.endpoint = new RemoteStowbaseEndpointCachingId(baseUrl);
    }

    /**
     * Factory method for creating a new RemoteStowbase.
     * 
     * @param baseUrl
     *            Base URL for the stowbase server instance, e.g. "http://myhost:9090".
     * @return A fresh RemoteStowbase to interact with the stowbase at the given URL.
     */
    public static RemoteStowbase forBaseUrl(final String baseUrl) {
        return new RemoteStowbase(baseUrl);
    }

    /**
     * Call {@link AbsoluteReference#setGlobalServerList(ServerList)}
     */
    public void configAbsoluteReference() {
        AbsoluteReference.setGlobalServerList(new ServerList(endpoint.getServerUniqueId(), endpoint.getServerUri()));
    }

    @Override
    public StowbaseObject create(final String group) {
        return new RemoteObject(group);
    }

    @Override
    public String toString() {
        return String.format("(%s: endpoint=%s)", this.getClass().getCanonicalName(), this.endpoint);
    }

    /**
     * {@link StowbaseObject} providing access to a remote object on a stowbase server instance.
     */
    private class RemoteObject extends StowbaseObjectUnsupported implements StowbaseObject {

        /**
         * Reference to this object, see {@link Reference}
         */
        private final Reference reference;

        /**
         * Create link to existing object on remote server
         * 
         * @param reference
         */
        private RemoteObject(final Reference reference) {
            this.reference = reference;
        }

        /**
         * Creates a new object on the remote server and sets its group to the given value.
         * 
         * @param group
         *            The group of the new object, e.g. "cargo".
         */
        private RemoteObject(final String group) {
            final StowbaseObject map = new StowbaseHashMap();
            map.put("_group", group);
            this.reference = new AbsoluteReference(endpoint.getServerUniqueId(), endpoint.send(OBJECT_PATH, "POST",
                    map, String.class).substring(8));
        }

        @Override
        public Reference getReference() {
            return reference;
        }

        @Override
        public StowbaseValue put(final String key, final StowbaseValue value) {
            final StowbaseValue stowbaseValue = map().get(key);
            RemoteStowbase.this.endpoint.send(OBJECT_PATH + "/" + getReference().getObjectId(), "PUT", Collections
                    .singletonMap(key, value), null);
            return stowbaseValue;
        }

        private StowbaseHashMap map() {
            final StowbaseHashMap map = RemoteStowbase.this.endpoint.send(OBJECT_PATH + "/"
                    + getReference().getObjectId(), "GET", null, StowbaseHashMap.class);
            map.keySet().removeAll(Arrays.asList("_baseUrl", "_objectId", "_serverUniqueId"));
            return map;
        }

        @Override
        public Set<Entry<String, StowbaseValue>> entrySet() {
            // TODO this is not correct as changes in the entry set should change data on server
            final StowbaseHashMap map = map();
            return map.entrySet();
        }

        @Override
        public StowbaseValue get(final Object key) {
            return map().get(key);
        }
    }

    @Override
    public void flush() {
        // Do nothing
    }

    /**
     * @return a Bundle connection
     */
    public Bundle bundle() {
        return new Bundle(endpoint);
    }

    /**
     * @param objectId
     * @return existing remote object
     */
    public StowbaseObject get(final String objectId) {
        final Reference reference = new AbsoluteReference(endpoint.getServerUniqueId(), objectId);
        return new RemoteObject(reference);
    }

}
