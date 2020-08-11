package multi_threading;
/**
 * In this program we are creating multi threads by implementing runnable interface
 * Also in this we create two threads by using two objects means every object has at most one thread
 */
public class CreatingMultipleThreads implements Runnable {
    CreatingMultipleThreads(String s) {
        Thread thread = new Thread(this, s);
        thread.start();
    }

    @Override
    public void run(){
        for(int i = 0; i < 5; i++) {
            System.out.println("this is the value of the current thread " + Thread.currentThread().getName() + " " + i);
        }
    }
    public static void main(String[] args) {
        new CreatingMultipleThreads("A");
        new CreatingMultipleThreads("B");
    }
}
