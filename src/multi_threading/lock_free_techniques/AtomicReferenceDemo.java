package multi_threading.lock_free_techniques;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Here in AtomicReference if the expectedValue matches the current value then it stores
 * the value.
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        String oldName = "oldName";
        String newName = "newName";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);

//        atomicReference.set("randomValue");
        if (atomicReference.compareAndSet(oldName, newName)) {
            System.out.println("New value is " + atomicReference.get());
        } else {
            System.out.println("Nothing has changed");
        }
    }
}
