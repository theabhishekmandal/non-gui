package multi_threading.lock_free_techniques.data_structure;


public class StandardStack<T> {
    static class StackNode<T> {
        T data;
        StackNode<T> next;
        public StackNode(T data, StackNode<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private StackNode<T> head;
    private int counter;

    public synchronized void push(T data) {
        head = new StackNode<>(data, head);
        counter++;
    }

    public synchronized T pop() {
        if (head == null) {
            counter++;
            return null;
        }
        StackNode<T> temp = head;
        head = head.next;
        counter++;
        return temp.data;
    }

    public int getCounter() {
        return counter;
    }
}
