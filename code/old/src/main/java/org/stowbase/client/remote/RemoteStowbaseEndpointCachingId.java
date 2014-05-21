package org.stowbase.client.remote;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Represents the low-level HTTP interface towards one, specific stowbase. This class caches the serverUniqueId locally.
 */
class RemoteStowbaseEndpointCachingId implements Endpoint {

    private final String baseUrl;

    private final String serverUniqueId;

    /**
     * Registers the given URL as the stowbase to correspond with, then contacts the stowbase to obtain and cache its
     * serverUniqueId.
     * 
     * @param baseUrl
     *            The base URL of a stowbase server instance.
     */
    RemoteStowbaseEndpointCachingId(final String baseUrl) {
        this.baseUrl = baseUrl;
        this.serverUniqueId = unpackAboutResponse(send("/about", "GET", null, Object.class));
        // TODO: Consider moving this to RemoteStowbase
    }

    /**
     * @param response
     *            The response from an Aperitivo About-resource.
     * @return The responding server's serverUniqueId
     */
    @SuppressWarnings("unchecked")
    private String unpackAboutResponse(final Object response) {
        final Map<Object, Object> map = (Map<Object, Object>) response;
        return (String) map.get("serverUniqueId");
    }

    /**
     * Factory method.
     * 
     * @param baseUrl
     *            The base URL of a stowbase server instance.
     * @return An instance of this class.
     */
    static Endpoint fromBaseUrl(final String baseUrl) {
        return new RemoteStowbaseEndpointCachingId(baseUrl);
    }

    @Override
    public <T> T send(final String path, final String httpMethod, final Object bodyObject, final Class<T> class_) {
        try {
            final HttpURLConnection conn = (HttpURLConnection) new URL(baseUrl + path).openConnection();
            conn.setUseCaches(false);
            conn.setRequestMethod(httpMethod);
            if (bodyObject != null) {
                conn.setDoOutput(true);
                final Writer writer = new OutputStreamWriter(conn.getOutputStream());
                JSON.writeValue(bodyObject, writer);
                writer.close();
            }
            final int responseCode = conn.getResponseCode();
            if (!(responseCode == 200 || responseCode == 201)) {
                throw new RuntimeException("Bad responseCode=" + responseCode + ", " + httpMethod + " " + path);
            }
            // TODO Do something reasonable with the response code.
            if (conn.getContentLength() == 0) {
                return null;
            }
            final InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            final T result = JSON.readValue(reader, class_);
            reader.close();
            return result;
        } catch (final IOException e) {
            throw new StowbaseProtocolException(e);
        } catch (final RuntimeException e) {
            throw new StowbaseProtocolException(e);
        }
    }

    /**
     * @return the internally cached serverUniqueId that was obtained from the server as this object was initialized.
     */
    @Override
    public String getServerUniqueId() {
        return this.serverUniqueId;
    }

    @Override
    public String toString() {
        return String.format("(%s: id=%s url=%s)", this.getClass().getCanonicalName(), this.serverUniqueId,
                this.baseUrl);
    }

    @Override
    public String getServerUri() {
        return baseUrl;
    }

}
