package multi_threading.lock_free_techniques;

import multi_threading.resource_sharing.dto.DecrementingThread;
import multi_threading.resource_sharing.dto.IncrementingThread;

public class InventoryCounterThreadSafeWithAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounterNew inventoryCounterNew = new InventoryCounterNew();
        var decrementingThread = new DecrementingThread(inventoryCounterNew);
        var incrementingThread = new IncrementingThread(inventoryCounterNew);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("Value of items when run in parallel = " + inventoryCounterNew.getItems());
    }
}
