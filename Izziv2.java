public class Izziv2{
    public static void main(String[] args){
        
        // Initialize a Stack, a Sequence and a Deque
        Stack<Integer> stack = new ArrayDeque<Integer>();
        Sequence<Integer> sequence = new ArrayDeque<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>(); 
        try {
            // Test arrays
            Integer[] front = new Integer[]{63, 62, 61, 60, 59};
            Integer[] back = new Integer[]{0, 1, 2, 3, 4};
            Integer[] linear = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            
            // Create a StringBuilder
            StringBuilder sb = new StringBuilder();
            
            // STACK //
            // TESTS //
            // PUSH 
            System.out.println("Stack: \n    Push: ");
            
            // fill a stack
            sb.append("\tIteration: ");
            for (int i=0;i<linear.length;i++){
                sb.append(i + ", " );
                stack.push(linear[i]);
            }

            // print results
            print("stack", sb.toString(), stack.toString(), "");

            // POP
            System.out.println("    Pop: ");
            
            // new StringBuilder
            StringBuilder newItems = new StringBuilder();
            sb = new StringBuilder();
            sb.append("\tIteration: ");
            newItems.append("\t    Items: ");
            
            // pop items from stack
            for(int i=0;i<5;i++){
                sb.append(i+", ");
                newItems.append((stack.pop()+", "));
            }
            
            // print results and top element
            print("stack", sb.toString(), stack.toString(), newItems.toString());
            System.out.println(String.format("\t%8s: %s", "Top", stack.top()));
            
            // SEQUENCE //
            // TESTS    //
            // ADD
            System.out.println("Sequence: \n    Add: ");

            // new StringBuilder
            sb = new StringBuilder();
            sb.append("\tIteration: ");

            // Add items to Sequence
            for (int i=0;i<linear.length;i++){
                sb.append(i + ", " );
                sequence.add(linear[i]);
            }

            // print result
            print("sequence", sb.toString(), sequence.toString(), "");

            // GET
            System.out.println("    Get: ");

            // new StringBuilder
            newItems = new StringBuilder();
            sb = new StringBuilder();
            sb.append("\tIteration: ");
            newItems.append("\t    Items: ");

            // Get items from Sequence
            for (int i=0;i<linear.length;i++){
                sb.append(i + ", " );
                newItems.append(sequence.get(i)+ ", ");
            }

            // print result
            print("sequence", sb.toString(), sequence.toString(), newItems.toString());
            
            // DEQUE //
            // TEST //
            // ENQUEUE
            System.out.println("Deque: \n    Enqueue: ");
            System.out.println(String.format("\t%8s: %s", "array", arrToString(back)));

             // new StringBuilder
            sb = new StringBuilder();
            sb.append("\tIteration: ");
 
            // Add items to Sequence
            for (int i=0;i<back.length;i++){
                sb.append(i + ", " );
                deque.enqueue(back[i]);
            }
 
            // print result
            print("sequence", sb.toString(), deque.toString(), "");

            // ENQUEUEFRONT
            System.out.println("    EnqueueFront: ");
            System.out.println(String.format("\t%8s: %s", "array", arrToString(front)));
 
            // new StringBuilder
            sb = new StringBuilder();
            sb.append("\tIteration:  ");
  
            // Add items to Sequence
            for (int i=0;i<front.length;i++){
                sb.append(front.length-i-1 + ",  " );
                deque.enqueueFront(front[i]);
            }
  
            // print result
            print("sequence", sb.toString(), deque.toString(), "");

            // DEQUEUE
            System.out.println("    Dequeue: ");
 
            // new StringBuilder
            newItems = new StringBuilder();
            sb = new StringBuilder();
            sb.append("\tIteration: ");
            newItems.append("\t    Items: ");
            // Add items to Sequence
            for (int i=0;i<3;i++){
                sb.append(i + ", " );
                newItems.append(deque.dequeue() + ", ");
            }
  
            // print result
            print("sequence", sb.toString(), deque.toString(), newItems.toString());

            // DEQUEUEFRONT
            System.out.println("    DequeueFront: ");
 
            // new StringBuilder
            newItems = new StringBuilder();
            sb = new StringBuilder();
            sb.append("\tIteration: ");
            newItems.append("\t    Items: ");
   
            // Add items to Sequence
            for (int i=0;i<3;i++){
                sb.append(i + ", " );
                newItems.append(deque.dequeueBack() + ", ");
            }
   
            // print result
            print("sequence", sb.toString(), deque.toString(), newItems.toString());        
            System.out.println("\tFront: " + deque.front() + "    Back: " + deque.back());

        } catch (CollectionException e) {
            // Error has been caught
            System.out.println(e);
        }
    }


    // Array to string
    public static String arrToString(Integer[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i=0;i<arr.length;i++){
            if (i != 0){
                sb.append(", ");
            }
                sb.append(arr[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    // Makes the printing easier
    public static void print(String type, String message, String item, String newItems){
        switch(type.toLowerCase()){
            case "stack": 
                System.out.println(message);
                if (newItems.length() > 0){
                    System.out.println(String.format("%s", newItems));
                }
                System.out.println(String.format("\t%8s: %s", "Stack", item));
                break;
            case "sequence": 
                System.out.println(message);
                if (newItems.length() > 0){
                    System.out.println(String.format("%8s", newItems));
                }
                if (item.length() > 0 ){
                    System.out.println(String.format("\t%8s: %s", "Sequence", item));
                }
                break;
            case "deque":   
                System.out.println(message);
                if (newItems.length() > 0){
                    System.out.println(String.format("%8s", newItems));
                }
                System.out.println(String.format("\t%8s: %s", "Deque", item));
                break;
        }
    }
}

/***********************
 *                     *
 *  Class ArrayDequeue *
 *                     *
 ***********************/     

class ArrayDeque<T> implements Stack<T>, Sequence<T>, Deque<T> {
    // vars
    private static final int DEFAULT_CAPACITY = 64;
    static Object[] arr;
    private int front, back, size;
    

    // Constructor
    public ArrayDeque(){
        front = 0;
        back = 0;
        size = 0;
        arr = (T[]) new Object[DEFAULT_CAPACITY]; 
    }
    
    /**                         *
     * This implements Deque<T> *
     *                          */

     // front() returns the first element of a queue
    public T front() throws CollectionException{
        if(isEmpty()){throw new CollectionException (ERR_MSG_EMPTY);}
        return (T) arr[front];
    }

    // back() returns the last element of the queue
    public T back() throws CollectionException{
        if(isEmpty()){throw new CollectionException (ERR_MSG_FULL);}
        return (T) arr[prev(back)];
    }

    // enqueue() adds x to the back of the queue
    public void enqueue(T x) throws CollectionException{
        if (isFull()){throw new CollectionException(ERR_MSG_FULL);}
        arr[back] = x;
        back = next(back);
        size ++;
    }

    // enqueueFront() adds x to the back of the queue
    public void enqueueFront(T x) throws CollectionException{
        if (isFull()){throw new CollectionException(ERR_MSG_FULL);}
        front = prev(front);
        arr[front] = x;
        size ++;
    }

    // dequeue() removes an element from the front of the queue
    public T dequeue() throws CollectionException{
        if (isEmpty()){throw new CollectionException(ERR_MSG_EMPTY);}
        Object o = arr[front];
        arr[front] = null;
        front = next(front);
        size --;
        return (T) o;
    }

    // dequeueBack() removes the element from the back of the queue
    public T dequeueBack() throws CollectionException{
        if (isEmpty()){throw new CollectionException(ERR_MSG_EMPTY);}
        Object o = arr[back];
        arr[back] = null;
        back = prev(back);
        size --;
        return (T) o;
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
        arr[size++] = x;
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

