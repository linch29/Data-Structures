public class ArrayDeque<T> {
    private T[] item;
    private int size;
    private int nextFirst = 7;
    private int nextLast = 0;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        item = (T[]) new Object[other.item.length];
        System.arraycopy(other.item, 0, item, 0, other.item.length);
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    /**
     * Resize the item
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        nextFirst += 1;
        nextLast -= 1;
        if (nextFirst == item.length) {
            nextFirst = 0;
        }
        if (nextLast == -1) {
            nextLast = item.length - 1;
        }
        if (capacity > item.length) {
            if (nextFirst == 0 && nextLast == item.length - 1) {
                System.arraycopy(item, 0, temp, 0, item.length);
                item = temp;
                nextFirst = item.length - 1;
                nextLast += 1;
            } else {
                System.arraycopy(item, 0, temp, 0, nextLast + 1);
                System.arraycopy(item, nextFirst, temp, capacity - (item.length - nextFirst),
                        item.length - nextFirst);
                nextFirst = capacity - (item.length - nextFirst) - 1;
                nextLast += 1;
                item = temp;
            }
        } else {
            if (nextFirst > nextLast) {
                System.arraycopy(item, 0, temp, 0, nextLast + 1);
                System.arraycopy(item, nextFirst, temp, capacity - (item.length - nextFirst),
                        item.length - nextFirst);
                nextFirst = capacity - (item.length - nextFirst) - 1;
                nextLast += 1;
                item = temp;
            } else {
                System.arraycopy(item, nextFirst, temp, 0, nextLast - nextFirst + 1);
                nextLast = nextLast - nextFirst + 1;
                nextFirst = capacity - 1;
                item = temp;
            }

        }
    }

    /**
     * Add the item to the first of the AList
     */
    public void addFirst(T add) {
        item[nextFirst] = add;
        nextFirst -= 1;
        if (nextFirst == -1) {
            nextFirst = item.length - 1;
        }
        size += 1;
        if (item.length <= size) {
            resize(size * 2);
        }
    }

    /**
     * Add the item to the last of the AList
     */
    public void addLast(T add) {
        item[nextLast] = add;
        nextLast += 1;
        if (nextLast > item.length - 1) {
            nextLast = 0;
        }
        size += 1;
        if (item.length <= size) {
            resize(size * 2);
        }
    }

    /**
     * Determine whether the AList is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the AList
     */
    public int size() {
        return size;
    }

    /**
     * Print the AList
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }

    /**
     * Remove the first item of the AList
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst += 1;
        if (nextFirst == item.length) {
            nextFirst = 0;
        }
        T result = item[nextFirst];
        size -= 1;
        if ((int) (0.25 * item.length) > size && item.length >= 16) {
            resize((int) (0.5 * item.length));
        }
        return result;
    }

    /**
     * Remove the last item of the AList
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = item.length - 1;
        }
        T result = item[nextLast];
        size -= 1;
        if ((int) 0.25 * item.length > size && item.length >= 16) {
            resize((int) (0.5 * item.length));
        }
        return result;
    }

    /**
     * Return the ith item of the AList
     */
    public T get(int index) {
        index = nextFirst + 1 + index;
        if (index > item.length - 1) {
            index -= item.length;
        }
        return item[index];
    }
}
