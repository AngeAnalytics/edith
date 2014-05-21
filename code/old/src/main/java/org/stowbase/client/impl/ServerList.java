package org.stowbase.client.impl;

import org.stowbase.client.Reference;

/**
 * List of servers, used for getting serverUri given the serverUniqueId and for writing full path of an
 * {@link AbsoluteReference}.
 */
public class ServerList {

    /*
     * This implementation only knows one server.
     */

    private final String onlyServerUniqueId;

    private final String onlyServerUri;

    /**
     * Create a server list that knows a single server.
     * 
     * @param onlyServerUniqueId
     * @param onlyServerUri
     */
    public ServerList(final String onlyServerUniqueId, final String onlyServerUri) {
        this.onlyServerUniqueId = onlyServerUniqueId;
        this.onlyServerUri = onlyServerUri;
    }

    private String getServerUri(final String serverUniqueId) {
        if (serverUniqueId == null) {
            return null;
        }
        if (onlyServerUniqueId.equals(serverUniqueId)) {
            return onlyServerUri;
        }
        throw new RuntimeException("Unknown server, " + serverUniqueId);
    }

    /**
     * @param reference
     * @return The serverUniqueId
     */
    public String getServerUri(final Reference reference) {
        return getServerUri(reference.getServerUniqueId());
    }

    /**
     * @param reference
     * @return The full path to the reference
     */
    public String toFullPath(final Reference reference) {
        final String serverUniqueId = reference.getServerUniqueId();
        final String serverUri = getServerUri(serverUniqueId);
        // Do not use String.format() here, it is too slow
        final StringBuilder stringBuilder = new StringBuilder();
        if (serverUri != null) {
            stringBuilder.append(serverUri);
        }
        stringBuilder.append("/object/");
        stringBuilder.append(reference.getObjectId());
        if (serverUniqueId != null) {
            stringBuilder.append("?sui=");
            stringBuilder.append(serverUniqueId);
        }
        return stringBuilder.toString();
    }

}
