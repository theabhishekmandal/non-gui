package Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Integer> opt = Optional.of(1);
        System.out.println(opt.isPresent() ? opt.get() : "no value");

        opt = Optional.empty();
        System.out.println(opt.isPresent() ? opt.get() : "no value");


        // Optional example with nullValue, noValue and with values
        List<Integer> intlist = null;

        intlist = Optional.ofNullable(getNullList())
                            .filter(x -> !x.isEmpty())
                            .orElse(null);
        System.out.println("passing null list using optional");
        System.out.println(intlist);

        intlist = Optional.ofNullable(getEmptyList())
                .filter(x -> !x.isEmpty())
                .orElse(null);
        System.out.println("passing empty list using optional");
        System.out.println(intlist);

        intlist = Optional.ofNullable(getValueList())
                .filter(x -> !x.isEmpty())
                .orElse(null);
        System.out.println("passing nonEmpty list using optional");
        System.out.println(intlist);

    }
    private static List<Integer> getNullList(){
        return null;
    }
    private static List<Integer> getEmptyList(){
        return Collections.emptyList();
    }
    private static List<Integer> getValueList(){
        return Arrays.asList(1, 2, 3, 4, 5);
    }
}
