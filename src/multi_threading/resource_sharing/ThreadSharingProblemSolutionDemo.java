package multi_threading.resource_sharing;

import multi_threading.resource_sharing.dto.DecrementingThread;
import multi_threading.resource_sharing.dto.IncrementingThread;
import multi_threading.resource_sharing.dto.InventoryCounterThreadSafe;

/**
 * To solve the problem of concurrent modification by threads in previous example.
 * -   Critical sections are defined inside the synchronized block
 * -   Only one thread at a time can update the value.
 * <p>
 * See InventoryCounterThreadSafe.
 */
public class ThreadSharingProblemSolutionDemo {
    public static void main(String[] args) throws InterruptedException {
        var inventoryCounter = new InventoryCounterThreadSafe();
        var decrementingThread = new DecrementingThread(inventoryCounter);
        var incrementingThread = new IncrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("Value of items when run in parallel = " + inventoryCounter.getItems());
    }
}
