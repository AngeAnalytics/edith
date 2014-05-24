package dk.ange.edith.lexer;

import java.io.InputStream;
import java.util.Iterator;

import dk.ange.edith.data.Segment;
import dk.ange.edith.lexer.scanner.EdifactScanner;
import dk.ange.edith.lexer.scanner.Token;
import dk.ange.edith.lexer.scanner.Token.TokenType;
import dk.ange.edith.stream.EdifactEventReader;

/**
 * Collects the scanned tokens into Segments
 */
public final class EdifactLexer extends PrefetchIterator<Segment> implements Iterator<Segment>, EdifactEventReader {

    private final EdifactScanner scanner;

    /**
     * @param stream
     */
    public EdifactLexer(final InputStream stream) {
        this.scanner = new EdifactScanner(stream);
    }

    @Override
    protected Segment prefetch() {
        // Check for EOF
        if (!scanner.hasNext()) {
            return null;
        }
        // Tag
        final Token tag = scanner.next();
        if (tag.type() != TokenType.VALUE) {
            throw new RuntimeException("tag.type() != TokenType.VALUE");
        }
        final Segment.Builder builder = new Segment.Builder(tag.value());
        // Data element separator (+)
        final Token separatorAfterTag = scanner.next();
        if (separatorAfterTag.type() != TokenType.DATA_ELEMENT_SEPARATOR) {
            throw new RuntimeException("separatorAfterTag.type() != TokenType.DATA_ELEMENT_SEPARATOR");
        }
        // The rest...
        boolean readyForValue = true;
        while (true) {
            final Token token = scanner.next();
            switch (token.type()) {
            case VALUE:
                builder.addValue(token.value());
                readyForValue = false;
                break;
            case DATA_ELEMENT_SEPARATOR:
                builder.nextDataElement();
                readyForValue = true;
                break;
            case COMPONENT_DATA_ELEMENT_SEPARATOR:
                if (readyForValue) {
                    builder.addValue(null);
                }
                readyForValue = true;
                break;
            case SEGEMENT_TERMINATOR:
                return builder.build();
            }
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
