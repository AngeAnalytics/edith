package dk.ange.edith.scanner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Iterator;

import dk.ange.edith.util.PrefetchIterator;

/**
 * Scans the Ansi X12 file and returns a stream of simple tokens
 */
public class X12TokenReader extends PrefetchIterator<Token> implements Iterator<Token> {

    private final PushbackInputStream pushbackInputStream;

    /**
     * COMPONENT_DATA_ELEMENT_SEPARATOR = '&gt;'
     */
    public static final char COMPONENT_DATA_ELEMENT_SEPARATOR = '>';

    /**
     * DATA_ELEMENT_SEPARATOR = '*'
     */
    public static final char DATA_ELEMENT_SEPARATOR = '*';

    // Ansi X12 does not support a release character

    /**
     * SEGEMENT_TERMINATOR = '~'
     */
    public static final char SEGEMENT_TERMINATOR = '~';

    /**
     * @param inputStream
     */
    public X12TokenReader(final InputStream inputStream) {
        this.pushbackInputStream = new PushbackInputStream(inputStream, 1);
    }

    @Override
    protected Token prefetch() {
        try {
            final int character = readNonLineTermination();
            switch (character) {
            case -1:
                return null;
            case COMPONENT_DATA_ELEMENT_SEPARATOR:
                return Token.COMPONENT_DATA_ELEMENT_SEPARATOR;
            case DATA_ELEMENT_SEPARATOR:
                return Token.DATA_ELEMENT_SEPARATOR;
            case SEGEMENT_TERMINATOR:
                return Token.SEGEMENT_TERMINATOR;
            default:
                return readDataElementValue(character);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Ignores all CR and LF, this might be the wrong way to do it
    private int readNonLineTermination() throws IOException {
        int nextByte;
        do {
            nextByte = pushbackInputStream.read();
        } while (nextByte == '\r' || nextByte == '\n');
        return nextByte;
    }

    private Token readDataElementValue(final int firstByte) throws IOException {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int nextByte = firstByte;
        while (true) {
            if (isServiceCharacter(nextByte)) {
                pushbackInputStream.unread(nextByte);
                return new Token(bytes.toString("Latin1"));
            }
            bytes.write(nextByte);
            nextByte = readNonLineTermination();
            // Handle EOF, this is an error but it will be handled in the parser
            if (nextByte == -1) {
                return new Token(bytes.toString("Latin1"));
            }
        }
    }

    /**
     * A service character need to be escaped in the output.
     *
     * @param b
     * @return Returns true if b is a service character.
     */
    public static boolean isServiceCharacter(final int b) {
        switch (b) {
        case COMPONENT_DATA_ELEMENT_SEPARATOR:
        case DATA_ELEMENT_SEPARATOR:
        case SEGEMENT_TERMINATOR:
            return true;
        default:
            return false;
        }
    }

}
