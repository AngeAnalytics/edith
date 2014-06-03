package dk.ange.edith.demo.iftsai;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import dk.ange.edith.dom.Group;
import dk.ange.edith.message.Iftsai99BGrouper;
import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;

/**
 * Command line tool for testing the IFTSAI parser
 */
public class DemoIftsaiParser {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        System.out.println("Begin");
        for (final String fileName : args) {
            System.out.print("Parse '" + fileName + "', ");
            try (InputStream stream = new BufferedInputStream(new FileInputStream(fileName))) {
                readStream(stream);
            }
        }
        System.out.println("Done.");
    }

    private static void readStream(final InputStream stream) {
        final EdifactSegmentReader reader = new EdifactSegmentReader(stream);
        final Segment unb = reader.next();
        if (!unb.getTag().equals("UNB")) {
            throw new RuntimeException("Wrong input in file: " + unb);
        }

        int messages = 0;
        int calls = 0;
        while (reader.peek().getTag().equals("UNH")) {

            final Segment unh = reader.peek();
            if (!unh.getTag().equals("UNH") //
                    || !unh.getData(2, 1).asString("").equals("IFTSAI") //
                    || !unh.getData(2, 2).asString("").equals("D") //
                    || !unh.getData(2, 3).asString("").equals("99B") //
                    || !unh.getData(2, 4).asString("").equals("UN") ) {
                throw new RuntimeException("UNH segment UNH+...+IFTSAI:D:99B:UN' expected, got: " + unh + " ." //
                        + " " + unh.getData(2, 1).asString("") + ":" + unh.getData(2, 2).asString("") //
                        + ":" + unh.getData(2, 3).asString("") + ":" + unh.getData(2, 4).asString(""));
            }

            final Iftsai99BGrouper iftsaiGrouper = new Iftsai99BGrouper();
            final Group iftsai = iftsaiGrouper.group(reader);
            // System.out.print(iftsai.toDebugString());
            // System.out.println(iftsai);
            messages += 1;
            calls += iftsai.getGroup(4).getGroupList(5).size();
        }
        System.out.println("parsed " + messages + " messages with a total of " + calls + " calls");

        final Segment unz = reader.next();
        if (!unz.getTag().equals("UNZ")) {
            throw new RuntimeException("Wrong input in file: " + unz);
        }

        if (reader.hasNext()) {
            throw new RuntimeException("Expected end of file, found: " + reader.peek());
        }
    }

}
