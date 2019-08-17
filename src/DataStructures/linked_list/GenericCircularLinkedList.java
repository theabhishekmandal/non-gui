package DataStructures.linked_list;

import java.util.NoSuchElementException;

class ClinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;

    public ClinkedList(){}

    public ClinkedList(ClinkedList<T> list){
        copyAll(list);
    }

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
        final node<T> first = head;
        node<T> newnode = new node<>(data, first);
        tail = newnode;
        if(last == null)
            head = newnode;
        else
            last.next = newnode;
        size++;
    }

    public void addFirst(T data){
        final node<T> first = head;
        node<T> newnode = new node<>(data, first);
        head = newnode;
        if(first == null) tail = newnode;
        else{
            tail.next = newnode;
        }
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
        final node<T> pred = getNode(pos);
        final node<T> succ = pred.next;
        node<T> newnode = new node<T>(data, succ);
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

    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        tail = getNode(size - 1);

        // deleting the next pointer to null
        newnode.next = null;

        if(tail == null) head = null;
        else{
            tail.next = head;
        }
        size--;
        return newnode;
    }

    public node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final node<T> newnode = head;
        head = (head.next == head) ? null : head.next;

        // deleting the next pointer to null
        newnode.next = null;

        if(head == null) tail = null;
        else{
            tail.next = head;
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

    public void copyAll(ClinkedList<T> list){
        for(node<T> temp = list.head; temp != list.tail; temp = temp.next)
            this.addLast(temp.data);
        this.addLast(list.tail.data);
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != tail; temp = temp.next){
            br.append(temp + "-->");
        }

        // printing tail here because it won't work in loop
        return br.append(tail + "-->").toString();
    }
}
public class GenericCircularLinkedList {
    public static void main(String[] args) {
        ClinkedList<String> list = new ClinkedList<>();
        list.addLast("hello");
        list.addFirst("world");
        list.addLast("Abhishek");
        list.addFirst("Mandal");
        list.insertInTheMiddle(list.getSize() >> 1, "middle");
        print(list, null);

        ClinkedList<String> list2 = new ClinkedList<>(list);
        ClinkedList<String> list3 = new ClinkedList<>(list);
        ClinkedList<String> list4 = new ClinkedList<>(list);

        System.out.println("list2 before deletion from end " + list2);
        for(;list2.getSize() > 0;){
            print(list2, list2.deleteLast());
        }

        System.out.println("list3 before deletion from begining " + list3);
        for(;list3.getSize() > 0;){
            print(list3, list3.deleteFirst());
        }

        System.out.println("list4 before deletion from middle " + list4);
        for(;list4.getSize() > 0;){
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(ClinkedList<T> list, ClinkedList.node<T> deletednode){
        ClinkedList.node<T> head = list.getHead();
        ClinkedList.node<T> headNext = (head != null)? head.next : null;
        ClinkedList.node<T> tail = list.getTail();
        ClinkedList.node<T> tailNext = (tail != null) ? tail.next : null;

        System.out.println(((deletednode == null) ? "" :"deleted node is " + deletednode)
                + "\nlist is " + list
                + "\nhead is " + head
                + "\nhead next is " + headNext
                + "\ntail is " + tail
                + "\ntail next is " + tailNext
                +"\nnumber of elements are " + list.getSize()
                + "\n\n");
    }
}
