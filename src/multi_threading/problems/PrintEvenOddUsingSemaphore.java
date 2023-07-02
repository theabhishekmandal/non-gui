package multi_threading.problems;

import java.util.concurrent.Semaphore;

public class PrintEvenOddUsingSemaphore {
    private int number = 1;
    private static final int MAX = 2;

    private final Semaphore first = new Semaphore(1);
    private final Semaphore second = new Semaphore(0);

    private void printEven() {
        while(number < MAX) {
            try {
                second.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +  " " + number);
            number++;
            first.release();
        }
    }

    private void printOdd() {
        while(number < MAX) {
            try {
                first.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " " + number);
            number++;
            second.release();
        }
    }
    public static void main(String[] args) {
        var obj = new PrintEvenOddUsingSemaphore();
        Runnable even = obj::printEven;
        Runnable odd = obj::printOdd;
        var first = new Thread(even, "even thread");
        var two = new Thread(odd, "odd thread");

        first.start();
        two.start();
    }
}

/**
 * first - print 1 -> 2 -> release second ->
 * second - suspend -> print 2
 */