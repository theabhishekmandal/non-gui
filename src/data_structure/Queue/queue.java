package data_structure.Queue;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class queueit
{
    PrintWriter out=new PrintWriter(System.out);
    PrintWriter err=new PrintWriter(System.err);
    private int arr[];
    private int size=10;
    private int head=-1;
    private int tail=-1;


    queueit()
    {
        this.arr= new int[this.size];
    }


    private int[] increaseCapacity(int arr[])
    {

        return Arrays.copyOf(arr,arr.length*2);
    }


    public void push(int data)
    {
        if(head==-1 && tail==-1)
        {
            arr[++tail]=data;
            ++head;
            return;
        }

        if(tail==arr.length-1)
            arr=increaseCapacity(arr);
        arr[++tail]=data;
    }


    public void pop()
    {
        if(head>tail||head==-1)
        {
            err.println("nothing to delete");
            return;
        }
        out.println(arr[head++]);
    }



    public void show()
    {
        StringBuilder br=new StringBuilder();
        if(head==-1||head>tail)
        {
            err.println("there is nothing to show");
            return;
        }

        for(int i=head;i<=tail;i++)
        {
            br.append(arr[i]).append(" ");
        }
        out.println(br);
    }
}


public class queue {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        queueit q=new queueit();
        System.out.println("enter how many elements in the Queue");
        int t=s.nextInt();
        while(t-->0)
        {
            q.push(s.nextInt());
        }
        System.out.println("enter how many you want to delete");
        t=s.nextInt();
        while(t-->0)
            q.pop();
        q.out.flush();
        q.err.flush();
        System.out.println("elements of the Queue are");
        q.show();
        q.out.flush();
        q.err.flush();


    }
}
