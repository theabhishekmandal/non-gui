package miscellaneous;

import java.util.HashSet;
import java.util.Set;

class Pair<T extends Comparable<? super T>, V extends Comparable<? super V>>
implements Comparable<Pair<T, V>>{
    private T t;
    private V v;

    public V getV() {
        return v;
    }

    public void setV(V V) {
        this.v = V;
    }

    public T getT() {
        return t;
    }

    public void setT(T T) {
        this.t = T;
    }

    Pair(T t, V v){
        this.t = t;
        this.v = v;
    }
    @Override
    public String toString(){
        return this.t + " " + this.v;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return this.t.equals(pair.t) && this.v.equals(pair.v);
    }
    @Override
    public int compareTo(Pair<T, V> o){
        int result = t.compareTo(o.t);
        return result == 0 ? v.compareTo(o.v) : result;
    }
    @Override
    public int hashCode(){
        return 31 * t.hashCode() * v.hashCode();
    }
}
public class PairClass {
    public static void main(String[] args) {
        Set<Pair<String, Integer>> set = new HashSet<>();
        set.add(new Pair<>("hello", 1));
        set.add(new Pair<>("hello", 1));
        set.add(new Pair<>("hello", 2));
        System.out.println(set);
    }
}
