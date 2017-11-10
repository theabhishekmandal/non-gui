package MultiThreading;
/*
In this program we are creating multi threads by implementing runnable interface
Also in this we create two threads by using two objects
means every object has at most one thread
 */
public class CreatingMultipleThreads implements Runnable {
    Thread t;
    String name;
    CreatingMultipleThreads(String s)
    {
        t=new Thread(this,s);
        t.start();
    }
    public void run()
    {
        for(int i=0;i<5;i++)

            System.out.println("this is the value of the current thread "+Thread.currentThread().getName()+" "+i);

    }
    public static void main(String args[])
    {
        CreatingMultipleThreads ob1=new CreatingMultipleThreads("A");
        CreatingMultipleThreads ob2=new CreatingMultipleThreads("B");



    }
}
