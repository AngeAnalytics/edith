package dk.ange.edith.lexer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dk.ange.edith.data.Segment;
import dk.ange.edith.lexer.scanner.EdifactScanner;
import dk.ange.edith.lexer.scanner.Token;
import dk.ange.edith.stream.EdifactEventReader;

/**
 * Collects the scanned tokens into Segments
 */
// Extract the UpdateNextIterator into a superclass
public final class EdifactLexer extends PrefetchIterator<Segment> implements Iterator<Segment>, EdifactEventReader {

    private final EdifactScanner scanner;

    /**
     * @param stream
     */
    public EdifactLexer(final InputStream stream) {
        scanner = new EdifactScanner(stream);
    }

    // TODO @SuppressWarnings("cast")
    @Override
    @SuppressWarnings("cast")
    protected Segment prefetch() {
        if (scanner.hasNext()) {
            final List<Token> tokenList = new ArrayList<>();
            while (true) {
                final Token token = scanner.next();
                // TODO fix code:
                if (token instanceof Token) { // SegmentTerminator) {
                    /*
                     * TODO a lot of parsing happened in the old "new Segment()", move that to here from
                     * dk.ange.stowbase.edifact
                     */
                    return new Segment(null, null);// tokenList);
                } else {
                    tokenList.add(token);
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public void close() {
        scanner.close();
    }

    @Override
    public void report(final String message) {
        // TODO Auto-generated method stub

    }

}
