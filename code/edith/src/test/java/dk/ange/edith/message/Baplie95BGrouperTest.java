package dk.ange.edith.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.junit.Assert;
import org.junit.Test;

import dk.ange.edith.dom.Group;
import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;


/**
 * Test
 */
public class Baplie95BGrouperTest {

    /**
     * Test Baplie95B grouping
     * BAPLIE-AAC1-S-AAC1_JUBL_1403_SIN.edi.gz
     * UNH+1820+BAPLIE:D:95B:UN:SMDG20'
     *
     * @throws IOException
     */
    @Test
    public void testBaplie95BGrouper01() throws IOException {

        try (final InputStream stream = getResourceAsStream("baplie95B/BAPLIE-AAC1-S-AAC1_JUBL_1403_SIN.edi")) {
            final List<Group> messages = readAndGroupBaplie95BStream(stream);
            Assert.assertEquals(1, messages.size());

            Assert.assertNotNull(messages.get(0).getSegment("BGM"));
            Assert.assertNotNull(messages.get(0).getSegment("DTM"));
            Assert.assertNull(messages.get(0).getSegment("RFF"));
            Assert.assertNull(messages.get(0).getSegment("NAD"));

            Assert.assertEquals(1, messages.get(0).getGroupList(1).size());
            Assert.assertNotNull(messages.get(0).getGroupList(1).get(0).getSegment("TDT"));
            Assert.assertEquals(2, messages.get(0).getGroupList(1).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(3, messages.get(0).getGroupList(1).get(0).getSegmentList("DTM").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(1).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(1).get(0).getSegmentList("FTX").size());

            Assert.assertEquals(2081, messages.get(0).getGroupList(2).size());

            /*
             * 1st container
             *
             * LOC+147+0590882::5'
             * FTX+ZZZ+++HL'
             * MEA+WT++KGM:4600'
             * LOC+9+SGSIN:139:6'
             * LOC+11+AUBNE:139:6'
             * RFF+BM:1'
             * EQD+CN+GATU0400027+2210+++5'
             * NAD+CA+HL:172:20'
             */
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getSegmentList("GID").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getSegmentList("GDS").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getSegmentList("FTX").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getSegmentList("TMP").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getSegmentList("RNG").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(2).get(0).getSegmentList("LOC", 2).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getSegmentList("RFF").size());

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegmentList("EQD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(0).getGroupList(3).get(0).getSegmentList("NAD").size());

            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(0).getGroupList(4).size());

            /*
             * 19th container
             *
             * LOC+147+0100314::5'
             * FTX+ZZZ+++HJ'
             * MEA+WT++KGM:9600'
             * LOC+9+SGSIN:139:6'
             * LOC+11+AUMEL:139:6'
             * RFF+BM:1'
             * EQD+CN+SESU6009948+4310+++5'
             * NAD+CA+HJS:172:20'
             * DGS+IMD+3+1263'
             */
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getSegmentList("LOC").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getSegmentList("GID").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getSegmentList("GDS").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getSegmentList("FTX").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getSegmentList("TMP").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getSegmentList("RNG").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(2).get(18).getSegmentList("LOC", 2).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getSegmentList("RFF").size());

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getGroupList(3).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getGroupList(3).get(0).getSegmentList("EQD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getGroupList(3).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getGroupList(3).get(0).getSegmentList("NAD").size());

            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getGroupList(4).size());
            Assert.assertEquals(1, messages.get(0).getGroupList(2).get(18).getGroupList(4).get(0).getSegmentList("DGS").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(2).get(18).getGroupList(4).get(0).getSegmentList("FTX").size());
        }
    }

    private static final List <Group> readAndGroupBaplie95BStream(final InputStream stream) {
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
                    || !unh.getData(2, 1).asString("").equals("BAPLIE") //
                    || !unh.getData(2, 2).asString("").equals("D") //
                    || !unh.getData(2, 3).asString("").equals("95B") //
                    || !unh.getData(2, 4).asString("").equals("UN") //
                    || !unh.getData(2, 5).asString("").equals("SMDG20")) {
                throw new RuntimeException("Expected segment UNH+...+BAPLIE:D:95B:UN:', got: " + unh.print());
            }

            final Baplie95BGrouper baplieGrouper = new Baplie95BGrouper();
            final Group baplie = baplieGrouper.group(reader);
            messages.add(baplie);

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
