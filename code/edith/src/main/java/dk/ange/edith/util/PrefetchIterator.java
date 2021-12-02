package dk.ange.edith.util;

import java.util.NoSuchElementException;

import com.google.common.collect.PeekingIterator;

/**
 * Iterator implemented by prefetching the next element
 *
 * @param <T>
 */
public abstract class PrefetchIterator<T> implements PeekingIterator<T> {

    private boolean nextIsValid = false;

    private T next;

    /**
     * @return the next element to return from the iterator, return null if there is no more elements
     */
    protected abstract T prefetch();

    private void makeNextValid() {
        if (nextIsValid) {
            return;
        }
        nextIsValid = true;
        next = prefetch();
    }

    @Override
    public boolean hasNext() {
        makeNextValid();
        return next != null;
    }

    @Override
    public T next() {
        makeNextValid();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        nextIsValid = false;
        return next;
    }

    /**
     * @return the next element without advancing the iterator, will return null if there is no more elements
     */
    @Override
    public T peek() {
        makeNextValid();
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " can not remove()");
    }

}
