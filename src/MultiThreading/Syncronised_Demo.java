package MultiThreading;

/**
 *
 * This program is an example of synchronisation
 * with the help of the keyword synchronised as it allows to executes one thread at a time and
 * doesn't allows another thread to interrupt while one thread is executing
 *
 * here we try to avoid the concurrent access of the threads on the method when one thread is executing.
 */

public class Syncronised_Demo implements Runnable {



   synchronized void call()
    {

        System.out.print("["+Thread.currentThread().getName());
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted");
        }
        System.out.print("]");
    }


    public void run()
    {
        this.call();
    }
public static void main(String args[]) throws InterruptedException
{
    Syncronised_Demo ob=new Syncronised_Demo();
    Thread one =new Thread(ob,"A");
    Thread two =new Thread(ob,"B");
    Thread three =new Thread(ob,"C");

    one.start();
    two.start();
    three.start();

    one.join();
    two.join();
    three.join();
}
}
