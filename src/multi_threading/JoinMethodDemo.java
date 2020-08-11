package multi_threading;

/**
 *here we are using the methods join() and isAlive()
 * isAlive() method returns true or false whether a thread is alive or not
 * if a thread has ended it's lifetime then it will return false
 * otherwise it will return true
 *
 *Whereas join() method waits until the thread is dead
 * For example :
 *         Suppose you created a thread and you call the start method of the thread
 *         also you printed something in the main method, the print method will be executed by the main thread
 *         But on running the program you will find that the main method has printed before the execution of the child threads
 *         TO AVOID this we use the join method
 *         JOIN METHOD WAITS UNTIL ALL THE THE THREADS ARE DEAD AND THEN IT WILL EXECUTE THE MAIN THREAD
 *         in the following program after calling the start method you call the join method
 *         it will wait until all the child threads are dead and then it will print the line
 */


public class JoinMethodDemo implements Runnable {
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("hello Abhishek");
        }
    }

    public static void main(String[] args) throws Exception {
        JoinMethodDemo ob = new JoinMethodDemo();
        Thread t1 = new Thread(ob);
        t1.start();
        t1.join();
        System.out.println("This is printed by the main thread and not the child thread");
    }
}