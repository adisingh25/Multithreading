import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private Queue<Integer> q;

    private int capacity;

    public BlockingQueue(int cap) {

        q = new LinkedList<>();
        capacity = cap;
    }

    public boolean add(Integer item) {

        synchronized (q) {

            while(q.size() == capacity) {  // very important to know that we should use 'while' instead of 'if'
                // reason for this is, what if there are multiple threads that are in waiting state to add a value
                // in the queue, and after some time, now there is one space available, but since we had done 'notifyAll()'
                // all the threads will be notified about that available space. Imagine thread 'adder1' gets the chance,
                // to add the value, it adds the value and sends out a 'true' value. Now, since 'adder2' was also
                // notified it will also try to add the value and start its execution from the point where it had left earlier
                // if we use 'if' that means we don't check 'q.size() == capacity' in future from that point in the code and
                // when the adder2 thread will try to add the value, it will fail and throw error,
                // if we use 'while' , the loop will ensure that all the threads whose execution was stopped inside that block
                // will at least check once more, if the space ois available or not. If not, it will again go in the 'waiting'
                // state.

                try {
                    q.wait();   // we relinquish the lock here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            q.add(item);

            // notify that now, we have some value in the queue now.
            q.notifyAll();  // notifies all the threads in execution , we also have "notify()" which will just
                            // notify the very specific thread which we might have referred there.
            return true;

        }
    }

    public Integer remove() {

        synchronized (q) {

            while(q.size() == 0) {
                // if there is nothing to be removed from the queue, this thread will have to wait for some time,
                // so that, some other thread will add some value to the queue, which this thread can remove later on

                try {
                    q.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Integer valueToBeReturned = q.peek();
            q.notifyAll();  // notifying all the other threads that, we have removed some value from the value
                            // in case some thread is waiting to add a value and was not able to add
                             // because the queue size was full, this will help them to recover from that waiting state,
                            // because now we have some space available in the queue to add values.
            q.poll();
            return valueToBeReturned;

        }
    }



}
