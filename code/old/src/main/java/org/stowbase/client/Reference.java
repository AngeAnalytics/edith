package org.stowbase.client;

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.stowbase.client.impl.AbsoluteReference;

/**
 * Reference to a stowbase object, either on this server or on another. The server is known by the serverUniqueId.
 * <p>
 * The URI format for a reference supports information about host+port of the instance where the reference was found,
 * but this is not implemented in this Java client.
 * <p>
 * Format of uri: "http://frank.ange.dk:9090/object/10?sui=1A2B3C"
 */
@JsonDeserialize(as = AbsoluteReference.class)
public interface Reference {

    /**
     * @return the path, e.g. "http://frank.ange.dk:9090". Can be null.
     */
    public abstract String getServerUri();

    /**
     * @return the id of the object, e.g. "10"
     */
    public abstract String getObjectId();

    /**
     * @return the serverUniqueId, e.g. "1A2B3C". Can be null, can not be "".
     */
    public abstract String getServerUniqueId();

    /**
     * @return the reference as a full string, e.g. "http://frank.ange.dk:9090/object/10?sui=1A2B3C"
     */
    @JsonValue
    public abstract String toFullPath();

}
