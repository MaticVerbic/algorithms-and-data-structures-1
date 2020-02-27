@SuppressWarnings("unchecked")
public class Naloga2 {
    public static void main(String[] args) {
        try {
            Sequence < Integer > seq = new ArrayDeque < Integer > ();
            seq.add(5);
            seq.add(3);
            seq.add(1);
            seq.add(4);
            seq.add(0);
            seq.add(2);
            System.out.println(seq.toString(0));
            Sort sort = new Sort(seq.copy());
            /*sort.insertionSort(true);
            sort.print();
            System.out.println("a" + seq.toString(0));
            sort.setSequence(seq.copy());
            sort.insertionSort(false);
            sort.print();
            System.out.println("a" + seq.toString(0));
            sort.setSequence(seq.copy());
            sort.selectionSort(true);
            sort.print();
            System.out.println("a" + seq.toString(0));
            sort.setSequence(seq.copy());
            sort.selectionSort(false);
            sort.print();
            System.out.println("a" + seq.toString(0));*/
            //System.out.println("a" + seq.toString(0));
            sort.setSequence(seq.copy());
            sort.bubbleSort(true);
            sort.print();
            //System.out.println("a" + seq.toString(0));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }
}



@SuppressWarnings("unchecked")
class Sort {
    Sequence < Integer > s;

    public Sort(Sequence < Integer > seq) throws CollectionException {
        s = seq;
    }

    public void insertionSort(boolean desc) throws CollectionException {
        int size = s.count();
        System.out.println(s.toString(1));
        for (int i = 1; i < size; i++) {
            if (desc) {
                for (int j = i; j > 0; j--) {
                    if (s.get(j) > s.get(j - 1)) {
                        s.swap(j, j - 1);
                    }
                }
            } else {
                for (int j = i; j > 0; j--) {
                    if (s.get(j) < s.get(j - 1)) {
                        s.swap(j, j - 1);
                    }
                }
            }
            System.out.println(s.toString(i + 1));
        }
    }

    public void selectionSort(boolean desc) throws CollectionException{
        int size = s.count();
        for (int i = 0; i < size; i++){
            if (desc){
                int max = s.get(i);
                int maxIndex = i;
                for (int j = i; j < size; j++){
                    if (s.get(j) > max){
                        maxIndex = j;
                        max = s.get(j);
                    }
                }
                if (maxIndex != i){
                    s.swap(i, maxIndex);
                }
            } else {
                int min = s.get(i);
                int minIndex = i;
                for (int j = i; j < size; j++){
                    if (s.get(j) < min){
                        minIndex = j;
                        min = s.get(j);
                    }
                }
                if (minIndex != i){
                    s.swap(i, minIndex);
                }
            }
            System.out.println(s.toString(i + 1));
        }
    }

    public void bubbleSort(boolean desc) throws CollectionException{
        int size = s.count();
        boolean sorted = false;
        for(int i = 0; i < size;i++){
            for(int j=i; j < size-1; j++){
                if (s.get(j+1) > s.get(j)){
                    System.out.println("\t" + s.toString(j, j+2));
                    s.swap(s.get(j), s.get(j+1));
                }
            }
            //System.out.println(s.toString(i + 1));
        }
    }

    public void print() throws CollectionException {
        System.out.println(s.toString(0));
    }

    public void setSequence(Sequence < Integer > seq){
        s = seq;
    }

}



@SuppressWarnings("unchecked")
class ArrayDeque < T > implements Sequence < T > {
    // vars
    private static final int CAPACITY = 64;
    private T[] arr;
    private int front,
    back,
    size;


    // Constructor
    public ArrayDeque() {
        front = 0;
        back = 0;
        size = 0;
        arr = (T[]) new Object[CAPACITY];
    }


    /**                         *
     * This implements Sequence<T> *
     *                          */

    public void resize() {
        T[] newArr = (T[]) new Object[CAPACITY * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // get() returns an item at index i
    public T get(int i) throws CollectionException {
        if (i >= size) {
            throw new CollectionException(ERR_MSG_INDEX);
        }
        return (T) arr[index(i)];
    }

    // add() pushes an object at the back of the list
    public void add(T x) throws CollectionException {
        if (size >= CAPACITY) resize();
        arr[size] = x;
        size++;
    }

    public void set(int i, T x) throws CollectionException {
        if (size >= CAPACITY) resize();
        arr[i] = x;
    }

    public void insert(int i, T x) throws CollectionException {
        if (i >= size + 1) {
            throw new CollectionException(ERR_MSG_INDEX);
        }
        if (size >= CAPACITY) resize();
        for (int j = size - 1; j >= i; j--) arr[j + 1] = arr[j];
        arr[i] = x;
        size++;
    }

    public void delete(int i) throws CollectionException {
        if (size == 0) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        if (i == size - 1) {
            arr[size - 1] = null;
            size--;
            return;
        }
        for (int j = i; j < size - 1; j++) arr[j] = arr[j + 1];
        size--;
    }

    public void swap(int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**                            *
     *  This implements Collection *
     *                             */

    // isEmpty() returns whether size is equal to 0
    public boolean isEmpty() {
        return size == 0;
    }

    // isFull returns whether size is equal to capacity
    public boolean isFull() {
        return size == CAPACITY;
    }

    // count() returns size+1
    public int count() {
        return size;
    }

    /**          *
     *  privates *
     *           *
     *           */

    // next() returns next element at logical index
    public int next(Integer i) {
        return (i + 1) % CAPACITY;
    }

    // prev() returns index at previous logical index
    public int prev(Integer i) {
        return (i > 0) ? i - 1 : CAPACITY - 1;
    }

    // index() returns a logical index
    public int index(Integer i) throws CollectionException {
        if (i < 0 || i >= size) {
            throw new CollectionException(ERR_MSG_INDEX);
        }
        return (front + i) % CAPACITY;
    }

    // toString returns nicely formatted arr
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < size; i++) {
           if (i != 0) {
                sb.append(", ");
            }
            try {
                sb.append(arr[index(i)]);
            } catch (CollectionException e) {
                System.out.println(e);
                return "";
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    // toString returns nicely formatted arr
    public String toString(int l) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < size; i++) {
            if (l == i && l != 0) {
                sb.append(" | ");
            } else if (i != 0) {
                sb.append(", ");
            }
            try {
                sb.append(arr[index(i)]);
            } catch (CollectionException e) {
                System.out.println(e);
                return "";
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // toString returns nicely formatted arr
    public String toString(int l1, int l2) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < size; i++) {
            if ((l1 == i || l2 == i )) {
                sb.append(" | ");
            } else if (i != 0) {
                sb.append(", ");
            }
            try {
                sb.append(arr[index(i)]);
            } catch (CollectionException e) {
                System.out.println(e);
                return "";
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public Sequence copy() throws CollectionException {
        Sequence < T > s = new ArrayDeque < T > ();
        for (int i = 0; i < size; i++) {
            s.add(arr[i]);
        }
        return s;
    }
}

class CollectionException extends Exception {
    public CollectionException(String message) {
        super(message);
    }
}

// Collection ...
interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int count();
    String toString();
    String toString(int l);
    String toString(int l1, int l2);
    Sequence copy() throws CollectionException;
}

// Sequence ...
interface Sequence < T > extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
    void set(int i, T x) throws CollectionException;
    void insert(int i, T x) throws CollectionException;
    void delete(int i) throws CollectionException;
    void resize();
    void swap(int i, int j);
}