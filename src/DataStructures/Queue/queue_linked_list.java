package DataStructures.Queue;


import java.util.Scanner;
class node1
{
    node1 next;
    int data;
    node1(int data)
    {
        this.data=data;
    }
}




public class queue_linked_list {


     private node1 start;
     private node1 end;


     public String toString()
     {
         return "nope you can't delete null";
     }
     void insert(int data)
     {
         if(start==null&&end==null)
         {
             start=new node1(data);
             end=start;
         }
         else
         {
             node1 temp=new node1(data);
             end.next=temp;
             end=temp;

         }
     }



     void display()
     {
         node1 traverse=start;
         while(traverse!=null)
         {
             System.out.print(traverse.data+"-->");
             traverse=traverse.next;
         }

     }



     void delete()
     {

             if (start == null)
                 System.err.println("deleting wrong value");
             else
             {
                 start=start.next;
             }
     }



     public static void main(String args[])
     {
         queue_linked_list ob=new queue_linked_list();
         Scanner s=new Scanner(System.in);

            System.out.println("enter how many elements");
            int t=s.nextInt();
            while(t-->0)
            {
                ob.insert(s.nextInt());
            }
            System.out.println("enter how much to delete");
            t=s.nextInt();
            while(t-->0)
                ob.delete();
            System.out.println("elements of the Queue are");
            ob.display();
     }
}
