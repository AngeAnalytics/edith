package dk.ange.edith.segments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import dk.ange.edith.util.EdifactParseRuntimeException;
import org.junit.Test;

public class EdifactSegmentReaderTest {

    @Test
    public void test() {
        assertEquals(null, segment(""));
        assertParseError("+");
        assertParseError("TAG");
        assertParseError("TAG:");
        assertParseError("TAG+");
        assertEquals(new Segment.Builder("TAG").addValue("X").nextDataElement().addValue(null).build(),
                segment("TAG+X+:'"));
        assertEquals(Arrays.asList(new Segment.Builder("TAG").build()), segments("TAG+'"));
    }

    @Test
    public void testMore() {
        Segment segment = new Segment.Builder("TAG").build();
        assertEquals(Arrays.asList(segment), segments("TAG+'"));
        assertEquals(Arrays.asList(segment, segment), segments("TAG+'TAG+'"));
    }

    @Test
    public void testUna() {
        assertParseError("UNA");
        assertParseError("UNA'");
        assertParseError("UNA:");
        assertParseError("UNA:'");
        assertParseError("UNA:+");
        assertParseError("UNA:+'");
        assertParseError("UNA:+. +");
        assertEquals(null, segment("UNA:+. '"));
        assertEquals(null, segment("UNA:+, '")); // Also allow comma as decimal mark
    }

    @Test
    public void testReadyForValue() {
        assertEquals(new Segment.Builder("TAG").addValue("X").build(), segment("TAG+X:'"));
    }

    private void assertParseError(final String string) {
        try {
            final Segment segment = segment(string);
            fail("Expected EdifactParseRuntimeException when parsing '" + string + "', got " + segment);
        } catch (final EdifactParseRuntimeException e) {
            // Expect this
        }
    }

    private static Segment segment(final String string) {
        return reader(string).prefetch();
    }

    private static List<Segment> segments(final String string) {
        return Lists.newArrayList(reader(string));
    }

    private static EdifactSegmentReader reader(final String string) {
        return new EdifactSegmentReader(new ByteArrayInputStream(string.getBytes()));
    }

}
