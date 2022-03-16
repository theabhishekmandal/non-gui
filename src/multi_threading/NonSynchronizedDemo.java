package multi_threading;

/**
 * this program shows that the if a thread is currently  being executed
 * and a delay of few milliseconds is given then another thread starts getting executed
 * without the former thread being finished
 * <p>
 * This leads to non Synchronisation which can lead to deadlock
 * So, the next program will tell on how to use synchronisation.
 * Synchronisation will be achieved by using the keyword synchronized
 */
public class NonSynchronizedDemo implements Runnable{


//in the next program we will make this method synchronized
    void call() {
        System.out.print("[" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.print("]");
    }

    public void run() {
        this.call();
    }

    public static void main(String[] args) throws InterruptedException {
        NonSynchronizedDemo ob = new NonSynchronizedDemo();
        Thread one = new Thread(ob,"A");
        Thread two = new Thread(ob,"B");
        Thread three = new Thread(ob,"C");

        //starting the thread
        one.start();
        two.start();
        three.start();


       //waiting until all threads are dead
        one.join();
        two.join();
        three.join();
    }
}
