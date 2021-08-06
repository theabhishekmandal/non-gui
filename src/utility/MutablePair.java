package utility;

public class MutablePair<T, V>{
    private T first;
    private V second;

    public MutablePair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <T, V> MutablePair<T, V> of(T first, V second) {
        return new MutablePair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public MutablePair<T, V> setFirst(T first) {
        this.first = first;
        return this;
    }

    public V getSecond() {
        return second;
    }

    public MutablePair<T, V> setSecond(V second) {
        this.second = second;
        return this;
    }
}
