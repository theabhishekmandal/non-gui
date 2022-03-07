package multi_threading.thread_join;

import multi_threading.thread_join.dto.FactorialThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is an example showing that if any other thread other than main thread is executing a long
 * runing task then main thread won't wait for them to finish. See next example for solution.
 */
public class MultiThreadingWithoutThreadJoinDemo {
    public static void main(String[] args) {
        List<Long> list = Arrays.asList(333L, 1234L, 5467L, 6668L, 92340L);
        List<FactorialThread> factorialThreads = new ArrayList<>(list.size());

        // creating Factorial Thread
        for (Long number : list) {
            factorialThreads.add(new FactorialThread(number));
        }

        // starting all the threads
        for (FactorialThread thread : factorialThreads) {
            thread.start();
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
