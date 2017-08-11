package dk.ange.edith.segments;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * Test parsing and extracting data from an ANSI X12 edi file
 */
public class X12SegmentReaderTest {

    /**
     * Test the message type (ST), Message Footer (GE)
     *
     * @throws IOException
     */
    @Test
    public void testGettingX12Data() throws IOException {
        try (final InputStream stream = X12SegmentReaderTest.class.getResource("x12_300.edi").openStream()) {
            final X12SegmentReader reader = new X12SegmentReader(stream);
            int count = 0;
            while (reader.hasNext()) {
                count += 1;
                final Segment seg = reader.next();
                if ("ST".equals(seg.getTag())) {
                    assertEquals("300", seg.getData(1, 1).asString());
                }
                if ("SE".equals(seg.getTag())) {
                    assertEquals("82", seg.getData(1, 1).asString());
                    assertEquals("0001", seg.getData(2, 1).asString());
                }
            }
            // Check parsed all segments. (-4 because message doesn't count ISA, SE, GE & IEA Segments)
            assertEquals(82, count - 4);
        }
    }

}
