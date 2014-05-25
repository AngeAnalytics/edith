package dk.ange.edith.baplieparser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import dk.ange.edith.dom.Group;
import dk.ange.edith.segments.EdifactSegmentReader;
import dk.ange.edith.segments.Segment;

/**
 * Command line tool for testing the BAPLIE parser
 */
public class BaplieParser {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
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

        final Segment unh = reader.peek();
        if (!unh.getTag().equals("UNH")) {
            throw new RuntimeException("Wrong input in file: " + unh);
        }
        final BaplieReader baplieReader = new BaplieReader();
        // TODO check that it is a BAPLIE message
        final Group baplie = baplieReader.read(reader);
        // System.out.print(baplie.toDebugString());
        // System.out.println(baplie);
        System.out.println("parsed " + baplie.getGroupList(2).size() + " containers");

        final Segment unz = reader.next();
        if (!unz.getTag().equals("UNZ")) {
            throw new RuntimeException("Wrong input in file: " + unz);
        }

        if (reader.hasNext()) {
            throw new RuntimeException("Expected end of file, found: " + reader.peek());
        }
    }

}
