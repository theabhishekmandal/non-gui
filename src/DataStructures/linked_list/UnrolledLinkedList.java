package DataStructures.linked_list;

import java.util.LinkedList;

class UlinkedList<T>{
    private DlinkedList<ClinkedList<T>> outerList;
    private LinkedList<LinkedList<T>> list;
    private int size;
    private int innerblocks;
    private int outerblocks;

    public UlinkedList(){
        outerList = new DlinkedList<>();
        list = new LinkedList<>();
    }

    public void add(T value){
        size++;
        innerblocks = getInnerblocks(size);
        outerblocks = getOuterblocks(size);
        if(outerList.getSize() < outerblocks){
            outerList.addLast(new ClinkedList<>());
        }
        outerList.getTail().data.addLast(value);
        for(DlinkedList.node<ClinkedList<T>> temp = outerList.getHead(); temp.next != null; temp = temp.next){
            if(temp.data.getSize() < innerblocks){
                DlinkedList.node<ClinkedList<T>> succOuterListNode = temp.next;
                while(temp.data.getSize() != innerblocks)
                    temp.data.addLast(succOuterListNode.data.deleteFirst().data);
            }
            else if(temp.data.getSize() > innerblocks){
                DlinkedList.node<ClinkedList<T>> succOuterListNode = temp.next;
                while(succOuterListNode.data.getSize() != innerblocks)
                    succOuterListNode.data.addFirst(temp.data.deleteLast().data);
            }
        }
    }


    public void add1(T data){
        size++;
        innerblocks = getInnerblocks(size);
        outerblocks = getOuterblocks(size);
        if(list.size() < outerblocks){
            list.addLast(new LinkedList<>());
        }
        list.getLast().add(data);
        for(int i = 0; i < list.size() - 1; i++){
            if(list.get(i).size() < innerblocks){
                while(list.get(i).size() != innerblocks)
                    list.get(i).addLast(list.get(i + 1).removeFirst());
            }
            else if(list.get(i).size() > innerblocks){
                while(list.get(i).size() != innerblocks){
                    list.get(i + 1).addFirst(list.get(i).removeLast());
                }
            }
        }
    }

    public void find(int index){

    }
    private int getInnerblocks(int size){
        return (int)Math.ceil(Math.sqrt(size));
    }
    private int getOuterblocks(int size){
        return (int)Math.floor(Math.sqrt(size));
    }
    @Override
    public String toString(){
        return "[" + list + "]";
    }
}
public class UnrolledLinkedList {
    public static void main(String[] args) {
        UlinkedList<Integer> list = new UlinkedList<>();
        for(int i = 1; i <= 16; i++)
            list.add1(i);
        System.out.println(list);
    }
}



/*

if(outerList.getSize() == outerblocks){
            for(linkedList.node<ClinkedList<T>> temp = outerList.getHead(); temp != null; temp = temp.next){

                if(temp == outerList.getTail()){
                    temp.data.addLast(value);
                }
                else if(temp.data.getSize() < innerblocks){
                    linkedList.node<ClinkedList<T>> succOuterListNode = temp.next;
                    temp.data.addLast(succOuterListNode.data.deleteFirst().data);
                }
                else if(temp.data.getSize() > innerblocks){
                    linkedList.node<ClinkedList<T>> succOuterListNode = temp.next;
                    succOuterListNode.data.addFirst(temp.data.deleteLast().data);
                }
            }
        }
        else{
            ClinkedList<T> bufferedCircularLinkedList = new ClinkedList<>();
            // adding last node with empty value in outerLinked list
            outerList.addLast(bufferedCircularLinkedList);
            for(linkedList.node<ClinkedList<T>> temp = outerList.getHead(); temp != null; temp = temp.next){
                if(temp == outerList.getTail()){

                }
            }
        }
 */
