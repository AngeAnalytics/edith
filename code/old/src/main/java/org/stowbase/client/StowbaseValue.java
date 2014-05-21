package org.stowbase.client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * Final Object that can contain only the known stowbase Object types. The types are enumerated below in {@link Type}
 */
public class StowbaseValue {

    private final Object data;

    /**
     * Create StowbaseValue that contains a double.
     * 
     * @param data
     *            data for the StowbaseValue
     */
    public StowbaseValue(final double data) {
        this.data = data;
    }

    /**
     * Create StowbaseValue that contains a double.
     * 
     * @param data
     *            data for the StowbaseValue
     */
    public StowbaseValue(final Double data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        this.data = data;
    }

    /**
     * Gets the double in the object.
     * 
     * @return The value of the object as a double
     * @throws ClassCastException
     *             if the object is not a double
     */
    public double getAsDouble() {
        return (Double) data;
    }

    /**
     * Create StowbaseValue that contains a String.
     * 
     * @param data
     *            data for the StowbaseValue
     * @throws NullPointerException
     *             if data is null
     */
    public StowbaseValue(final String data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        this.data = data;
    }

    /**
     * Gets the String in the object.
     * 
     * @return The value of the object as a String
     * @throws ClassCastException
     *             if the object is not a String
     */
    public String getAsString() {
        return (String) data;
    }

    /**
     * Create StowbaseValue that contains a boolean.
     * 
     * @param data
     *            data for the StowbaseValue
     */
    public StowbaseValue(final boolean data) {
        this.data = data;
    }

    /**
     * Create StowbaseValue that contains a boolean.
     * 
     * @param data
     *            data for the StowbaseValue
     */
    public StowbaseValue(final Boolean data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        this.data = data;
    }

    /**
     * Gets the boolean in the object.
     * 
     * @return The value of the object as a boolean
     * @throws ClassCastException
     *             if the object is not a boolean
     */
    public boolean getAsBoolean() {
        return (Boolean) data;
    }

    /**
     * Create StowbaseValue that contains a timestamp.
     * 
     * @param data
     *            data for the StowbaseValue
     * @throws NullPointerException
     *             if data is null
     */
    public StowbaseValue(final Date data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        this.data = data;
    }

    /**
     * Gets the timestamp in the object.
     * 
     * @return The value of the object as a Date
     * @throws ClassCastException
     *             if the object is not a Date
     */
    public Date getAsTimestamp() {
        return (Date) data;
    }

    /**
     * @param data
     */
    public StowbaseValue(final References data) {
        this.data = data;
    }

    /**
     * @param data
     */
    public StowbaseValue(final Reference data) {
        this.data = new References(Arrays.asList(data));
    }

    /**
     * Gets the References in the object.
     * 
     * @return The value of the object as a References
     * @throws ClassCastException
     *             if the object is not a References
     */
    public References getAsReferences() {
        return (References) data;
    }

    /**
     * Create StowbaseValue that contains a List<String>.
     * 
     * @param data
     *            data for the StowbaseValue
     * @throws NullPointerException
     *             if data is null
     * @throws ClassCastException
     *             if any of the Objects in the List is not of type String
     */
    public StowbaseValue(final List<String> data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        final List<String> stringList = new ArrayList<String>(data.size());
        for (final Object object : data) {
            stringList.add((String) object);
        }
        this.data = stringList;
    }

    /**
     * Create StowbaseValue that contains a List<String>.
     * 
     * @param data
     *            data for the StowbaseValue
     * @throws NullPointerException
     *             if data is null
     * @throws ClassCastException
     *             if any of the Objects in the List is not of type String
     */
    public StowbaseValue(final String[] data) {
        if (data == null) {
            throw new NullPointerException("data == null");
        }
        final List<String> stringList = new ArrayList<String>(data.length);
        for (final Object object : data) {
            stringList.add((String) object);
        }
        this.data = Collections.unmodifiableList(stringList);
    }

    /**
     * Gets the List<String> in the object.
     * 
     * @return The value of the object as a List<String>
     * @throws ClassCastException
     *             if the object is not a List<String>
     */
    @SuppressWarnings("unchecked")
    public List<String> getAsStringList() {
        return (List<String>) data;
    }

