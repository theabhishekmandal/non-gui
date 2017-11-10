package data_structure.linked_list;

import java.util.*;

/**
 *
 * This program is an example of linked list implementation in java.
 *
 * Information about the node is stored in node.java file in the same package
 *
 * the append method adds a node at the end of the list
 *
 * the prepend method adds the node at the begining of the list
 *
 * the delete method deletes the passed value of the data from the list
 *
 * the insertinthemiddle inserts a data in a given position
 *
 */



public class Linkedlist
{
    node head;
    StringBuilder br;



    public void append(int data)
    {

        if(head==null)
           head=new node(data);
        else
        {
            node current=head;
            while(current.next!=null) current=current.next;
            current.next=new node(data);
        }
    }



    public void prepend(int data)
    {
        if(head==null)
            head=new node(data);
        else
        {
            node current=new node(data);
            current.next=head;
            head=current;
        }
    }



    public String delete(int data)
    {
        String k="didn't find the value";
        if(head==null)
            return "delete ain't possible null value";
        else
            if(head.data==data)
            {
                head=head.next;
                k="ya deletion successful";
                return k;
            }
        else
        {
            node current=head;
            while(current.next!=null)
            {
                if(current.next.data==data){
                    k="ya deletion successful";
                    current.next=current.next.next;
                   return k;
                }
                current=current.next;
            }

        }
        return k;
    }



    public void insertInMiddle(int data,int pos)
    {
        node temp=head;
        node temp2=null;
        int i=1;
        int len=0;

        // getting the length of the list
        while(temp!=null)
        {
            len++;
            temp=temp.next;
        }
        if(pos>len)
        {
            System.err.println("Not enough nodes insertion not possible");
            return;
        }
        if(pos==1)
        {
            temp2=new node(data);
            temp2.next=head;
            head=temp2;
            return;
        }
        temp=head;
        while(temp.next!=null&&i!=pos)
        {

            temp2=temp;
            temp=temp.next;
            i++;
        }
        temp2.next=new node(data);
        temp2.next.next=temp;
    }



    public void show()
    {
        node temp=head;
        br=new StringBuilder("");
        while(temp!=null)
        {
            br.append(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println(br);
    }



    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        Linkedlist l=new Linkedlist();
        System.out.println("enter how much elements to enter");
        int t=s.nextInt();
        while(t-->0)
        {
            l.append(s.nextInt());
        }
        System.out.println("enter how many to repend");
        t=s.nextInt();
        while(t-->0)
            l.prepend(s.nextInt());
        System.out.println("enter what data you want to delete");
           System.out.println(l.delete(s.nextInt()));
           l.show();
       System.out.println("enter what element you want to insert with a given position");
       l.insertInMiddle(s.nextInt(),s.nextInt());
       l.show();


    }

}