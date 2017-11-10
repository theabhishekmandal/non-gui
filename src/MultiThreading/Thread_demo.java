package MultiThreading;
import java.util.*;
/**
 This is an example on how to set and get name of your main thread
 */

public class Thread_demo {
    public static void main(String args[])
    {
        Thread t=Thread.currentThread();
        System.out.println("this is the default name of the main thread-->"+t.getName());
        System.out.println("enter a name for your main thread");
        t.setName(new Scanner(System.in).nextLine());
        System.out.println("This is the name of your main thread-->"+t.getName());
        System.out.println("This is the state  of your main thread-->"+t.getState());

    }
}
