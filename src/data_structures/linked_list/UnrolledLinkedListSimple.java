package data_structures.linked_list;

class Ull<T>{
    private int nodeCapacity;
    private int size = 0;
    private ListNode firstNode;
    private ListNode lastNode;

    // using private class becuase static class
    // cannot use variable nodeCapacity
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
                br.append(elements[i]);
               if(i != elements.length - 1) br.append(" ,"); 
            }
            br.append("]");
            return br.toString();
        }
    }
        
    //Constructs an empty list with specified capacity
    public Ull(int nodeCapacity) throws IllegalArgumentException{
        if(nodeCapacity < 8)
            throw new IllegalArgumentException("nodeCapacity < 8");
        this.nodeCapacity = nodeCapacity;
        firstNode = new ListNode();
        lastNode = firstNode;
    }

    public Ull(){
        this(16);
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    public boolean add(T e){
        insertIntoNode(lastNode, lastNode.numElements, e);
        return true;
    }

    private void checkRange(int index)throws IndexOutOfBoundsException{
        if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
    }

    private Object[] getValues(int index){
        ListNode node = null;
        int p = 0;
        /*if this condition is true then value lies at the starting of the list
        note that it will check for both i.e if a value lies at the last of the first node
        and if the value lies at the last of the last node
        */
        if(this.size - index > index){
            /*
                checking if total number of elements exceeds the given index, if that is true
                then the we got our node
             */
            for(node = firstNode, p = 0; p + node.numElements <= index; p += node.numElements, node = node.next){

            }
        }
        else{
            /*
            subtracting the total elements at the beginning because, by subtracting we want to go to the first
            index of the current node
             */
            for(node = lastNode, p = this.size - node.numElements; p > index; p -= node.numElements, node = node.previous){

            }
        }
        return new Object[]{node, p};
    }

    public T get(int index) throws IndexOutOfBoundsException{
        checkRange(index);
        Object[] arr = getValues(index);
        ListNode node = (ListNode) arr[0];
        int p = (int) arr[1];
        return (T) node.elements[index - p];
    }

    public void set(int index, T value){
        checkRange(index);
        Object[] arr = getValues(index);
        ListNode node = (ListNode) arr[0];
        int p = (int) arr[1];
        node.elements[index - p] = value;
    }

    public void add(int index, T value){
        checkRange(index);
        Object[] arr = getValues(index);
        ListNode node = (ListNode) arr[0];
        int p = (int) arr[1];
        insertIntoNode(node, index - p, value);
    }

    // removing a given object if exists
    public boolean remove(Object o){
        ListNode node = firstNode;
        if(o == null){
            while(node != null){
                for(int i = 0; i < node.numElements; i++){
                    if(node.elements[i] == null){
                        removeFromNode(node, i);
                        return true;
                    }           
                }
                node = node.next;
            }
        }
        else{
            while(node != null){
                for(int i = 0; i < node.numElements; i++){
                    if(o.equals(node.elements[i])){
                        removeFromNode(node, i);
                        return true;
                    }           
                }
                node = node.next;
            }
        }
        return false;
    }

    /*
     * replacing a given value and then adjusting the number
     * of elements such that it remains under nodecapacity
     */
    public void removeFromNode(ListNode node, int index){
        node.numElements--;
        for(int i = index; i < node.numElements;  i++){
            node.elements[i] = node.elements[i + 1];
        }
        node.elements[node.numElements] = null;
        if(node.next != null && node.numElements + node.next.numElements <= nodeCapacity){
            mergeWithNextNode(node);
        }
        else if(node.previous != null && node.numElements + node.previous.numElements <= nodeCapacity){
            mergeWithNextNode(node.previous);
        }
        size--;

    }

    /*
     * merging the node if a given node.elements and next.elements
     * does not satisfy the total nodeCapacity
     */
    private void mergeWithNextNode(ListNode node){
        ListNode next = node.next;
        for(int i = 0; i < next.numElements; i++){
            node.elements[node.numElements + i] = next.elements[i];
            next.elements[i] = null;
        }
        node.numElements += next.numElements;
        node.next = next.next;
        if(next.next != null){
            next.next.previous = node;
        }
        if(next == lastNode){
            lastNode = node;
        }
    }

    // method used for both inserting between nodes and at the end
    private void insertIntoNode(ListNode node, int ptr, T element){
        // if the node is full
        if(node.numElements == this.nodeCapacity){
            // create a new node
            ListNode newNode = new ListNode();
            // move half of the elements to the new node
            int elementsToMove = this.nodeCapacity >> 1;
            int startIndex = this.nodeCapacity - elementsToMove;
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
        size++;
        node.numElements++;
    }
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("[");
        ListNode temp = firstNode;
        int index = 0;
        while(temp != null){
            for(int i = 0; i < temp.numElements; i++){
                string.append(temp.elements[i]);
                if(index != this.size - 1) string.append(", ");
                index++;
            }
            temp = temp.next;
        }
        string.append("]");
        return string.toString();
    }

}
public class UnrolledLinkedListSimple{
    public static void main(String[] args){
        Ull<String> list = new Ull<>(8);
        String[] stringarr = "Hello I am Abhishek good morning friends my name is abhishek".split(" ");
        for(String str : stringarr)
            list.add(str);
        System.out.println(list);
        list.remove("morning");
        list.remove("abhishek");
        list.remove("name");
        list.remove("friends");
        System.out.println(list);

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
