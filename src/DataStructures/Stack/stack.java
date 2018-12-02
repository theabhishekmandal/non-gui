package DataStructures.Stack;

import java.io.PrintWriter;
import java.util.*;
class Stackit
{
    PrintWriter out=new PrintWriter(System.out);
    PrintWriter err=new PrintWriter(System.err);
    private int arr[];
    private int size=10;
    private int top=-1;
    Stackit()
    {
        this.arr=new int[this.size];
    }

    private void increaseCapacity(int a[],int size)
    {
        size=size*2;
        arr=Arrays.copyOf(a,size);

    }
    public void push(int data)
    {
        if(top==arr.length-1)
        {
            increaseCapacity(arr,size);
        }
        arr[++top]=data;

    }
    public void pop()
    {
        if(top==-1)
        {
            err.println("there is nothing to Delete");
             return;
        }
        out.println(arr[top--]);


    }
    public void show()
    {
        if(top==-1)
        {
            err.println("nothing to show");
            return;
        }
        for(int i=0;i<=top;i++)
            out.print(arr[i]+" ");
    }

}
public class stack {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        Stackit st=new Stackit();
        System.out.println("how many elements do you want to push");
        int t=s.nextInt();
        while(t-->0)
        {
            st.push(s.nextInt());
        }
        System.out.println("how many elements do you want to pop");
        t=s.nextInt();
        while(t-->0)
            st.pop();
        st.out.flush();
        st.err.flush();
        st.out.println("elements of the Queue are");
        st.show();
        st.out.flush();
        st.err.flush();


    }
}
