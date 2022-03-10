package multi_threading.resource_sharing;

import multi_threading.resource_sharing.dto.DecrementingThread;
import multi_threading.resource_sharing.dto.IncrementingThread;
import multi_threading.resource_sharing.dto.InventoryCounter;

/**
 * This is an example of Problems that we can face while sharing resource in multithreaded environment.
 * -    Here InventoryCounter is the object which is shared between two threads DecrementingThread and
 * IncrementingThread.
 * -    IncrementingThread increases the value and DecrementingThread decreases the value of InventoryCounter
 * -    On sequential running the final value of InventoryCounter object is 0. But on running in parallel
 * we can see that the final value is not 0.
 * <p>
 * -    The core problem here is
 * -   InventoryCounter is shared between two threads.
 * -   items++ and items-- are happening simultaneously.
 * -   and these two are not atomic operations.
 * <p>
 * -    items++ is a 3-step operation
 * -   items <- 0
 * -   items + 1 = 1
 * -   items <- 1
 * <p>
 * - See next ThreadSharingProblemSolutionDemo
 */
public class ThreadSharingProblemDemo {
    public static void main(String[] args) throws InterruptedException {
        sequential();
        parallel();
    }

    private static void sequential() throws InterruptedException {
        var inventoryCounter = new InventoryCounter();
        var decrementingThread = new DecrementingThread(inventoryCounter);
        var incrementingThread = new IncrementingThread(inventoryCounter);

        incrementingThread.start();
        incrementingThread.join();

        decrementingThread.start();
        decrementingThread.join();

        System.out.println("Value of items when run sequentially = " + inventoryCounter.getItems());
    }

    private static void parallel() throws InterruptedException {
        var inventoryCounter = new InventoryCounter();
        var decrementingThread = new DecrementingThread(inventoryCounter);
        var incrementingThread = new IncrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("Value of items when run in parallel = " + inventoryCounter.getItems());
    }

}
