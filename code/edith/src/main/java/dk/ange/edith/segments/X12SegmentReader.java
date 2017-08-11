package dk.ange.edith.segments;

import java.io.InputStream;
import java.util.Iterator;

import dk.ange.edith.scanner.Token;
import dk.ange.edith.scanner.Token.TokenType;
import dk.ange.edith.scanner.X12TokenReader;
import dk.ange.edith.util.PrefetchIterator;

/**
 * Collects the scanned tokens into Segments
 */
public class X12SegmentReader extends PrefetchIterator<Segment> implements Iterator<Segment> {

    private final X12TokenReader scanner;

    /**
     * @param stream
     */
    public X12SegmentReader(final InputStream stream) {
        this.scanner = new X12TokenReader(stream);
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
        // Data element separator (*)
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

}
