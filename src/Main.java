public class Main {

    public static void main(String [] args) {

        System.out.println("Hello Multithreading");

//        Thread thread1 = new Thread1("Thread 1");
//        thread1.setDaemon(true); // marking it as daemon thread, comment this line to see the output
//        thread1.start();


        // Another Method to create a thread, this is a better method as here we implement the Runnable interface
        // This is better because, in java we don't have concept of multiple inheritance, therefore, if our class will
        // extend the thread class then, we won't be able to extend any another class with it, but that's not the case with
        // an interface kind of thing.
//        Thread thread2 = new Thread(new Thread2(), "Thread 2");

        // lambda implementation
//        Thread thread = new Thread(()->
//        {
//            for(int i =0; i<10; i++) {
//                System.out.println("Inside the " + Thread.currentThread().getName() + " and the value is "+ i);
//            }
//        }, "new thread");
//
//        thread.start();


//        Stack myStack = new Stack(5);
//
//        new Thread(() -> {
//            int counter = 0;
//            while(counter < 10) {
//                System.out.println("Pushed " + myStack.push(counter));
//                counter++;
//            }
//        }, "Pusher").start();
//
//        new Thread(() -> {
//            int counter = 0;
//            while(counter < 10) {
//                System.out.println("Poping out the value " + myStack.pop());
//                counter++;
//            }
//        }, "Poper").start();


//        Thread thread = new Thread (() -> {
//            System.out.println(Thread.currentThread().getName());
////            Thread.currentThread().setPriority(10); //this is an advisory method for the JVM, hence JVM is in no way
//                                                      // obliged to honor.
//        }, "Thread will join");
//
//        thread.start();
//
//        try {
//            thread.join();  // this block the 'main' thread from execution, this will ensure that , we first execute this thread
//                            // and then execute the main thread
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(thread.getPriority()); // return the priority of the thread.


        //how to create a deadlock

        String lock1 = "aditya";
        String lock2 = "singh";

        Thread thread1 = new Thread( () -> {

            synchronized (lock1) {   // tries to acquire the first lock
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {    // tries to acquire the second lock
                    System.out.print(Thread.currentThread().getName());
                }
            }
        }, "thread 1 ");



        // to create a deadlock, we just need to reverse the sequence in which locks were acquired.
        // to solve the problem, we should ensure that the locks are acquired in the same order
        // i.e first lock1 then lock2 in this case

        Thread thread2 = new Thread( () -> {

            synchronized (lock2) {   // tries to acquire the first lock
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {    // tries to acquire the second lock
                    System.out.print(Thread.currentThread().getName());
                }
            }

        }, "thread 2");

        thread1.start();
        thread2.start();


        System.out.println("Bye Multithreading");


    }
}
