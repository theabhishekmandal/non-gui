package dataStructures.linkedList;

import dataStructures.linkedList.node.CircularLinkedList;
import dataStructures.linkedList.node.DoublyLinkedList;

import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.System.*;

/**
 * Unrolled linked list stores multiple elements in each node.
 * And Each node is a collection of linked list.
 *
 * Conditions:
 * 1. All the outerBlocks except the last outerBlock should contain ceil(root(n)) elements. i.e
 *      except the last outerNode every node should contain ceil(root(n))
 *      eg: [[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12, 13, 14, 15]]] where n is 15
 * 2. Number of outerBlocks should be no more than floor(root(n)).
 * @param <T>
 */
class UlinkedList<T>{
    private DoublyLinkedList<CircularLinkedList<T>> outerList;
    private LinkedList<LinkedList<T>> list;
    private int size;
    private int innerblocks;
    private int outerblocks;

    private static long start, stop;
    private static void debug(Object... a){
        System.err.println(Arrays.deepToString(a));
    }
    static void starttime(){start = currentTimeMillis();}
    static void stoptime(){stop = currentTimeMillis();}
    static void gettime(){err.println(((double)(stop - start) / 1000.0) + " seconds");}

    public UlinkedList(Boolean bool){
        if(bool) outerList = new DoublyLinkedList<>();
        else list = new LinkedList<>();
    }

    public int getInnerblocks(){
        return this.innerblocks;
    }

    public int getOuterblocks(){
        return this.outerblocks;
    }

    // this one is slow
    public void add(T value){
        size++;

        // getting the innerList and outerList size
        innerblocks = getInnerblocks(size);
        outerblocks = getOuterblocks(size);

        // adding a new node at the outerList if it's size is less
        if(outerList.getSize() < outerblocks){
            outerList.addLast(new CircularLinkedList<>());
        }

        /* adding a outerList node first at the end and not the beginning
        eg: [1,2]-->[3,4] now if you want to add 5 then add it to the end of the last node
        [1, 2]-->[3, 4, 5]
         */
        outerList.getTail().getData().addLast(value);

        // adjusting the nodes such that
        /*
            1. All the outerBlocks except the last outerBlock should contain ceil(root(n)) elements. i.e
                except the last outerNode every node should contain ceil(root(n))
                eg: [[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12, 13, 14, 15]]] where n is 15
             2. Number of outerBlocks should be no more than floor(root(n)).
         */
        for(DoublyLinkedList.node<CircularLinkedList<T>> temp = outerList.getHead(); temp.getData() != null; temp = temp.getNext()){
            if(temp.getData().getSize() < innerblocks){
                DoublyLinkedList.node<CircularLinkedList<T>> succOuterListNode = temp.getNext();
                while(temp.getData().getSize() != innerblocks)
                    temp.getData().addLast(succOuterListNode.getData().deleteFirst().getData());
            }
            else if(temp.getData().getSize() > innerblocks){
                DoublyLinkedList.node<CircularLinkedList<T>> succOuterListNode = temp.getNext();
                while(succOuterListNode.getData().getSize() != innerblocks)
                    succOuterListNode.getData().addFirst(temp.getData().deleteLast().getData());
            }
        }
    }

    // this one is faster
    public void add1(T data){
        size++;
        innerblocks = getInnerblocks(size);
        outerblocks = getOuterblocks(size);
        if(list.size() < outerblocks){
            list.addLast(new LinkedList<>());
        }
        list.getLast().add(data);
        for(int i = 0; i < list.size() - 1; i++){
            LinkedList<T> innerTempList = list.get(i);
            LinkedList<T> innerNextList = list.get(i + 1);
            if(innerTempList.size() < innerblocks){
                while(innerTempList.size() != innerblocks){
                    innerTempList.addLast(innerNextList.removeFirst());
                }
            }
            else if(innerTempList.size() > innerblocks){
                while(innerTempList.size() != innerblocks){
                    innerNextList.addFirst(innerTempList.removeLast());
                }
            }
        }
    }

    public T get(int index){
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        int innerblockSize = getInnerblocks();
        int outerblockSize = getOuterblocks();

        // getting in which outerBlock will the node lie, so it can be found by
        // ceil(k / innerblocksSize)
        int mod = (int) Math.ceil((double)index / (double)innerblockSize);

        /* there can be a possibility when mod is greater than outerBlock size
        For eg: [[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12, 13, 14, 15]]]
        here for ceil(15 / 4) = 4 which is greater than 3

        then we subtract by 2 if true and subtract by 1 if false

        we subtract by 1 so as to ensure 0 based indexing.
        Also we subtract if the flag is true because we want to ensure 15 and 12
        should have the same block number
        */
        boolean isGreaterThanOuterblockSize = mod > outerblockSize;
        int indexFound = (isGreaterThanOuterblockSize)? mod - 2 : mod - 1;

        /*
        now to find where inside the outerBlock does the element resides.

        if remainder is 0 then we add the mod value to it. so as to ensure
        we don't get this For eg: [1, 2, 3, 4] after taking remainder values.
        remainder list is [1, 2, 3, 0], 0 comes after so we add the mod value.

        Also subtract by 1 for zero based indexing.
         */
        int rem = index % innerblockSize;
        rem = (rem == 0) ? innerblockSize : rem;
        int increment = (isGreaterThanOuterblockSize) ? mod : 0;
        rem += increment - 1;

        return list.get(indexFound).get(rem);
        //return  "[" + "Index = " + index + ", OuterBlockIndex = " + indexFound + ", InnerBlockIndex = " + rem + "]";
    }
    private int getInnerblocks(int size){
        return (int)Math.ceil(Math.sqrt(size));
    }
    private int getOuterblocks(int size){
        return (int)Math.floor(Math.sqrt(size));
    }
    @Override
    public String toString(){
        return "[" + ((list == null) ? (outerList == null) ? "" : outerList : list) + "]";
    }
}
public class UnrolledLinkedList {
    public static void main(String[] args) {
        UlinkedList<Integer> list = new UlinkedList<>(false);
        StringBuilder br = new StringBuilder();
        UlinkedList.starttime();

        int cal = 15;
        for(int i = 1; i <= cal; i++) {
            list.add1(i);
            br.append(list).append("\n");
        }
        UlinkedList.stoptime();
        UlinkedList.gettime();
        System.out.println(br + "\n");
        System.out.println(list.getInnerblocks() + " " + list.getOuterblocks());

        UlinkedList.starttime();
        int ans = list.get(cal);
        UlinkedList.stoptime();
        UlinkedList.gettime();
        System.out.println(ans);
    }
}
