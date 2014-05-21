package org.stowbase.client.impl;

import org.codehaus.jackson.annotate.JsonValue;
import org.stowbase.client.BadFormatException;
import org.stowbase.client.Reference;

/**
 * Reference stored in this object.
 * <p>
 * Does not use serverUri in the equals() and hashCode().
 */
public final class AbsoluteReference implements Reference {

    private static ServerList serverList = new ServerList(null, null);

    private final String serverUniqueId;

    private final String objectId;

    /**
     * @param serverUniqueId
     * @param objectId
     * @throws BadFormatException
     *             if the format if the input string is bad
     */
    public AbsoluteReference(final String serverUniqueId, final String objectId) {
        this.serverUniqueId = serverUniqueId;
        this.objectId = objectId;
        validate();
    }

    /**
     * Create reference from e.g. "http://frank.ange.dk:9090/object/10?sui=1A2B3C"
     * 
     * Use "/object/" and "?sui=" to split the String.
     * 
     * @param uriString
     * @throws BadFormatException
     *             if the format if the input string is bad
     */
    public AbsoluteReference(final String uriString) {
        // Do not use "new URI()" to parse uriString, it is slow.
        final int indexOfObject = uriString.lastIndexOf("/object/");
        if (indexOfObject == -1) {
            throw new BadFormatException("Missing '/object/' in " + uriString);
        }
        final String withoutBaseUrl = uriString.substring(indexOfObject + 8);
        // Find out if there is "?sui=" in the string
        final int indexOfSui = withoutBaseUrl.indexOf("?sui=");
        if (indexOfSui == -1) {
            // Set this.serverUniqueId
            this.serverUniqueId = null;
            // Set this.objectId
            this.objectId = withoutBaseUrl;
        } else {
            // Set this.serverUniqueId
            this.serverUniqueId = withoutBaseUrl.substring(indexOfSui + 5);
            // Set this.objectId
            this.objectId = withoutBaseUrl.substring(0, indexOfSui);
        }
        // Validate all
        validate();
    }

    /**
     * Validate this new object
     * 
     * @throws BadFormatException
     *             if the format if the input string is bad
     */
    private void validate() {
        // objectId
        if (objectId == null) {
            throw new BadFormatException("Null objectId is not allowed");
        }
        if (objectId.length() == 0) {
            throw new BadFormatException("objectId.length() == 0");
        }
        // serverUniqueId
        if ("".equals(serverUniqueId)) {
            throw new BadFormatException("\"\" server ID is not allowed - use null if the data is unavailable");
        }
    }

    /**
     * @param serverList
     */
    public static void setGlobalServerList(final ServerList serverList) {
        AbsoluteReference.serverList = serverList;
    }

    @Override
    public String getServerUri() {
        return serverList.getServerUri(this);
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public String getServerUniqueId() {
        return serverUniqueId;
    }

    @Override
    @JsonValue
    public String toFullPath() {
        return serverList.toFullPath(this);
    }

    @Override
    public String toString() {
        return "Reference(" + serverUniqueId + "," + objectId + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
        result = prime * result + ((serverUniqueId == null) ? 0 : serverUniqueId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        // vvv Notice, this is a trick to make WrappedReference compare equal to the inner item
        if (obj instanceof WrappedReference) {
            final WrappedReference wrapped = (WrappedReference) obj;
            return wrapped.equals(this);
        }
        // ^^^ Notice, this is a trick to make WrappedReference compare equal to the inner item
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbsoluteReference other = (AbsoluteReference) obj;
        if (objectId == null) {
            if (other.objectId != null) {
                return false;
            }
        } else if (!objectId.equals(other.objectId)) {
            return false;
        }
        if (serverUniqueId == null) {
            if (other.serverUniqueId != null) {
                return false;
            }
        } else if (!serverUniqueId.equals(other.serverUniqueId)) {
            return false;
        }
        return true;
    }

}
