package regex_implementation;

import java.io.Console;
import java.util.Objects;

public class Comment {
    public static void main(String[] args) {
        Console console = System.console();
        Objects.requireNonNull(console, "NoConsole");
        console.printf("%s","hello world");
        System.exit(1);
    }
}



