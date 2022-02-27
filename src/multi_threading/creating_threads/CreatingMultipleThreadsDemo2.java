package multi_threading.creating_threads;

/**
 * In this program we will create multiple threads by using runnable interface
 * Also in this program a single object can have many thread objects as compared to
 * the previous program
 */
public class CreatingMultipleThreadsDemo2 implements Runnable {

    public static void main(String... args) throws Exception {
        CreatingMultipleThreadsDemo2 ob1 = new CreatingMultipleThreadsDemo2();

        Thread t1 = new Thread(ob1, "A");
        Thread t2 = new Thread(ob1, "B");
        Thread t3 = new Thread(ob1, "C");

        t1.start();
        t2.start();
        t3.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("this is main thread " + i);
        }
        Thread.sleep(10000);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("the value of the current thread is " + Thread.currentThread().getName() + " " + i);
        }
    }
}
