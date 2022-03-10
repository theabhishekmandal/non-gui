package multi_threading.resource_sharing.dto;

public class InventoryCounter {
    protected int items;

    public void increment() {
        ++this.items;
    }

    public void decrement() {
        --this.items;
    }

    public int getItems() {
        return this.items;
    }
}
