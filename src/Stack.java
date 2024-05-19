public class Stack {

    private int [] array;
    private int stackTop;
    private Object lock;   // another way to provide a lock

    public Stack(int capacity) {
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();   // another way to provide a lock
    }


//     to make an entire method synchronized we can take this approach also
//     public synchronized boolean push(int element) {}
//     internally this ensures that a given point only 1 thread has access to all the synchronized methods in the class
//     internally it wraps the entire method in a synchronized block with 'this' as the lock, in case of non-static method
//     synchronized (this) { ............... }
    public boolean push(int element) {

//        synchronized (lock) {}  // in case we use the 'lock' created by us
        synchronized(Stack.class) {   //this is class lock example, this is used with a static method
            if (isFull()) return false;
            ++stackTop;

            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }


            // this is the place where, we are allowing multiple threads to access the state of our variable directly,
            // without acquiring a lock
            array[stackTop] = element;
            return true;
        }
    }

    public int pop() {
        synchronized(Stack.class) {
            if (isEmpty()) return Integer.MIN_VALUE;
            int obj = array[stackTop];
            array[stackTop] = Integer.MIN_VALUE;

            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            stackTop--;
            return obj;
        }
    }

    public boolean isEmpty() {
        return stackTop < 0;
    }


    public boolean isFull() {
        if(stackTop == array.length + 1) return true;
        return false;
    }
}
