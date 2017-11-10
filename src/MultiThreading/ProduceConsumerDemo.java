package MultiThreading;
/**
 * This is an example of Producer and Consumer problem
 * In this example we can see that it has some error if we look at the output
 * In a Producer and Consumer problem the  Producer produces the data and the Consumer consumes
 * the data. This is done with the help of a Queue.
 *
 * In the following example we can see that Nothing stops the Producer from overrunning the Consumer
 * and nothing stops Consumer from overrunning the Producer. That's why we can see that for one value of
 * the data produced by the Producer , Consumer gets the same value many times and vice versa.
 *
 * In this both the Consumer and the Producer doesn't have communication between each other
 * For the communication we will use various methods  in the next example.

 */
class Q
{
    int n;
    synchronized  void put(int n)
    {
        this.n=n;
        System.out.println("Put: "+this.n);
    }
    synchronized  void get()
    {
        System.out.println("Got: "+this.n);
        //return this.n;
        //return 0;
    }
}

class Producer implements Runnable{
    Q q;
    Producer(Q ob)
    {
        this.q=ob;
        new Thread(this,"Producer").start();

    }
    public void run()
    {
        int i=0;
        while(true)
        {
            q.put(i++);
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
                System.out.println("Interrupted");
            }
        }
    }
}

class Consumer implements Runnable{
    Q ob;
    Consumer(Q q)
    {
        this.ob=q;
        new Thread(this,"Consumer").start();
    }
    public void run()
    {
        while(true)
        {
            ob.get();
            try
            {
                Thread.sleep(2000);

            }
            catch(InterruptedException e)
            {
                System.out.println("Interrupted");
            }
        }
    }
}
public class ProduceConsumerDemo {
    public static void main(String args[])
    {
        Q ob=new Q();
        Producer ob1=new Producer(ob);
        Consumer ob2=new Consumer(ob);
        System.out.println("Press Ctrl-c to stop");

    }
}
