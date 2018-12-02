package RegexImplementation;

import java.io.Console;

public class comment
{
    public static void main(String args[]) {
        Console console = System.console();
        if(console==null)
            System.err.println("NO console");
        console.printf("%s","hello world");
        System.exit(1);
    }
}



