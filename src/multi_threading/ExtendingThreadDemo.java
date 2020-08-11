package multi_threading;

public class ExtendingThreadDemo extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("value of the current thread " + Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        ExtendingThreadDemo ob1 = new ExtendingThreadDemo();
        ExtendingThreadDemo ob2 = new ExtendingThreadDemo();
        ob1.setName("A");
        ob1.start();
        ob2.setName("B");
        ob2.start();
    }
}
