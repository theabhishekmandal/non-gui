package DataStructures.linked_list;
import java.util.*;
import java.io.Serializable;

class UnrolledLinkedListFast<E> extends AbstractList<E> implements List<E>, Serializable{
    //The maximum number of elements that can be stored in single node
    private int nodeCapacity;

    //the current size of this list.
    private int size = 0;

    //The first node of this list.
    private ListNode firstNode;

    //The  last node of this list.
    private ListNode lastNode;

    //Constructs an empty list with specified capacity
    public UnrolledLinkedListFast(int nodeCapacity) throws IllegalArgumentException{
        if(nodeCapacity < 8)
            throw new IllegalArgumentException("nodeCapacity < 8");
        this.nodeCapacity = nodeCapacity;
        firstNode = new ListNode();
        lastNode = firstNode;
    }

    public UnrolledLinkedListFast(){
        this(16);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    // Returns true if this list contains the specified element.
    public boolean contains(Object o){
        return (indexOf(o) != -1);
    }

    public Iterator<E> iterator(){
        return new ULLIterator(firstNode, -1, 0);
    }

    // Appends the specified element to the end of this list.
    public boolean add(E e){
        insertIntoNode(lastNode, lastNode.numElements, e);
        return true;
    }

    // Removes the first occurence of the specified element from the list,
    public boolean remove(Object o){
        int index = 0;
        ListNode node = firstNode;
        if(o == null){
            while(node != null){
                for(int ptr = 0; ptr < node.numElements; ptr++){
                    if(node.elements[ptr] == null){
                        removeFromNode(node, ptr);
                        return true;
                    }
                }
                index += node.numElements;
                node = node.next;
            }
        }
        else{
            while(node != null){
                for(int ptr = 0; ptr < node.numElements; ptr++){
                    if(o.equals(node.elements[ptr])){
                        removeFromNode(node, ptr);
                        return true;
                    }
                }
                index += node.numElements;
                node = node.next;
            }
        }
        return false;
    }

    // Removes all the elements
    public void clear(){
        ListNode node = firstNode.next;
        while(node != null){
            ListNode next = node.next;
            node.next = null;
            node.previous = null;
            node.elements = null;
            node = next;
        }
        lastNode = firstNode;
        for(int ptr = 0; ptr < firstNode.numElements; ptr++){
            firstNode.elements[ptr] = null;
        }
        firstNode.numElements = 0;
        firstNode.next = null;
        size = 0;
    }

    private void checkInRange(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    private Object[] getValues(int index){
        ListNode node;
        int p = 0;
        // it means the node lies on the left hand side
        if(size - index > index){
            node = firstNode;
            while(p <= index - node.numElements){
                p += node.numElements;
                node = node.next;
            }
        }
        // it means the node lies on the right hand side
        else{
            node = lastNode;
            p = size;
            while((p -= node.numElements) > index){
                node = node.previous;
            }
        }
        return new Object[]{node, index, p};
    }

    // Returns the element at the specified position in this list.
    public E get(int ind) throws IndexOutOfBoundsException{
        checkInRange(ind);
        Object[] nodeValues = getValues(ind);
        ListNode node = (ListNode) nodeValues[0];
        int index = (int) nodeValues[1];
        int p = (int) nodeValues[2];
        return (E) node.elements[index - p];
    }

    // Replaces the element at the specified position in this list with the specified element.
    public E set(int ind, E element) throws IndexOutOfBoundsException{
        checkInRange(ind);
        Object[] nodeValues = getValues(ind);
        ListNode node = (ListNode) nodeValues[0];
        int index = (int) nodeValues[1];
        int p = (int) nodeValues[2];

        // replacing and returning
        E el = (E) node.elements[index - p];
        node.elements[index - p] = element;
        return el;
    }

    // Inserts the specified element at the specified position in this list.
    // Shifts the element currently at that position (if any) and any
    // subsequent elements to the right (adds one to their indices)
    public void add(int ind, E element)throws IndexOutOfBoundsException{
        checkInRange(ind);
        Object[] nodeValues = getValues(ind);
        ListNode node = (ListNode) nodeValues[0];
        int index = (int) nodeValues[1];
        int p = (int) nodeValues[2];
        insertIntoNode(node, index - p, element);
    }

    // Removes the element at the specified posititon in this list.
    // Shifts any subsequent elements to the left (subtracts one from their indices).
    public E remove(int ind) throws IndexOutOfBoundsException{
        checkInRange(ind);
        Object[] nodeValues = getValues(ind);
        ListNode node = (ListNode) nodeValues[0];
        int index = (int) nodeValues[1];
        int p = (int) nodeValues[2];
        E el = (E) node.elements[index - p];
        removeFromNode(node, index - p);
        return el;
    }

    private static final long serialVersionUID = -674052309103045211L;
    private class ListNode{
        ListNode next;
        ListNode previous;
        int numElements = 0;
        Object[] elements;
        ListNode(){
            elements = new Object[nodeCapacity];
        }
        @Override
        public String toString(){
            StringBuilder br = new StringBuilder("[");
            for(int i = 0; i < elements.length; i++){
                br.append((String)elements[i]);
                if(i != elements.length - 1) br.append(" ,");
            }
            br.append("]");
            return br.toString();
        }
    }

    private class ULLIterator implements ListIterator<E>{
        ListNode currentNode;
        // pointer to the inner Object array
        int ptr;
        // pointer to the list
        int index;
        private int expectedModCount = modCount;
        ULLIterator(ListNode node, int ptr, int index){
            this.currentNode = node;
            this.ptr = ptr;
            this.index = index;
        }
        public boolean hasNext(){
            return (index < size - 1);
        }
        public E next() throws NoSuchElementException{
            ptr++;
            if(ptr >= currentNode.numElements){
                if(currentNode.next != null){
                    currentNode = currentNode.next;
                    ptr = 0;
                }
                else{
                    throw new NoSuchElementException();
                }
            }
            index++;
            checkForModification();
            return (E) currentNode.elements[ptr];
        }
        public boolean hasPrevious(){
            return (index > 0);
        }
        public E previous() throws NoSuchElementException{
            ptr--;
            if(ptr < 0){
                if(currentNode.previous != null){
                    currentNode = currentNode.previous;
                    ptr = currentNode.numElements - 1;
                }
                else{
                    throw new NoSuchElementException();
                }
            }
            index--;
            checkForModification();
            return (E) currentNode.elements[ptr];
        }
        public int nextIndex(){
            return (index + 1);
        }
        public int previousIndex(){
            return (index - 1);
        }
        public void remove(){
            checkForModification();
            removeFromNode(currentNode, ptr);
        }
        public void set(E e){
            checkForModification();
            currentNode.elements[ptr] = e;
        }
        public void add(E e){
            checkForModification();
            insertIntoNode(currentNode, ptr + 1, e);
        }
        private void checkForModification(){
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }
    private void insertIntoNode(ListNode node, int ptr, E element){
        // if the node is null
        if(node.numElements == nodeCapacity){
            // create a new node
            ListNode newNode = new ListNode();
            // move half of the elements to the new node
            int elementsToMove = nodeCapacity >> 1;
            int startIndex = nodeCapacity - elementsToMove;
            for(int i = 0; i < elementsToMove; i++){
                newNode.elements[i] = node.elements[startIndex + i];
                node.elements[startIndex + i] = null;
            }
            node.numElements -= elementsToMove;
            newNode.numElements = elementsToMove;
            // insert the new node into the list
            newNode.next = node.next;
            newNode.previous = node;
            if(node.next != null){
                node.next.previous = newNode;
            }
            node.next = newNode;
            if(node == lastNode){
                lastNode = newNode;
            }
            // check whether the element should be inserted into
            // the original node or into the new node
            if(ptr > node.numElements){
                node = newNode;
                ptr -= node.numElements;
            }
        }
        // if you want to insert at given index
        // i.e in between the elements then this will be used
        // to shift the elements to make space
        for(int i = node.numElements; i > ptr; i--){
            node.elements[i] = node.elements[i - 1];
        }
        node.elements[ptr] = element;
        node.numElements++;
        size++;
        modCount++;
    }
    private void removeFromNode(ListNode node, int ptr){
        node.numElements--;
        for(int i = 0; i < node.numElements; i++){
            node.elements[i] = node.elements[i + 1];
        }
        node.elements[node.numElements] = null;
        if(node.next != null && node.next.numElements + node.numElements <= nodeCapacity){
            mergeWithNextNode(node);
        }
        else if(node.previous != null && node.previous.numElements + node.numElements <= nodeCapacity){
            mergeWithNextNode(node.previous);
        }
        size--;
        modCount++;
    }
    //This method does merge the specified node with the next node.
    private void mergeWithNextNode(ListNode node){
        ListNode next = node.next;
        for(int i = 0; i < next.numElements; i++){
            node.elements[node.numElements + i] = next.elements[i];
            next.elements[i] = null;
        }
        node.numElements += next.numElements;
        if(next.next != null){
            next.next.previous = node;
        }
        node.next = next.next.next;
        if(next == lastNode){
            lastNode = node;
        }
    }
}


public class UnrolledLinkedList2Demo {
    public static void main(String[] args) {
        UnrolledLinkedListFast<String> list = new UnrolledLinkedListFast<>(8);
        int size = 11;
        String[] arr = new String[size];
        for(int i = 65; i < 65 + size; i++){
            arr[i - 65] = "" + (char)i;
        }
        for(String str : arr){
            list.add(str);
        }
        //list.add(0, "M");
        String ans = list.toString();
        System.out.println(list.toString());
        list.get(5);
    }
}
