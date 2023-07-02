package multi_threading.problems;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * ZeroEvenOdd obj is shared among three threads. one threads print zero, second thread prints odd and third even.
 * Given a number n, print in such order
 * 01020304 where total length of the digits printed should be 2 n.
 *
 * If n = 2
 * 0102
 */
public class PrintZeroEvenOdd {
    static class ZeroEvenOdd {
        private final int n;
        private final Semaphore one = new Semaphore(1);
        private final Semaphore two = new Semaphore(0);
        private final Semaphore three = new Semaphore(0);

        private final int maxSize;
        private int size;

        private boolean flag;

        private int num;

        ZeroEvenOdd(int n) {
            this.n = n;
            this.maxSize = 2 * n;
            this.num = 1;
        }

        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (size < maxSize) {
                one.acquire();

                // break if size is modified by different thread
                if (size >= maxSize) {
                    break;
                }
                printNumber.accept(0);
                ++size;
                if (!flag) {
                    two.release();
                } else {
                    three.release();
                }
                flag = !flag;
            }
            this.releaseAll();
        }

        private void releaseAll() {
            // release all locks after processing
            one.release();
            two.release();
            three.release();
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while (size < maxSize) {
                three.acquire();
                if (size >= maxSize) {
                    break;
                }
                printNumber.accept(num);
                ++num;
                ++size;
                one.release();
            }
            this.releaseAll();
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while (size < maxSize) {
                two.acquire();
                if (size >= maxSize) {
                    break;
                }
                printNumber.accept(num);
                ++num;
                ++size;
                one.release();
            }
            this.releaseAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd obj = new ZeroEvenOdd(10);
        IntConsumer printNumber = x -> System.out.print(x);
        Thread first = new Thread(() -> {
            try {
                obj.zero(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread two = new Thread(() -> {
            try {
                obj.odd(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread three = new Thread(() -> {
            try {
                obj.even(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        first.start();
        two.start();
        three.start();

        first.join();
        two.join();
        three.join();
    }
}
