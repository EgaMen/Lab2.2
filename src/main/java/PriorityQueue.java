import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<E> {
    private Object[] queue;
    private int size=0;
    private final Comparator<? super E> comparator;


    public PriorityQueue(int initialCapacity) {

        this(initialCapacity, null);
    }
    public  PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {

        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;



    }

    private void grow(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        int oldCapacity = queue.length;
        int newCapacity = ((oldCapacity < 64)? ((oldCapacity + 1) * 2): ((oldCapacity / 2) * 3));
        if (newCapacity < 0)
            newCapacity = Integer.MAX_VALUE;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        queue = Arrays.copyOf(queue, newCapacity);

    }
    private void  siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
    private void  siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
            break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
    private void siftUpUsingComparator(int k, E x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1; //деление на 2
            Object e = queue[parent];
            if (comparator.compare(x, (E) e) >= 0)
            break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }





    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException();
        int i = size;

        if (i >= queue.length)
            grow(i + 1);

        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1)
        return false;
        else {
            removeAt(i);
            return true;
        }

    }
    public E extractMaximum() {
        return (E)queue[0];


   }
    private int  indexOf(Object o) {
        if (o != null)
            for (int i = 0; i < size; i++){
                if (o.equals(queue[i]))
                return i;
            }
        return -1;
}
    private E  removeAt(int i) {
        if (i < 0 || i >= size) throw new AssertionError();
        int s = --size;
        if (s == i) // removed last element
        queue[i] = null;
        else {
            E moved = (E) queue[s];
            queue[s] = null;
            siftDown(i, moved);
            if (queue[i] == moved) {
                siftUp(i, moved);
                if (queue[i] != moved)
                    return moved;
            }
        }
        return null;
    }
    private void siftDown(int k, E x) {
        if (comparator != null)
        siftDownUsingComparator(k, x);
        else
        siftDownComparable(k, x);
    }
    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;

        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
            break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }


    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size && comparator.compare((E) c, (E) queue[right]) > 0)
            c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0)
            break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    public Object[] toArray() {
        return queue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }


}
