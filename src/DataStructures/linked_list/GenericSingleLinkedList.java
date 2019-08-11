package DataStructures.linked_list;
import java.util.NoSuchElementException;

class linkedList<T>{
    node<T> head;
    node<T> tail;
    int size;
    static class node<T>{
        T data;
        node<T> next;
        node(T data, node<T> next){
            this.data = data;
            this.next = next;
        }
    }
    public linkedList(){

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
        node<T> temp = getNode(pos);
        final node<T> nextnode = temp.next;
        final node<T> newnode = new node<>(data, nextnode);
        temp.next = newnode;
        size++;
    }
    private node<T> getNode(int pos){
        node<T> temp = head;
        for(int i = 0; i < pos - 1; i++){
            temp = temp.next;
        }
        return  temp;
    }

    public node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final node<T> newnode = head;
        head = head.next;
        newnode.next = null;
        if(head == null) tail = null;
        size--;
        return newnode;
    }

    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        node<T> prevnode = null;
        if(head != tail) {
            prevnode = getNode(size - 1);
        }
        tail = prevnode;
        if(tail == null) head = null;
        else
            tail.next = null;
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

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != null; temp = temp.next)
            br.append(temp.data + "-->");
        return br.toString();
    }
}
public class GenericSingleLinkedList {
    public static void main(String[] args) {
        linkedList<Integer> list = new linkedList<>();
        list.addLast(2);
        list.addLast(3);
        System.out.println(list.size);
        list.insertInTheMiddle(2, 99);
        System.out.println(list);
        list.deleteFirst();
        System.out.println(list);

        linkedList<String> list2 = new linkedList<>();
        list2.addLast("I");
        list2.addLast("am");
        list2.addLast("Abhishek");
        list2.deleteInTheMiddle(2);
        System.out.println(list2);
    }
}
