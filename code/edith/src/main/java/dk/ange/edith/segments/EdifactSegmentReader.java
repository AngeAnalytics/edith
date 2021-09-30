package dk.ange.edith.segments;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import dk.ange.edith.scanner.EdifactTokenReader;
import dk.ange.edith.scanner.Token;
import dk.ange.edith.scanner.Token.TokenType;
import dk.ange.edith.util.EdifactParseRuntimeException;
import dk.ange.edith.util.PrefetchIterator;

/**
 * Collects the scanned tokens into Segments
 */
public final class EdifactSegmentReader extends PrefetchIterator<Segment> implements Iterator<Segment> {

    private final EdifactTokenReader scanner;

    private boolean firstSegment = true;

    /**
     * @param stream
     */
    public EdifactSegmentReader(final InputStream stream) {
        this.scanner = new EdifactTokenReader(stream);
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
            throw new EdifactParseRuntimeException("tag.type() != TokenType.VALUE");
        }
        final Segment.Builder builder = new Segment.Builder(tag.value());
        // Data element separator (+)
        if (!scanner.hasNext()) {
            throw new EdifactParseRuntimeException("EOF when expecting: Data element separator (+)");
        }
        final Token separatorAfterTag = scanner.next();
        if (separatorAfterTag.type() != TokenType.DATA_ELEMENT_SEPARATOR) {
            throw new EdifactParseRuntimeException("separatorAfterTag.type() != TokenType.DATA_ELEMENT_SEPARATOR");
        }
        // The rest...
        boolean readyForValue = true;
        while (true) {
            if (!scanner.hasNext()) {
                throw new EdifactParseRuntimeException("EOF when reading unfinished Segment");
            }
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
        if (una == null) {
            return;
        }
        if (una.type() == TokenType.VALUE && una.value().equals("UNA")) {
            scanner.next(); // pop UNA that was peeked
            checkNextEquals(Token.COMPONENT_DATA_ELEMENT_SEPARATOR);
            checkNextEquals(Token.DATA_ELEMENT_SEPARATOR);
            // Decimal notation, Release indicator, Reserved for future use:
            checkNextEquals(new Token(". "), new Token(", "));
            checkNextEquals(Token.SEGEMENT_TERMINATOR);
        }
    }

    private void checkNextEquals(final Token... expected) {
        if (!scanner.hasNext()) {
            throw new EdifactParseRuntimeException("EOF when checking UNA segment");
        }
        final Token next = scanner.next();
        for (final Token exp : expected) {
            if (exp.equals(next)) {
                return;
            }
        }
        throw new EdifactParseRuntimeException("Did not read the expected element to UNA segment,"
                + " expected " + Arrays.toString(expected) + ", got " + next);
    }

}
