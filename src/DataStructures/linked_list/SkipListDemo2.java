package DataStructures.linked_list;

import java.util.Random;

/**
 * Use this http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 * for reference
 */

public class SkipListDemo2{
    private class SkipListEntry{
        String key;
        Integer value;
        SkipListEntry up, down, left, right;
        static final String posInf = "+INF";
        static final String negInf = "-INF";
        SkipListEntry(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    private SkipListEntry head, tail;
    private int n;          // number of entries in list
    private int h;          // height of the list
    private Random random;  //  Coin toss

    public SkipListDemo2(){
        SkipListEntry p1, p2;

        // create a plus infinity and negative infinity object
        p1 = new SkipListEntry(SkipListEntry.posInf, null);
        p2 = new SkipListEntry(SkipListEntry.negInf, null);

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


}
