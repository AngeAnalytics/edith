package org.stowbase.client.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

/**
 * Wrapper for our usage of the Jackson library. Note that this is not a complete facade hiding the chosen library. This
 * would not be practical because of the type erasure discussed below, and because the "JsonProperty" annotations belong
 * naturally at the place where the encoded types are defined.
 */
public class JSON {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JSON.class);

    /*
     * Using a single static Mapper as it is expensive to create and thread safe to use
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        // Turn of auto detection of how to map objects
        MAPPER.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, false);
        MAPPER.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
        // Indent output and insert line feeds
        MAPPER.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    }

    /**
     * Read and parse the JSON input from {@code reader} and map it to Java objects
     * 
     * @param <T>
     *            Java type to parse the JSON data into
     * @param reader
     *            Reader to read JSON data from
     * @param t
     *            Class object for type T
     * @return The constructed Java objects. WARNING: Returns null if the given Reader has no data.
     * @throws JsonParseException
     *             if the JSON data does not parse, most likely caused by syntax error in the input JSON
     * @throws JsonMappingException
     *             if it is not possible to map the JSON data into Java objects, most likely caused by JSON that does
     *             not map to the expected type T
     * @throws IOException
     *             if there is an error reading the data from the Reader (Notice that the Json exceptions inherit from
     *             IOException)
     */
    public static <T> T readValue(final Reader reader, final Class<T> t) throws JsonParseException,
            JsonMappingException, IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.mark(1);
        if (bufferedReader.read() == -1) {
            // Don't throw, but log the stack trace in case the null value escapes to unprepared code
            log.debug("No data in reader", new NullPointerException());
            return null; // Return null on empty input
        }
        bufferedReader.reset();
        return MAPPER.readValue(bufferedReader, t);
    }

    /**
     * Read and parse the JSON input from {@code reader} and map it to Java objects
     * 
     * @param <T>
     *            Java type to parse the JSON data into
     * @param reader
     *            Reader to read JSON data from
     * @param t
     *            TypeReference object for type T, this is used for specifying generic type info in a way that will not
     *            cause type erasure. An example: <code>new TypeReference&lt;List&lt;MyObject>>(){}</code>, for more
     *            info see {@link TypeReference}.
     * @return The constructed Java objects. WARNING: Returns null if the given Reader has no data.
     * @throws JsonParseException
     *             if the JSON data does not parse, most likely caused by syntax error in the input JSON
     * @throws JsonMappingException
     *             if it is not possible to map the JSON data into Java objects, most likely caused by JSON that does
     *             not map to the expected type T
     * @throws IOException
     *             if there is an error reading the data from the Reader
     */
    @SuppressWarnings("unchecked")
    public static <T> T readValue(final Reader reader, final TypeReference<T> t) throws JsonParseException,
            JsonMappingException, IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.mark(1);
        if (bufferedReader.read() == -1) {
            // Don't throw, but log the stack trace in case the null value escapes to unprepared code
            log.debug("No data in reader", new NullPointerException());
            return null; // Return null on empty input
        }
        bufferedReader.reset();
        // Eclipse can compile without the cast, Suns javac can not:
        return (T) MAPPER.readValue(bufferedReader, t);
    }

    /**
     * Transform Java objects to JSON and write it to {@code writer}.
     * 
     * @param object
     *            Java object to write
     * @param writer
     *            Writer to write the JSON to
     * @throws JsonMappingException
     *             if the Java object can not be transformed to JSON
     * @throws JsonGenerationException
     *             if there is problems when generating the JSON, that should not happen
     * @throws IOException
     *             if there is problems writing to the Writer
     */
    public static void writeValue(final Object object, final Writer writer) throws JsonGenerationException,
            JsonMappingException, IOException {
        MAPPER.writeValue(new ProtectWriter(writer), object);
    }

}
