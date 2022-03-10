package multi_threading.resource_sharing.dto;

public class DecrementingThread extends Thread {
    private final InventoryCounter inventoryCounter;

    public DecrementingThread(InventoryCounter inventoryCounter) {
        this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            this.inventoryCounter.decrement();
        }
    }
}
