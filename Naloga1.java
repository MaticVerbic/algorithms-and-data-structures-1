import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Naloga1{
    public static void main(String[] args){
        try {
        Kalkulator k = new Kalkulator();
        k.read();
        } catch (CollectionException e){
            System.out.println(e);
        }
    }
}


/***********************
 *                     *
 *  Class Kalkulator *
 *                     *
 ***********************/ 
@SuppressWarnings("unchecked")
class Kalkulator {
    Sequence<Stack> seq;
    public Kalkulator() throws CollectionException{
        seq = new ArrayDeque<>();
        for (int i=0; i<10; i++){
                Stack<String> st = new ArrayDeque<>();
                //System.out.println(st.toString());
                seq.add(st);
        }
        System.out.println(seq.toString());
    }

    public void read() throws CollectionException {
            Scanner s = new Scanner(System.in);
            while (s.hasNextLine()){
                seq = new ArrayDeque<>();
                for (int i=0; i<10; i++){
                        Stack<String> st = new ArrayDeque<>();
                        //System.out.println(st.toString());
                        seq.add(st);
                }
                String l = s.nextLine();
                if (l.equals("q")){
                    
                    s.close();
                    System.exit(0);
                }
                Scanner sc = new Scanner(l);
                while (sc.hasNext()){
                    String next = sc.next();    
                    Stack<String> stck = seq.get(0);
                    stck.push(next);
                }
                run();
                sc.close();
            }
        
    }

    private void run() throws CollectionException {
        Stack<String> st = seq.get(0).reverse();
        while(!st.isEmpty()){
            String s = String.valueOf(st.pop());
            switch(s){
                case "echo": 
                    echo();
                    break;
                case "pop": 
                    pop();
                    break;
                default: 
                    continue;
            }
        }
    }

    public void echo() throws CollectionException {
        if (seq.get(0).isEmpty()){
            System.out.print("\n");
        } else {
            System.out.println(seq.get(0).top());
        }
    }

    public void pop() throws CollectionException {
        seq.get(0).pop();
        seq.get(0).pop();
    }
}






/***********************
 *                     *
 *  Class ArrayDequeue *
 *                     *
 ***********************/     
@SuppressWarnings("unchecked")
class ArrayDeque<T> implements Stack<T>, Sequence<T> {
    // vars
    private static final int DEFAULT_CAPACITY = 64;
    private T[] arr;
    private int front, back, size;
    

    // Constructor
    public ArrayDeque(){
        front = 0;
        back = 0;
        size = 0;
        arr = (T[]) new Object[DEFAULT_CAPACITY]; 
    }
    
    /**                          *
     *  This implements Stack<T> *
     *                           */

    // push() pushes an Object o to the top of the stack ...
    public void push(T x) throws CollectionException {
        arr[size++] = x;
    }

    // pop() removes and returns the object from the top of the stack ...
    public T pop() throws CollectionException {
        Object o = arr[--size];
        arr[size] = null;
        return (T)o;
    }

    // peek() returns the object form the top of the stack ...
    public T top() throws CollectionException {
        return (T) arr[size-1];
    }

    public Stack<T> reverse() throws CollectionException{
        Stack<T> s = new ArrayDeque<T>();
        for(int i=size;i>=0;i--){
            s.push(arr[i]);
        }
        return s;
    }

    /**                         *
     * This implements Sequence<T> *
     *                          */
    
    // get() returns an item at index i
    public T get(int i) throws CollectionException{
        if (i >= size){throw new CollectionException(ERR_MSG_INDEX);}
        return (T) arr[index(i)];
    }

    // add() pushes an object at the back of the list
    public void add(T x) throws CollectionException{
        if (isFull()) {throw new CollectionException(ERR_MSG_FULL);}
        arr[size] = x;
        size++;
    }

    /**                            *
     *  This implements Collection *
     *                             */
    
     // isEmpty() returns whether size is equal to 0
    public boolean isEmpty(){
        return size == 0;
    }

    // isFull returns whether size is equal to capacity
    public boolean isFull(){
        return size == DEFAULT_CAPACITY;
    }
    
    // count() returns size+1
    public int count(){
        return size+1;
    }
    
    /**          *
     *  privates *
     *           *
     *           */     

    // next() returns next element at logical index
    public int next(Integer i){
        return (i+1)%DEFAULT_CAPACITY;  
    }

    // prev() returns index at previous logical index
    public int prev(Integer i){
        return (i>0) ? i-1 : DEFAULT_CAPACITY-1;
    }

    // index() returns a logical index
    public int index(Integer i) throws CollectionException{
        if (i < 0 || i >= size){
            throw new CollectionException(ERR_MSG_INDEX);
        }
        return (front+i)%DEFAULT_CAPACITY;
    }

    // toString returns nicely formatted arr
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i=0;i<size;i++){
            if (i != 0){
                sb.append(", ");
            }
            try {
                sb.append(arr[index(i)]);
            } catch (CollectionException e){
                System.out.println(e);
                return "";
            }
        }
        sb.append(")");
        return sb.toString();
    } 
}   


/*******  Interfaces and Errors *******/

// CollectionException ...
class CollectionException extends Exception {
    public CollectionException(String msg) {
         super(msg);
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
}

// Stack ...
interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
    public Stack<T> reverse() throws CollectionException;
}

// Deque ...
interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}

// Sequence ...
interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
}

