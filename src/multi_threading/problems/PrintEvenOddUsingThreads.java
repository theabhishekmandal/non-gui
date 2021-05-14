package multi_threading.problems;

public class PrintEvenOddUsingThreads{
    private int number = 1;
    private static final int MAX = 20;

    private void printEven() {
        synchronized (this) {
            while(number < MAX) {
               while ((number & 1) == 1) {
                   try {
                       wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println(Thread.currentThread().getName() +  " " + number);
               number++;
               notifyAll();
            }
        }
    }

    private void printOdd() {
        synchronized (this) {
            while(number < MAX) {
                while ((number & 1) == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        var obj = new PrintEvenOddUsingThreads();
        Runnable even = obj::printEven;
        Runnable odd = obj::printOdd;
        var first = new Thread(even, "even thread");
        var two = new Thread(odd, "odd thread");

        first.start();
        two.start();
    }
}