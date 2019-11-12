package DataStructures.linked_list;

import java.util.Random;

/**
 * Use this http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 * for reference
 */

class SkipListNew {
    private class SkipListEntry{
        String key;
        Integer value;
        SkipListEntry up, down, left, right;
        int index;
        static final String posInf = "+INF";
        static final String negInf = "-INF";
        SkipListEntry(String key, Integer value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "SkipListEntry{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    private SkipListEntry head, tail;
    private int n;          // number of entries in list
    private int h;          // height of the list
    private Random random;  //  Coin toss

    public SkipListNew(){
        SkipListEntry p1, p2;

        // create a plus infinity and negative infinity object
        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);


        // linking both infinity objects
        p1.right = p2;
        p2.left = p1;

        // Initialize head and tail
        head = p1;
        tail = p2;

        n = 0;     //   No entries in the skip list
        h = 0;     //   Height is 0

        random = new Random();  //  Make random object to simulate coin toss
    }

    /*
    If the key k is found in the skip list, findEntry(k) will return the reference
    to the entry containing the key k

    If the key k is not found in the Skip List, findEntry(k) will return the reference
    to the floorEntry(k) entry containing a key that is smaller than k
     */
    public SkipListEntry findEntry(String k){
        SkipListEntry p = head;
        while(true){
            while(!p.right.key.equals(SkipListEntry.posInf) &&
                    p.right.key.compareTo(k) <= 0){
                p = p.right;
            }
            if(p.down != null) p = p.down;
            else break;
        }
        return p;
    }

    public Integer getKey(String k){
        SkipListEntry p = findEntry(k);
        if(k.equals(p.key)) return p.value;
        return null;
    }

    public void createEmptyLayer(){
        SkipListEntry p = new SkipListEntry(SkipListEntry.negInf, null);
        SkipListEntry q = new SkipListEntry(SkipListEntry.posInf, null);
        p.right = q;
        q.left = p;

        p.down = head;
        head.up = p;

        q.down = tail;
        tail.up = q;

        head = p;
        tail = q;

        // increasing the current height
        this.h++;
    }
    public Integer put(String k, Integer value){
        SkipListEntry p = findEntry(k);

        if(k.equals(p.key)){
            Integer oldValue = p.value;
            p.value = value;
            return oldValue;
        }

        SkipListEntry q = new SkipListEntry(k, value);
        q.left = p;
        q.right = p.right;
        p.right.left = q;
        p.right = q;

        int i = 0;
        while(random.nextDouble() < 0.5){
            // Check if we need to increase the height of the -INF and +INF pillars
            if(i >= this.h){
                createEmptyLayer();
            }
            while(p.up == null) p = p.left;
            p = p.up;
            SkipListEntry e = new SkipListEntry(k, null); // don't need a value
            e.down = q;
            q.up = e;
            e.left = p;
            e.right = p.right;
            p.right.left = e;
            p.right = e;

            q = e;
            i = i + 1;
        }
        this.n++;
        return null; // no old value
    }
    public Integer remove(String k){
        SkipListEntry p = findEntry(k);
        if(!k.equals(p.key)) return null;
        Integer removedValue = p.value;
        while(p != null){
            p.left.right = p.right;
            p.right.left = p.left;

            // destroying values
            p.left = p.right = p.up = null;
            p.value = null;
            p.key = null;
            p = p.down;
        }
        this.n--;
        return removedValue;
    }
    @Override
    public String toString(){
        SkipListEntry start = head;
        while(start.down != null) start = start.down;
        StringBuilder br = new StringBuilder();
        while(start != null){
            br.append("[ " + start.key + " " + start.value + " " + start.index + " ]").append("-->");
            start = start.right;
        }
        return br.toString();
    }
    public void printAllLayers(){
        SkipListEntry p = head.down;
        StringBuilder outer = new StringBuilder();
        while(p != null){
            StringBuilder inner = new StringBuilder();
            SkipListEntry temp = p;
            while(temp != null){
              inner.append("[ " + temp.key + " " + temp.value + " " + temp.index + " ]").append("-->");
              temp = temp.right;
            }
            outer.append(inner).append("\n");
            p = p.down;
        }
        System.out.println(outer);
    }
}
public class SkipListDemo2{
    public static void main(String[] args) {
        SkipListNew obj = new SkipListNew();
        char[] arr = {'A', 'B', 'C'};
        for(int i = 0; i < 10; i++){
            obj.put(new String(arr), 1);
            for(int k = 0; k < arr.length; k++) arr[k] += 1;
        }
        obj.printAllLayers();
    }
}