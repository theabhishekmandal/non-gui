package multi_threading.lock_free_techniques;

import multi_threading.resource_sharing.dto.InventoryCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCounterNew extends InventoryCounter {
    protected AtomicInteger itemsNew = new AtomicInteger();
    
    @Override
    public void increment() {
        itemsNew.incrementAndGet();
    }
    
    @Override
    public void decrement() {
        itemsNew.decrementAndGet();
    }
    
    @Override
    public int getItems() {
        return itemsNew.get();
    } 
}
