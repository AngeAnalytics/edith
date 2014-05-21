package org.stowbase.client.remote;

import java.io.IOException;
import java.io.Writer;

/**
 * A simple wrapper around Writer that protects it against close and flush. This is useful to avoid a response from
 * being committed, we need that in order to be able to show the correct error page if only a little has been written
 * before an error happens.
 */
class ProtectWriter extends Writer {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProtectWriter.class);

    private final Writer writer;

    public ProtectWriter(final Writer writer) {
        this.writer = writer;
    }

    // Delegators that do nothing:

    @Override
    public void close() {
        log.trace("Don't close");
        // log.debug("Don't close", new Throwable("Stack trace"));
    }

    @Override
    public void flush() {
        log.trace("Don't flush");
        // log.debug("Don't flush", new Throwable("Stack trace"));
    }

    // Simple delegators:

    @Override
    public Writer append(final char c) throws IOException {
        return writer.append(c);
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        return writer.append(csq, start, end);
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        return writer.append(csq);
    }

    @Override
    public boolean equals(final Object obj) {
        return writer.equals(obj);
    }

    @Override
    public int hashCode() {
        return writer.hashCode();
    }

    @Override
    public String toString() {
        return writer.toString();
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        writer.write(cbuf);
    }

    @Override
    public void write(final int c) throws IOException {
        writer.write(c);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        writer.write(str, off, len);
    }

    @Override
    public void write(final String str) throws IOException {
        writer.write(str);
    }

}
