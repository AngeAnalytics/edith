package org.stowbase.client.objects;

import java.util.List;

import org.stowbase.client.StowbaseObject;
import org.stowbase.client.StowbaseObjectFactory;

/**
 * Represents a function of two variables that is calculated using linear interpolation.
 */
public final class LinearInterpolation2d extends Function2d implements StowbaseObject {

    /**
     * Simple constructor
     * 
     * @param inner
     *            the {@link StowbaseObject} that holds the data
     */
    protected LinearInterpolation2d(final StowbaseObject inner) {
        super(inner);
        super.setFunctionType("interpolation2d");
        inner.put("interpolation", "linear");
        inner.put("extrapolation", "nearest");
    }

    /**
     * Creates a new Container object backed by the given factory.
     * 
     * @param mapFactory
     *            The created object will wrap an object created by this factory
     * @return The created object
     */
    public static LinearInterpolation2d create(final StowbaseObjectFactory mapFactory) {
        return new LinearInterpolation2d(createInner(mapFactory));
    }

    /**
     * First coordinate of the sample points.
     * 
     * @param samplePoints
     */
    public void setSamplePoints1(final List<Double> samplePoints) {
        inner.put("samplePoints1", doubleListToString(samplePoints));
    }

    /**
     * Second coordinate of the sample points.
     * 
     * @param samplePoints
     */
    public void setSamplePoints2(final List<Double> samplePoints) {
        inner.put("samplePoints2", doubleListToString(samplePoints));
    }

    /**
     * The sample data for the cartesian product of samplePoints1 and samplePoints2.
     * 
     * @param sampleData
     */
    public void setSampleData(final List<Double> sampleData) {
        inner.put("sampleData", doubleListToString(sampleData));
    }

    @Override
    public void setFunctionType(final String functionType) {
        throw new UnsupportedOperationException("You can not change the function type of "
                + LinearInterpolation2d.class.getSimpleName());
    }

    private static String doubleListToString(final List<Double> samplePoints) {
        final StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (final Double point : samplePoints) {
            if (first) {
                first = false;
            } else {
                stringBuilder.append(";");
            }
            stringBuilder.append(point);
        }
        return stringBuilder.toString();
    }

}
