package data_structures.linked_list;
/**
 * This is an implementation of SkipList.
 * -    In this randomised layers with fast pointers are used for searching the node.
 * -    Each node has two pointers next and down as shown below
 * -    Also each layer have level starting from max level to level 0
 * -    A node can only be added if it's level is greater than or equal to current level
 * -    Randomness is used to decide from which layer a node is going to be added
 *
 * For searching
 * -    A node to be searched in the topmost layer
 * -    we check while traversing if traversed node does not overshoot the value
 *      of the node that is being searched
 * -    If it overshoots then we move down a layer and search in that layer
 * -    The same process is repeated again and again until the value is found.
 *
 * Null----------------> level 3
 *  \
 * Null---1--------4---> level 2
 *   \    \        \
 * Null---1-----3--4---> level 1
 *   \    \     \  \
 * Null---1--2--3--4---> level 0
 */
import java.util.Random;
 class SkipList<T extends Comparable<? super T>, U>{
    private class Node{
    public T key;
    public U value;
    public long level;
    public Node next;
    public Node down;
    public Node(T key, U value, long level, Node next, Node down){
            this.key = key;
            this.value = value;
            this.level = level;
            this.next = next;
            this.down = down;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", level=" + level +
                    ", next=" + "[" + this.next + "]" +
                    ", down=" + "[" + this.down + "]" +
                    '}';
        }
    }

    private Node head;
    private Random _random;
    private long size;
    private double _p;

    private long level(){
        long level = 0;
        while(level <= this.size && this._random.nextDouble() < this._p){
            level++;
        }
        return level;
    }

    public SkipList(){
        this.head = new Node(null, null, 0, null, null);
        this._random = new Random();
        this.size = 0;
        this._p = 0.5;
    }
    public void add(T key, U value){
        long level = level();
        // if level is greater than create an upper layer
        if(level > this.head.level){
            head = new Node(null, null, level, null, this.head);
        }
        Node cur = this.head;
        Node last = null;
        while(cur != null){
            /**
             * If the next node is null or the next node's key is greater than
             * current key, then only check if it can be added with current level
             *
             * Otherwise if node's key is similar to that of current key
             * then just update the value
             *
             * else go for next node
             */
            if(cur.next == null || cur.next.key.compareTo(key) > 0){
                if(level >= cur.level){
                    Node n = new Node(key, value,cur.level, cur.next, null);
                    if(last != null){
                        last.down = n;
                    }
                    cur.next = n;
                    last = n;
                }
                cur = cur.down;
                continue;
            }
            else if(cur.next.key.equals(key)){
                cur.next.value = value;
                return;
            }
            cur = cur.next;
        }
        this.size++;
    }
    public boolean containsKey(T key){
        return get(key) != null;
    }
    public U remove(T key){
        U value  = null;
        Node cur = this.head;
        while(cur != null){
            /**
             * if the next node is null or next node is greater than equal to
             * then only you can remove the node. Also, remove it corresponding
             * below node
             *
             * Otherwise check for next node
             */
            if(cur.next == null || cur.next.key.compareTo(key) >= 0){
                if(cur.next != null && cur.next.key.equals(key)){
                    value  = cur.next.value;
                    cur.next = cur.next.next;
                }
                cur = cur.down;
                continue;
            }
            cur = cur.next;
        }
        this.size--;
        return value;
    }
    public U get(T key){
        Node cur = head;
        while(cur != null){
            /**
             * For searching the node start from upper layer,
             * if the next node is null or next node's key is greater than
             * current node then go to below node.
             *
             * Otherwise if the node is found then return it
             *
             * else continue to next node
             */
            if(cur.next == null || cur.next.key.compareTo(key) > 0){
                cur = cur.down;
                continue;
            } else if(cur.next.key.equals(key)){
                return cur.next.value;
            }
            cur = cur.next;
        }
        return null;
    }


    @Override
    public String toString() {
        return this.head + "";
    }
}
public class SkipListDemo{
    public static void main(String[] args) {
        SkipList<Integer, Integer> s = new SkipList<>();
        s.add(1, 2);
        s.add(2, 2);
        s.add(3, 2);
        s.add(4, 2);
        s.add(5, 2);
        s.add(6, 2);
        s.add(7, 2);
        s.add(8, 2);
        s.add(9, 2);
        s.add(10, 2);
        System.out.println(s);
        System.out.println(s.get(1));
    }
}