package design_patterns.pipeline;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var pipeLine = new PipeLine<>(String::valueOf)
                .addHandler(String::toCharArray);

        char[] execute = pipeLine.execute(10);

        System.out.println(Arrays.toString(execute));
    }
}
