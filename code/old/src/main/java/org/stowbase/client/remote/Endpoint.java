package org.stowbase.client.remote;

/**
 * Abstract interface of a remote stowbase server.
 */
public interface Endpoint {

    /**
     * Connects to the stowbase instance, sends the given body, and parses the response.
     * 
     * @param <T>
     *            Type of the return object
     * @param path
     *            The absolute path to connection to on the Stowbase server, e.g. "/move".
     * @param httpMethod
     *            Should be "GET", "PUT" OR "POST".
     * @param bodyObject
     *            The Object to send.
     * @param class_
     *            The type to return
     * @return The parsed response
     */
    public <T> T send(final String path, final String httpMethod, final Object bodyObject, final Class<T> class_);

    /**
     * @return An opaque key String that uniquely identifies the stowbase instance.
     */
    public String getServerUniqueId();

    /**
     * @return Base URL for the stowbase server instance, e.g. "http://myhost:9090".
     */
    public String getServerUri();

}
