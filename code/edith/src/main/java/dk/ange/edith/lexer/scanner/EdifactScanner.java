package dk.ange.edith.lexer.scanner;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Scans the EDIFACT file and returns a stream of simple tokens
 * <p>
 * TODO See dk.ange.stowbase.edifact.scanner for an implementation
 */
public final class EdifactScanner implements Iterator<Token>, AutoCloseable {

    /**
     * @param is
     */
    // TODO SuppressWarnings("unused")
    @SuppressWarnings("unused")
    public EdifactScanner(final InputStream is) {
        // TODO
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Token next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
