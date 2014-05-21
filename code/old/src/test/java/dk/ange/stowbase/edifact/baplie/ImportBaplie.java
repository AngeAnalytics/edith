package dk.ange.stowbase.edifact.baplie;

import java.io.InputStream;

import dk.ange.stowbase.edifact.format.FormatReader;
import dk.ange.stowbase.edifact.lexer.EdifactLexer;
import dk.ange.stowbase.edifact.parser.EdifactReader;

/**
 * Import a BAPLIE into stowbase
 */
public class ImportBaplie {

    private ImportBaplie() {
        // this.stowbase = RemoteStowbase.forBaseUrl(StowbaseProperties.getServerUri()).bundle();
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        new ImportBaplie().run();
    }

    private void run() {
        parse("HKG to TAG EDI.EDI");
        parse("job_43002_input_Release_data.txt");
        parse("job_43003_input_Release_data.txt");
        parse("job_43005_input_Release_data.txt");
    }

    private void parse(final String fileName) {
        final EdifactReader reader = new EdifactReader();
        final BaplieContentHandler contentHandler = new BaplieContentHandler();
        reader.setContentHandler(contentHandler);
        reader.setSegmentTable(FormatReader.readFormat(BaplieContentHandler.class.getResourceAsStream("BAPLIE_D.95B")));
        final InputStream inputStream = ImportBaplie.class.getResourceAsStream(fileName);
        reader.parse(new EdifactLexer(inputStream));
        System.out.println("Saw " + contentHandler.getCount() + " containers in " + fileName);
    }

}
