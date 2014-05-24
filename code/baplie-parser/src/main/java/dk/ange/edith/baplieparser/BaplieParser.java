package dk.ange.edith.baplieparser;

import java.io.FileInputStream;
import java.io.InputStream;

import dk.ange.edith.data.Group;
import dk.ange.edith.data.Segment;
import dk.ange.edith.lexer.EdifactLexer;
import dk.ange.edith.stream.EdifactEventReader;

/**
 * Command line tool for testing the BAPLIE parser
 */
public class BaplieParser {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Wrong number of arguments!");
            System.err.println("Usage: BaplieParser file");
            System.exit(1);
        }
        final String fileName = args[0];
        System.out.println("Open file: " + fileName);
        try (InputStream stream = new FileInputStream(fileName)) {
            readStream(stream);
        }
        System.out.println("Done.");
    }

    private static void readStream(final InputStream stream) {
        try (EdifactEventReader reader = new EdifactLexer(stream)) {
            final Segment perhapsUna = reader.peek();
            if (perhapsUna.getTag().equals("UNA")) {
                reader.next();
            }

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
            System.out.print(baplie.toDebugString());
            System.out.println(baplie);
            System.out.println("Read BAPLIE with " + baplie.getGroupList(2).size() + " containers");

            final Segment unz = reader.next();
            if (!unz.getTag().equals("UNZ")) {
                throw new RuntimeException("Wrong input in file: " + unz);
            }

            if (reader.hasNext()) {
                throw new RuntimeException("Expected end of file, found: " + reader.peek());
            }
        }
    }

}
