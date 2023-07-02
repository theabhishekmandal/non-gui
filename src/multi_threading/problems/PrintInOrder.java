package multi_threading.problems;

/**
 * Given a class Foo whose object will be shared among three threads.
 * Where one Thread will call first method, second thread will call second method and third thread will call third method.
 * In which ever order the threads are called, it should be printed in order.
 * For eg: Three threads are running in parallel, then output should be
 *         print first
 *         print second
 *         print third
 */
public class PrintInOrder {
    static class Foo {

        private boolean first;
        private boolean second;
        Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (this) {
                printFirst.run();
                first = true;
                notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (this) {
                while (!first) {
                    wait();
                }
                printSecond.run();
                second = true;
                notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (this) {
                while (!second) {
                    wait();
                }
                printThird.run();
                notifyAll();
            }
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
