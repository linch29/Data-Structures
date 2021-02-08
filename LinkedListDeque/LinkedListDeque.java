public class LinkedListDeque<T> {
    private class LinkedList<T2> {
        private T2 item;
        private LinkedList next;
        private LinkedList prev;

        LinkedList(T2 item, LinkedList next, LinkedList prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private LinkedList<Integer> sentinel = new LinkedList<Integer>(-51723, null, null);
    private int size = 0;
    private LinkedList recursion = sentinel;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * @source https://www.youtube.com/watch?v=JNroRiEG7U4
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /**
     * Add the item to the first of the list
     */
    public void addFirst(T item) {
        LinkedList<T> temp = new LinkedList<>(item, sentinel.next, sentinel);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    /**
     * Add the item to the end of the list
     */
    public void addLast(T item) {
        LinkedList<T> temp = new LinkedList<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    /**
     * Determine whether the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Print out all the list
     */
    public void printDeque() {
        LinkedList temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Remove the first item of the list
     */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T temp = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    /**
     * Remove the last item of the list
     */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T temp = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return temp;
    }

    /**
     * Return the ith item of the list
     */
    public T getRecursive(int index) {
        recursion = recursion.next;
        if (index == 0 && recursion != sentinel) {
            T result = (T) recursion.item;
            recursion = sentinel;
            return result;
        } else if (index != 0 && recursion == sentinel) {
            return null;
        } else {
            return getRecursive(index - 1);
        }
    }

    /**
     * Return the ith item of the list by iteration
     */
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        LinkedList<T> temp = sentinel.next;
        while (temp != sentinel && index > 0) {
            temp = temp.next;
            index -= 1;
        }
        if (temp == sentinel) {
            return null;
        } else {
            return temp.item;
        }
    }
}
