package org.stowbase.client.remote;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.stowbase.client.References;
import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.impl.ReferenceObject;
import org.stowbase.client.impl.ReferencedStowbaseHashMap;

/**
 * Create a Bundle object that will collect stowbase objects and flush them to the server many at a time.
 */
public final class Bundle implements StowbaseObjectFactory {

    private final Endpoint endpoint;

    private final List<ReferencedStowbaseHashMap> list = new ArrayList<ReferencedStowbaseHashMap>();

    private final int autoFlush = 1000;

    /**
     * @param endpoint
     */
    public Bundle(final Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public StowbaseObject create(final String group) {
        if (list.size() >= autoFlush) {
            flush();
        }
        final ReferencedStowbaseHashMap object = new ReferencedStowbaseHashMap();
        object.put("_group", group);
        list.add(object);
        return object;
    }

    @Override
    public void flush() {
        final ServerOutput serverOutput = endpoint.send("/bundle", "POST", new ServerInput(list), ServerOutput.class);
        if (list.size() != serverOutput.size()) {
            throw new RuntimeException(String.format("Input (%d) and output (%d) has different lengths", list.size(),
                    serverOutput.size()));
        }
        for (int i = 0; i < serverOutput.size(); ++i) {
            list.get(i).getReference().setReference(serverOutput.get(i));
        }
        list.clear();
        // return serverOutput; we return this in Python, is there a need for that in Java?
    }

    /**
     * The input to the server in a bundle command
     */
    public static class ServerInput {
        /**
         * The data send in the bundle
         */
        @JsonProperty
        public final List<ReferenceObject> data;

        /**
         * Empty constructor
         */
        public ServerInput() {
            data = new ArrayList<ReferenceObject>();
        }

        /**
         * @param list
         */
        public ServerInput(final List<ReferencedStowbaseHashMap> list) {
            data = new ArrayList<ReferenceObject>(list.size());
            for (final ReferencedStowbaseHashMap object : list) {
                data.add(new ReferenceObject(object));
            }
        }
    }

    /**
     * The output from the server to a bundle command
     */
    public static class ServerOutput extends References {
        /**
         * Empty constructor
         */
        public ServerOutput() {
            // Do nothing
        }

        /**
         * Create an empty list prepared for size elements
         * 
         * @param size
         */
        public ServerOutput(final int size) {
            super(size);
        }
    }

}
