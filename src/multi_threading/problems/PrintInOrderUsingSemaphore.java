package multi_threading.problems;


import java.util.concurrent.Semaphore;

/**
 * PrintInOrder.class but using Semaphore.
 */
public class PrintInOrderUsingSemaphore {
    static class Foo {

        Semaphore main = new Semaphore(1);
        Semaphore semaphoreOne = new Semaphore(0);
        Semaphore semaphoreTwo = new Semaphore(0);


        public void first(Runnable printFirst) throws InterruptedException {
            main.acquire();
            printFirst.run();
            semaphoreOne.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            semaphoreOne.acquire();
            printSecond.run();
            semaphoreTwo.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            semaphoreTwo.acquire();
            printThird.run();
            main.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable first = () -> System.out.println("print first");
        Runnable second = () -> System.out.println("print second");
        Runnable third = () -> System.out.println("print third");

        Foo foo = new Foo();
        Thread one = new Thread(() -> {
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(() -> {
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread three = new Thread(() -> {
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        one.start();
        three.start();
        two.start();

        one.join();
        two.join();
        three.join();
    }
}
