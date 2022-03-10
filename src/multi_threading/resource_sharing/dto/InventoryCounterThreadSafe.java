package multi_threading.resource_sharing.dto;

public class InventoryCounterThreadSafe extends InventoryCounter {
    private final Object lock = new Object();

    @Override
    public void increment() {
        synchronized (lock) {
            this.items++;
        }
    }

    @Override
    public void decrement() {
        synchronized (lock) {
            this.items--;
        }
    }
}
