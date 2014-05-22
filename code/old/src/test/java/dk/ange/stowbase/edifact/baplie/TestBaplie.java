package dk.ange.stowbase.edifact.baplie;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import dk.ange.stowbase.edifact.format.FormatReader;
import dk.ange.stowbase.edifact.lexer.EdifactLexer;
import dk.ange.stowbase.edifact.parser.EdifactReader;

/**
 * Test that BAPLIEs are read correct
 */
public class TestBaplie {

    /**
     * Test
     *
     * @throws IOException
     */
    @Test
    public void testBaplie() throws IOException {
        final EdifactReader reader = new EdifactReader();
        final BaplieContentHandler contentHandler = new BaplieContentHandler();
        reader.setContentHandler(contentHandler);
        reader.setSegmentTable(FormatReader.readFormat(BaplieContentHandler.class.getResourceAsStream("BAPLIE_D.95B")));
        final String fileName = "job_43002_input_Release_data.txt";
        try (final InputStream inputStream = ImportBaplie.class.getResourceAsStream(fileName)) {
            reader.parse(new EdifactLexer(inputStream));
        }
    }

}
