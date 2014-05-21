package org.stowbase.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Access the data from stowbase.properties
 */
public class StowbaseProperties {

    private static final Properties PROPERTIES = staticProperties();

    private static Properties staticProperties() throws ExceptionInInitializerError {
        final Properties properties = new Properties();
        try {
            final InputStream inputStream = staticStream();
            properties.load(inputStream);
            inputStream.close();
        } catch (final IOException e) {
            throw new ExceptionInInitializerError(e);
        }
        return new Properties(properties);
    }

    private static InputStream staticStream() throws IOException {
        // Find config using system property
        final String fileName = System.getProperty("org.stowbase.properties");
        if (fileName != null) {
            return new FileInputStream(fileName);
        }
        // Find config in class path
        final URL url = Thread.currentThread().getContextClassLoader().getResource("stowbase.properties");
        if (url != null) {
            return url.openStream();
        }
        // Didn't find it
        throw new RuntimeException("System property 'org.stowbase.properties' has not been set,"
                + " and could not find stowbase.properties in classpath");
    }

    /**
     * @return value of stowbase.login.user or null if undefined
     */
    public static String getLoginUser() {
        return PROPERTIES.getProperty("stowbase.login.user", null);
    }

    /**
     * @return value of stowbase.login.password or null if undefined
     */
    public static String getLoginPassword() {
        return PROPERTIES.getProperty("stowbase.login.password", null);
    }

    /**
     * @return the URI for the stowbase server
     */
    public static String getServerUri() {
        return PROPERTIES.getProperty("stowbase.server.uri", //
                "http://localhost:" + PROPERTIES.getProperty("stowbase.server.port", "9090"));
    }

    /**
     * @param key
     *            the property key
     * @return the value in this property list with the specified key value
     */
    public static String get(final String key) {
        return PROPERTIES.getProperty(key);
    }

}
