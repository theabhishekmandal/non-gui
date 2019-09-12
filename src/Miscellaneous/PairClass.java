package Miscellaneous;

import java.util.HashSet;
import java.util.Set;

class Pair<T extends Comparable<? super T>, V extends Comparable<? super V>>
implements Comparable<Pair<T, V>>{
    public T T;
    public V V;

    public V getV() {
        return V;
    }

    public void setV(V V) {
        this.V = V;
    }

    public T getT() {
        return T;
    }

    public void setT(T T) {
        this.T = T;
    }

    Pair(T T, V V){
        this.T = T;
        this.V = V;
    }
    @Override
    public String toString(){
        return this.T + " " + this.V;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Pair<?, ?> Pair = (Pair<?, ?>) o;
        return this.T.equals(Pair.T) && this.V.equals(Pair.V);
    }
    @Override
    public int compareTo(Pair<T, V> o){
        int result = T.compareTo(o.T);
        return result == 0 ? V.compareTo(o.V) : result;
    }
    @Override
    public int hashCode(){
        return 31 * T.hashCode() * V.hashCode();
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
