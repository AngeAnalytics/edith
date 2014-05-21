package dk.ange.edith.lexer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import dk.ange.edith.data.Segment;
import dk.ange.edith.lexer.scanner.EdifactScanner;
import dk.ange.edith.lexer.scanner.Token;

/**
 * Collects the scanned tokens into Segments
 */
// Extract the UpdateNextIterator into a superclass
public final class EdifactLexer implements Iterator<Segment> {

    private final Iterator<Token> scanner;

    private Segment next;

    /**
     * @param stream
     */
    public EdifactLexer(final InputStream stream) {
        scanner = new EdifactScanner(stream);
        updateNext();
    }

    // TODO @SuppressWarnings("cast")
    @SuppressWarnings("cast")
    private void updateNext() {
        if (scanner.hasNext()) {
            final List<Token> tokenList = new ArrayList<Token>();
            while (true) {
                final Token token = scanner.next();
                // TODO fix code:
                if (token instanceof Token) { // SegmentTerminator) {
                    /*
                     * TODO a lot of parsing happened in the old "new Segment()", move that to here from
                     * dk.ange.stowbase.edifact
                     */
                    next = new Segment(null, null);// tokenList);
                    break;
                } else {
                    tokenList.add(token);
                }
            }
        } else {
            next = null;
        }
    }

    public boolean hasNext() {
        return next != null;
    }

    public Segment next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        final Segment oldNext = next;
        updateNext();
        return oldNext;
    }

    public void remove() {
        throw new UnsupportedOperationException(EdifactLexer.class.getSimpleName() + " can not remove()");
    }

}
