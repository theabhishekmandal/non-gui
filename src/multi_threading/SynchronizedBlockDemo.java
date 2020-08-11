package multi_threading;

/**
 * This program is an example of synchronised block example
 * that is in this we have use the synchronised block because
 * sometimes it is not possible to add synchronised keyword to the methods of the classes
 * which are being provided by the third party vendors
 * As the code is not accessible by us , so we use the the synchronised block to synchronise them
 */
public class SynchronizedBlockDemo implements Runnable {
    void call() {
        System.out.print("["+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.print("]");

    }
    public void run() {
        synchronized (this) {
            this.call();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlockDemo ob = new SynchronizedBlockDemo();
        Thread one = new Thread(ob,"A");
        Thread two = new Thread(ob,"B");
        Thread three = new Thread(ob,"C" );

        one.start();
        two.start();
        three.start();

        one.join();
        two.join();
        three.join();

        System.out.println("hello world");
    }
}
