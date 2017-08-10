package dk.ange.edith.segments;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test {@link Value}
 */
public class ValueTest {

    /** Test an edith parser Value class */
    @Test
    public void testValue01() {
        final Value valueNumber = Value.valueOf("123456789");
        Assert.assertTrue(valueNumber.isPresent());
        Assert.assertEquals("123456789", valueNumber.asString(""));
        Assert.assertEquals("asDouble() " + valueNumber, 123456789, valueNumber.asDouble(0), 0.1);
    }

    /** Test an edith parser Value class */
    @Test(expected=java.lang.NumberFormatException.class)
    public void testValue02() {
        final Value valueString = Value.valueOf("IFTSAI");
        Assert.assertTrue(valueString.isPresent());
        Assert.assertEquals("IFTSAI", valueString.asString(""));
        valueString.asDouble(0); // throws
    }

    /** Test an edith parser Value class */
    @Test
    public void testValue03() {
        final Value valueEmptyString = Value.valueOf("");
        Assert.assertTrue(!valueEmptyString.isPresent());
        Assert.assertNull(valueEmptyString.asString(null));
        // empty string is treated as non-present, does not throw
        Assert.assertEquals("asDouble() " + valueEmptyString, 0, valueEmptyString.asDouble(0), 0.1);
    }

    /** Test an edith parser Value class */
    @Test
    public void testValue04() {
        final Value valueNull = Value.valueOf(null);
        Assert.assertTrue(!valueNull.isPresent());
        Assert.assertNull(valueNull.asString(null));
        Assert.assertEquals("asDouble() " + valueNull, 0, valueNull.asDouble(0), 0.1);
    }

}
