package DataStructures.linked_list;

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
        if(level > this.head.level){
            head = new Node(null, null, level, null, this.head);
        }
        Node cur = this.head;
        Node last = null;
        while(cur != null){
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
}
public class SkipListDemo{
    public static void main(String[] args) {
        SkipList<Integer, Integer> s = new SkipList<>();
        s.add(1, 100);
        System.out.println(s.get(1));
        java.util.concurrent.ConcurrentSkipListMap<Integer, Integer> arr;
    }
}