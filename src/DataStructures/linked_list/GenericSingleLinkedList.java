package DataStructures.linked_list;
import java.util.NoSuchElementException;

class linkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;
    static class node<T>{
        T data;
        node<T> next;
        node(T data, node<T> next){
            this.data = data;
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }
    public linkedList(){}

    public linkedList(linkedList<T> list){
        copyAll(list);
    }

    public int getSize(){
        return this.size;
    }

    public node<T> getHead(){
        return this.head;
    }

    public node<T> getTail(){
        return this.tail;
    }

    public void addLast(T data){
        final node<T> last = tail;
        final node<T> newnode = new node<>(data, null);
        tail = newnode;
        if(last == null)
            head = newnode;
        else
            last.next = newnode;
        size++;
    }

    public void addFirst(T data){
        final node<T> first = head;
        final node<T> newnode = new node<>(data, first);
        head = newnode;
        if(first == null)
            tail = newnode;
        size++;
    }

    public void insertInTheMiddle(int pos, T data){
        if(pos <= 0){
            addFirst(data);
            return;
        }
        if(pos >= size){
            addLast(data);
            return;
        }
        // getting the previous node of the current node
        // in the doubly linked list class this getNode will fetch
        // the current node rather than previous node
        node<T> pred = getNode(pos);
        final node<T> nextnode = pred.next;
        final node<T> newnode = new node<>(data, nextnode);
        pred.next = newnode;
        size++;
    }
    private node<T> getNode(int pos){
        node<T> temp = head;
        if(head == tail) return null;
        for(int i = 0; i < pos - 1; i++){
            temp = temp.next;
        }
        return  temp;
    }

    public node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final node<T> newnode = head;
        head = head.next;

        // deleting the value and the next pointer to null
        newnode.next = null;
        newnode.data = null;

        if(head == null) tail = null;
        size--;
        return newnode;
    }

    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        tail = getNode(size - 1);

        // deleting the value and the next pointer to null
        newnode.data = null;

        if(tail == null) head = null;
        else{
            tail.next = null;
        }
        size--;
        return newnode;
    }

    public node<T> deleteInTheMiddle(int pos){
        if(pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if(pos == 0) return deleteFirst();
        if(pos == size - 1) return deleteLast();
        node<T> temp = getNode(pos);
        node<T> currentNode = temp.next;
        temp.next = currentNode.next;
        currentNode.next = null;
        size--;
        return currentNode;
    }

    public void copyAll(linkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != null; temp = temp.next)
            br.append(temp + "-->");
        return br.toString();
    }
}
public class GenericSingleLinkedList {
    public static void main(String[] args) {
        linkedList<Integer> list = new linkedList<>();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        linkedList<Integer> list2 = new linkedList<>();
        list2.copyAll(list);

        linkedList<Integer> list3 = new linkedList<>(list2);

        System.out.println("list1 before deletion " +  list);
        // using this type of loop because the getsize() keeps on changing
        for(;list.getSize() > 0;){
            list.deleteLast();
            System.out.println(list);
        }
        System.out.println("list2 before deletion " + list2);
        for(;list2.getSize() > 0;){
            list2.deleteFirst();
            System.out.println(list2);
        }

        System.out.println("list3 before insertion " + list3);
        int[] arr = {5, 6, 7, 8, 9};
        for(int i = 0; i < arr.length; i++){
            list3.insertInTheMiddle(list3.getSize() / 2, arr[i]);
            System.out.println(list3);
        }

        System.out.println("list3 before deletion " + list3);
        for(;list3.getSize() > 0;){
            list3.deleteInTheMiddle(list3.getSize() / 2);
            System.out.println(list3);
        }
    }
}
