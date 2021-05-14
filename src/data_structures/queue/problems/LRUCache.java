package data_structures.queue.problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Cache {
    private final Deque<Integer> queue;
    private final Set<Integer> set;
    private final int size;

    public Cache(int size) {
        this.size = size;
        queue = new ArrayDeque<>();
        set = new HashSet<>();
    }

   public void refer(int page) {
        if (!set.contains(page)) {
            if (queue.size() == size) {
                var first = queue.removeFirst();
                set.remove(first);
            }
        } else {
            queue.remove(page);
        }
        queue.addLast(page);
        set.add(page);
   }

   @Override
   public String toString() {
        return queue.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
   }

}

public class LRUCache {
    public static void main(String[] args) {
        var lru = new Cache(4);
        lru.refer(1);
        lru.refer(2);
        lru.refer(3);
        lru.refer(1);
        lru.refer(4);
        lru.refer(5);
        lru.refer(2);
        lru.refer(2);
        lru.refer(1);

        System.out.println(lru);
    }
}
