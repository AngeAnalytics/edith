package dk.ange.edith.segments;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test {@link Segment}
 */
public class SegmentTest {

    /** Test an IFTSAI file */
    @Test
    public void testSegment() {

        final Segment.Builder builder = new Segment.Builder("UNH");
        builder.addValue("123456789");
        builder.nextDataElement();
        builder.addValue("IFTSAI");
        builder.addValue("D");
        builder.addValue("99B");
        builder.addValue("UN");
        final Segment unh = builder.build();

        Assert.assertEquals("Segment(\"UNH\", [[\"123456789\"], [\"IFTSAI\", \"D\", \"99B\", \"UN\"]])", unh.toString());

        Assert.assertEquals("UNH", unh.getTag());
        Assert.assertNotEquals("WRONG", unh.getTag());

        Assert.assertEquals(Value.valueOf("123456789"), unh.getData(1, 1));
        Assert.assertNotEquals(Value.valueOf("WRONG"), unh.getData(1, 1));
        Assert.assertEquals(Value.valueOf("IFTSAI"), unh.getData(2, 1));
        Assert.assertEquals(Value.valueOf("D"), unh.getData(2, 2));
        Assert.assertEquals(Value.valueOf("99B"), unh.getData(2, 3));
        Assert.assertEquals(Value.valueOf("UN"), unh.getData(2, 4));

        // the following two composites do not exist in this segment
        Assert.assertEquals(false, unh.getData(2, 5).isPresent());
        Assert.assertEquals(false, unh.getData(3, 1).isPresent());
    }

}