    /**
     * @return Returns the type of the contained Object
     */
    public Type getType() {
        if (data instanceof Double) {
            return Type.DOUBLE;
        }
        if (data instanceof String) {
            return Type.STRING;
        }
        if (data instanceof Boolean) {
            return Type.BOOLEAN;
        }
        if (data instanceof Date) {
            return Type.TIMESTAMP;
        }
        if (data instanceof References) {
            return Type.REFERENCES;
        }
        if (data instanceof List<?>) {
            return Type.STRING_LIST;
        }
        throw new RuntimeException("Unknown type " + data.getClass() + ", should never happen");
    }

    /**
     * The supported types of values in StowbaseValue
     */
    public enum Type {
        /**
         * Corresponds to double
         */
        DOUBLE,
        /**
         * Corresponds to {@link String}
         */
        STRING,
        /**
         * Corresponds to boolean
         */
        BOOLEAN,
        /**
         * Corresponds to {@link Date}
         */
        TIMESTAMP,
        /**
         * Corresponds to {@link References}
         */
        REFERENCES,
        /**
         * Corresponds to {@link List} of {@link String}s
         */
        STRING_LIST,
    }

    @Override
    public String toString() {
        return "StowbaseValue[data=" + getType() + "/" + data + "]";
    }

    /**
     * @return data to dump as JSON
     */
    @JsonValue
    public Object jsonValue() {
        if (data instanceof Date) {
            final Date date = (Date) data;
            final Map<String, String> jsonObject = new LinkedHashMap<String, String>(2);
            jsonObject.put("type", "timestamp");
            jsonObject.put("timestamp", dateFormat().format(date));
            return jsonObject;
        }
        if (data instanceof List<?> && !(data instanceof References)) {
            final List<?> dataList = (List<?>) data;
            final Map<String, Object> jsonList = new LinkedHashMap<String, Object>(2);
            jsonList.put("type", "stringlist");
            jsonList.put("stringlist", dataList);
            return jsonList;
        }
        return data;
    }

    /**
     * The DateFormat is SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") configured to UTC.
     * 
     * @return DateFormat used in stowbase.org
     */
    public static DateFormat dateFormat() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    /**
     * Construct a StowbaseValue based on JSON input
     * 
     * @param object
     *            input data
     * @return a new StowbaseValue
     */
    @JsonCreator
    @SuppressWarnings("unchecked")
    public static StowbaseValue valueOfJson(final Object object) {
        if (object instanceof String) {
            return new StowbaseValue((String) object);
        }
        if (object instanceof Number) {
            if (object instanceof Double) {
                return new StowbaseValue((Double) object);
            } else {
                return new StowbaseValue(((Number) object).doubleValue());
            }
        }
        if (object instanceof Boolean) {
            return new StowbaseValue((Boolean) object);
        }
        if (object instanceof Date) {
            throw new RuntimeException("Date can not be input from JSON");
        }
        if (object instanceof List<?>) {
            final List<String> list = niceCast(object, "object", List.class);
            return new StowbaseValue(References.fromFullPathList(list));
        }
        if (object instanceof Map<?, ?>) {
            final Map<String, Object> jsonObject = niceCast(object, "object", Map.class);
            if (jsonObject.size() != 2) {
                throw new RuntimeException("JSON object must have at exactly 2 elements");
            }
            final String type = niceCast(jsonObject.get("type"), "type", String.class);
            if ("timestamp".equals(type)) {
                final String timestamp = niceCast(jsonObject.get("timestamp"), "timestamp", String.class);
                try {
                    return new StowbaseValue(dateFormat().parse(timestamp));
                } catch (final ParseException e) {
                    throw new RuntimeException("Could not parse '" + timestamp + "'", e);
                }
            } else if ("stringlist".equals(type)) {
                final List<String> list = niceCast(jsonObject.get("stringlist"), "stringlist", List.class);
                return new StowbaseValue(list);
            } else {
                throw new RuntimeException("Invalid type, '" + type + "'");
            }
        }
        throw new RuntimeException("Data of unexpected type from json, type=" + object.getClass() + " data='" + object
                + "'");
    }

    private static <T> T niceCast(final Object object, final String name, final Class<T> clazz) {
        if (!clazz.isInstance(object)) {
            throw new RuntimeException(name + " element must be " + clazz);
        }
        return clazz.cast(object);
    }

    @Override
    public int hashCode() {
        return data == null ? 0 : data.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StowbaseValue other = (StowbaseValue) obj;
        if (data == null && other.data != null) {
            return false;
        }
        return data.equals(other.data);
    }

}
