package org.stowbase.client.objects;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;
import org.stowbase.client.impl.StowbaseObjectWrapped;

/**
 * Represents a function of two variables
 */
public abstract class Function2d extends StowbaseObjectWrapped implements StowbaseObject {

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected Function2d(final StowbaseObject inner) {
        super(inner);
    }

    /**
     * Makes the given factory create an object for the group "function2d".
     * 
     * @param mapFactory
     *            The returned object is created by this factory.
     * @return The created object
     */
    protected static StowbaseObject createInner(final StowbaseObjectFactory mapFactory) {
        return mapFactory.create("function2d");
    }

    /**
     * Name of first parameter.
     * 
     * @param input1
     */
    public void setInput1(final String input1) {
        inner.put("input1", input1);
    }

    /**
     * Name of second parameter.
     * 
     * @param input2
     */
    public void setInput2(final String input2) {
        inner.put("input2", input2);
    }

    /**
     * Name of output.
     * 
     * @param output
     */
    public void setOutput(final String output) {
        inner.put("output", output);
    }

    /**
     * Type of function. E.g. "interpolation2d".
     * 
     * @param functionType
     */
    public void setFunctionType(final String functionType) {
        inner.put("functionType", functionType);
    }

}
