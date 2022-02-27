package multi_threading.thread_join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is an extension to previous example. In this with the help of join method
 * we will wait till 2 secs to finish the job and after that the main thread will exit.
 * <p>
 * But, here after the main thread finishes, we can see that the application is still running
 * because we didn't interrupt the running thread
 */
public class MultiThreadingWithThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Long> list = Arrays.asList(333L, 1234L, 5467L, 6668L, 92340L, 10000000L);
        List<FactorialThread> factorialThreads = new ArrayList<>(list.size());

        // creating Factorial Thread
        for (Long number : list) {
            factorialThreads.add(new FactorialThread(number));
        }

        // starting all the threads
        for (FactorialThread thread : factorialThreads) {
            thread.start();
        }

        // waiting almost 2 secs to finish the job
        for (FactorialThread factorialThread : factorialThreads) {
            factorialThread.join(2000);
        }

        // checking whether finished or not
        for (FactorialThread factorialThread : factorialThreads) {
            if (factorialThread.isFinished()) {
                System.out.println("factorial of number " + factorialThread.getInputNumber()
                        + " is " + factorialThread.getResult());
            } else {
                System.out.println("factorial of number " + factorialThread.getInputNumber()
                        + " is being calculated");
            }
        }

    }
}
