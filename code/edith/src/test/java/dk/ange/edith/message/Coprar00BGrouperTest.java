package dk.ange.edith.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.junit.Assert;
import org.junit.Test;

import dk.ange.edith.message.Coprar00BGrouper;
import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;
import dk.ange.edith.dom.Group;


/**
 * Test
 */
public class Coprar00BGrouperTest {

    /**
     * Test Coprar00B grouping
     * 
     * @throws IOException
     */
    @Test
    public void testCoprar00BGrouper01() throws IOException {
        try (final InputStream stream = getResourceAsStream("coprar00B/BK2COPRARYL74_1420_W_ESALG_X_5110156.edi")) {
            List<Group> messages = readAndGroupCoprar00BStream(stream);
            Assert.assertEquals(1, messages.size());

            Assert.assertEquals(1, messages.get(0).getGroupList(1).size());
            Assert.assertNotNull(messages.get(0).getGroupList(1).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("TDT"));
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegment("LOC"));

            Assert.assertEquals(2, messages.get(0).getGroupList(4).size());
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(0).getSegment("NAD"));
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(1).getSegment("NAD"));

            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(0).getGroupList(5).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(1).getGroupList(5).size());

            Assert.assertEquals(2, messages.get(0).getGroupList(6).size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(0).getSegment("EQD"));
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DTM").size());
            Assert.assertEquals(3, messages.get(0).getGroupList(6).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(7).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(10).size());
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(0).getSegmentList("NAD").size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(1).getSegment("EQD"));
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(1).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("DTM").size());
            Assert.assertEquals(3, messages.get(0).getGroupList(6).get(1).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(1).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(7).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(10).size());
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(1).getSegmentList("NAD").size());
        }
    }

    /**
     * Test Coprar00B grouping
     * 
     * @throws IOException
     */
    @Test
    public void testCoprar00BGrouper02() throws IOException {
        try (final InputStream stream = getResourceAsStream("coprar00B/COS_COPRAR_CARGA_AL_RAIN_004W.edi")) {
            List<Group> messages = readAndGroupCoprar00BStream(stream);
            Assert.assertEquals(1, messages.size());

            Assert.assertEquals(1, messages.get(0).getGroupList(1).size());
            Assert.assertNotNull(messages.get(0).getGroupList(1).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("TDT"));
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegment("LOC"));

            Assert.assertEquals(2, messages.get(0).getGroupList(4).size());
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(0).getSegment("NAD"));
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(1).getSegment("NAD"));

            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(0).getGroupList(5).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(1).getGroupList(5).size());

            Assert.assertEquals(3, messages.get(0).getGroupList(6).size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(0).getSegment("EQD"));
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DTM").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(7).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(10).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("NAD").size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(1).getSegment("EQD"));
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(1).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("DTM").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(1).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(1).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(7).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(1).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(1).getGroupList(10).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(1).getSegmentList("NAD").size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(0).getSegment("EQD"));
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(2).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("DTM").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(2).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(2).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getGroupList(7).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(2).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(2).getGroupList(10).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(2).getSegmentList("NAD").size());

        }
    }

    /** Test Coprar00B grouping 
     * @throws IOException */
    @Test
    public void testCoprar00BGrouper03() throws IOException {
        try (final InputStream stream = getResourceAsStream("coprar00B/EDIGTR868271.edi")) {
            List <Group> messages = readAndGroupCoprar00BStream(stream);
            Assert.assertEquals(1, messages.size());

            Assert.assertEquals(1, messages.get(0).getGroupList(1).size());
            Assert.assertNotNull(messages.get(0).getGroupList(1).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("TDT"));
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegment("LOC"));

            Assert.assertEquals(2, messages.get(0).getGroupList(4).size());
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(0).getSegment("NAD"));
            Assert.assertNotNull(messages.get(0).getGroupList(4).get(1).getSegment("NAD"));

            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(0).getGroupList(5).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(4).get(1).getGroupList(5).size());

            Assert.assertEquals(48, messages.get(0).getGroupList(6).size());

            Assert.assertNotNull(messages.get(0).getGroupList(6).get(0).getSegment("EQD"));
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DTM").size());
            Assert.assertEquals(3, messages.get(0).getGroupList(6).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(7).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(0).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getGroupList(10).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(0).getSegmentList("NAD").size());


            Assert.assertNotNull(messages.get(0).getGroupList(6).get(47).getSegment("EQD"));
            Assert.assertEquals(2, messages.get(0).getGroupList(6).get(47).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("EQN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("DTM").size());
            Assert.assertEquals(3, messages.get(0).getGroupList(6).get(47).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(47).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getGroupList(7).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(6).get(47).getSegmentList("SEL").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getGroupList(8).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("HAN").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getGroupList(10).size());
            Assert.assertEquals(0, messages.get(0).getGroupList(6).get(47).getSegmentList("NAD").size());
        }
    }

    private static final List <Group> readAndGroupCoprar00BStream(final InputStream stream) {
        Assert.assertNotNull(stream);
        final List <Group> messages = new ArrayList<>();

        final EdifactSegmentReader reader = new EdifactSegmentReader(stream);
        final Segment unb = reader.next();
        if (!unb.getTag().equals("UNB")) {
            throw new RuntimeException("Wrong input in file: " + unb);
        }

        while (reader.peek().getTag().equals("UNH")) {

            final Segment unh = reader.peek();
            if (!unh.getTag().equals("UNH") //
                    || !unh.getData(2, 1).asString("").equals("COPRAR") //
                    || !unh.getData(2, 2).asString("").equals("D") //
                    || !unh.getData(2, 3).asString("").equals("00B") //
                    || !unh.getData(2, 4).asString("").equals("UN") ) {
                throw new RuntimeException("Expected segment UNH+...+COPRAR:D:00B:UN:', got: " + unh.print());
            }

            final Coprar00BGrouper coprarGrouper = new Coprar00BGrouper();
            final Group coprar = coprarGrouper.group(reader);
            messages.add(coprar);

        }

        final Segment unz = reader.next();
        if (!unz.getTag().equals("UNZ")) {
            throw new RuntimeException("Expected segment UNZ+...+...', got: " + unz.print());
        }

        if (reader.hasNext()) {
            throw new RuntimeException("Expected EOF, found: " + reader.peek());
        }

        return messages;
    }

    private InputStream getResourceAsStream(final String name) {
        final Class<?> class_ = this.getClass();

        final InputStream stream = class_.getResourceAsStream(name);
        if (stream != null) {
            return stream;
        }

        final InputStream gzStream = class_.getResourceAsStream(name + ".gz");
        if (gzStream == null) {
            throw new RuntimeException("Resource '" + name + "' not found, neither directly or compressed");
        }

        try {
            return new GZIPInputStream(gzStream);
        } catch (final IOException e) {
            throw new RuntimeException("Problem decompressing resource '" + name + "'", e);
        }
    }
}
