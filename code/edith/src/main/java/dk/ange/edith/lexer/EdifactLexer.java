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

    private boolean firstSegment = true;

    /**
     * @param stream
     */
    public EdifactLexer(final InputStream stream) {
        this.scanner = new EdifactScanner(stream);
    }

    @Override
    protected Segment prefetch() {
        // Special handling of UNA header
        if (firstSegment) {
            firstSegment = false;
            consumePossibleUnaSegment();
        }
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

    private void consumePossibleUnaSegment() {
        final Token una = scanner.peek();
        if (una.type() == TokenType.VALUE && una.value().equals("UNA")) {
            scanner.next(); // pop UNA that was peeked
            checkNextEquals(Token.COMPONENT_DATA_ELEMENT_SEPARATOR);
            checkNextEquals(Token.DATA_ELEMENT_SEPARATOR);
            checkNextEquals(new Token(". ")); // Decimal notation, Release indicator, Reserved for future use
            checkNextEquals(Token.SEGEMENT_TERMINATOR);
        }
    }

    private void checkNextEquals(final Token expected) {
        final Token next = scanner.next();
        if (!expected.equals(next)) {
            throw new RuntimeException("Did not read the expected element to UNA segment, expected " + expected
                    + ", got " + next);
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
