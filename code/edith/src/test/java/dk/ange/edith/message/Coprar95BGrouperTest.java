package dk.ange.edith.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.junit.Assert;
import org.junit.Test;

import dk.ange.edith.message.Coprar95BGrouper;
import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;
import dk.ange.edith.dom.Group;


/**
 * Test
 */
public class Coprar95BGrouperTest {

    /**
     * Test Coprar95B grouping
     * 
     * @throws IOException
     */
    @Test
    public void testCoprar95BGrouper01() throws IOException {
        try (final InputStream stream = getResourceAsStream("coprar95B/HJSURAN1420W.O.edi")) {
            List<Group> messages = readAndGroupCoprar95BStream(stream);
            Assert.assertEquals(1, messages.size());

            Assert.assertNotNull(messages.get(0).getSegment("BGM"));
            Assert.assertNotNull(messages.get(0).getSegment("RFF"));

            Assert.assertEquals(1, messages.get(0).getGroupList(1).size());
            Assert.assertNotNull(messages.get(0).getGroupList(1).get(0).getSegment("TDT"));
            Assert.assertEquals(1, messages.get(0).getGroupList(1).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(1).get(0).getSegmentList("DTM").size());

            Assert.assertEquals(1, messages.get(0).getGroupList(2).size());
            Assert.assertNotNull(messages.get(0).getGroupList(2).get(0).getSegment("NAD"));

            Assert.assertEquals(54, messages.get(0).getGroupList(3).size());

            /*
             * 1st EQD
             *
             * EQD+CN+BMOU2945190+22G1:102:5++2+5'
             * RFF+BN:ALG401465500'
             * TMD+3++2'
             * LOC+11+USNYC:139:6:NEW YORK,NY'
             * MEA+AAE+G+KGM:23390'
             * SEL+TRV016874+SH'
             * FTX+AAS+++APTO PARA FLEXI.RELEASE A TRANSERVICON TTIA - TERMINAL ALGECIRAS MUELL:E DE LA ISLA VERDE 11207 ALGECIRAS SPAIN TTIA - TERMINAL ALGECIRAS NOT:A?: POR FAVOR QUE EL TRANSPORTISTA REVISE QUE EL CONTENEDOR/ES SON APTO:S ANTES DE SACARLOS DE TTIA O SAM, YA QUE SI NO EST??N EN BUENAS CONDIC:IONES Y TEN??IS QUE DEVOLVERLOS SE OS FACTURAR?? 92??/CNTDR POR MOVIMIENT'
             * FTX+LOI++ODF+ANIMAL OR VEGETABLE FATS AND OILS'
             */
            Assert.assertNotNull(messages.get(0).getGroupList(3).get(0).getSegment("EQD"));
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("EQN").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(0).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("DTM").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(0).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("TMP").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("RNG").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(0).getSegmentList("SEL").size());
            Assert.assertEquals(2, messages.get(0).getGroupList(3).get(0).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("DGS").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getSegmentList("EQA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(0).getGroupList(4).size());

            /*
             * 8th EQD
             *
             * EQD+CN+HJCU2342134+22G1:102:5++2+5'
             * RFF+BN:BIO401310100'
             * TMD+3++2'
             * LOC+11+USNYC:139:6:NEW YORK,NY'
             * MEA+AAE+G+KGM:19413'
             * SEL+NG016080+SH'
             * FTX+AAA+++AUTOMOBLIES; OTHER THAN RAILWAY VEHICLES'
             * TDT+10+1422S++++++9351115:146'
             * LOC+11+:139:6'
             */
            Assert.assertNotNull(messages.get(0).getGroupList(3).get(7).getSegment("EQD"));
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("RFF").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("EQN").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("TMD").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("DTM").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("LOC").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("MEA").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("DIM").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("TMP").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("RNG").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("SEL").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getSegmentList("FTX").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("DGS").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getSegmentList("EQA").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getGroupList(4).size());
            Assert.assertNotNull(messages.get(0).getGroupList(3).get(7).getGroupList(4).get(0).getSegment("TDT"));
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getGroupList(4).get(0).getSegmentList("RFF").size());
            Assert.assertEquals(1, messages.get(0).getGroupList(3).get(7).getGroupList(4).get(0).getSegmentList("LOC").size());
            Assert.assertEquals(0, messages.get(0).getGroupList(3).get(7).getGroupList(4).get(0).getSegmentList("DTM").size());

            Assert.assertNotNull(messages.get(0).getGroupList(3).get(8));
            Assert.assertNotNull(messages.get(0).getGroupList(3).get(53));

        }
    }

    private static final List <Group> readAndGroupCoprar95BStream(final InputStream stream) {
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
                    || !unh.getData(2, 3).asString("").equals("95B") //
                    || !unh.getData(2, 4).asString("").equals("UN") ) {
                throw new RuntimeException("Expected segment UNH+...+COPRAR:D:95B:UN:', got: " + unh.print());
            }

            final Coprar95BGrouper coprarGrouper = new Coprar95BGrouper();
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
