package MultiThreading;

/*
 For creating a thread you can do it by extending the class Thread
 or by implementing the runnable interface
 The advantage of implementing runnable interface is that you would be able to extend other important classes

For creating a thread by using the runnable interface
               first you need to create a the object of the class
               Then you override the run method with access specifier public
               after creating the object of the classs then you create the object of the Thread class
               and pass the object of the class as a parameter
               then you call the start method
               this start method will automatically will call the run method thereby creating a child thread
               If you call the run method directly then it acts just like the normal method and won't create any child thread
 */
public class Implementing_Runnable_demo implements  Runnable {
    public void run()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println("value of the current thread "+Thread.currentThread().getName()+" "+i);
        }
    }
    public static void main(String args[])
    {
        Implementing_Runnable_demo r=new Implementing_Runnable_demo();
        Thread ob1=new Thread(r,"A");
        Thread ob2=new Thread(r,"B");
        ob1.start();
        ob2.start();

    }
}
