package multi_threading.creating_threads;

public class ExtendingThreadDemo extends Thread {

    public static void main(String[] args) {
        var ob1 = new ExtendingThreadDemo();
        var ob2 = new ExtendingThreadDemo();
        ob1.setName("A");
        ob1.start();
        ob2.setName("B");
        ob2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("value of the current thread " + Thread.currentThread().getName() + " " + i);
        }
    }
}
