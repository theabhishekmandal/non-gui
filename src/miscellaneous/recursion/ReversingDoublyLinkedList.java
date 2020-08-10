package miscellaneous.recursion;
class linkedList<T>{
    node<T> first;
    node<T> last;
    static class node<T>{
        T value;
        node<T> next;
        node<T> previous;
        public node(node<T> prev, T value, node<T> last){
            this.value = value;
            this.next = last;
            this.previous = prev;
        }
    }
    public linkedList(){

    }
    public void addLast(T ob){
        final node<T> l = last;
        final node<T> newnode = new node<>(l, ob, null);
        last = newnode;
        if(l == null)
            first = newnode;
        else
            l.next = newnode;
    }
    @Override
    public String toString(){
        if(first == null) return "[]";
        if(first == last) return "[" + this.first.value + "]";
        StringBuilder br = new StringBuilder("[");
        node<T> f = first;
        while(f != null){
            br.append(f.value).append(", ");
            f = f.next;
        }
        br.append("]");
        return br.toString();
    }
}
public class ReversingDoublyLinkedList {
    public static <T> linkedList<T> reverseTheList(linkedList<T> list2, linkedList.node<T> last){
        if(last == null)
            return list2;
        list2.addLast(last.value);
        last = last.previous;
        return reverseTheList(list2, last);
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        linkedList<Integer> list = new linkedList<>();
        for(int i : arr){
            list.addLast(i);
        }
        System.out.println(list);
        linkedList<?> list2 = reverseTheList(new linkedList<>(), list.last);
        System.out.println(list2);
    }
}
