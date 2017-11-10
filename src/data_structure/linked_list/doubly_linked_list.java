package data_structure.linked_list;

/**
  - This program is an example of doubly linked list various operations are performed to provide the result



  -  the  head will remain at it's position  and we must not change it



  -  the insert() method which will take integer as an argument as the data value for the node
        if head is null i.e
          head->null
        then create a new node and pass it to the head
             if value 10 is passed then head
             is containing 10 and the next of it will contain null
             10-->null

       if head is not null meaning head is already containing any value
       then we have to traverse the whole list until the end , so as to add a new node
       at the end

       to traverse the list we use a temporary node and pass the reference of the head in it
       then we traverse the whole list till we encounter null   then we add a new node
       we use next pointer of the temp to refer to the new node
       and the previous pointer of the end to refer to temp


  -  The delete method will delete values if you provide the position of the node that you want
     to delete.

     If your head is containing nothing then you cannot delete that because it is already containing nothing.

     Else if ,the value of the position is 1 (means you want to delete the first node)
     then you need to shift the head to the next node and then make the previous of the head as null

     or else traverse the list having a counter variable until the the given node is encountered
     then assign the previous node of the given node (that you want to delete)
     like for eg node hello=temp.previous

      then make pointer reference of the  node hello  to the next node of the temp
      hello.next=temp.next

      then we have to make the previous of the next node of the temp to hello
      but here are two conditions : 1 if the next of the temp is null then
      we cannot assign the previous of next of temp to hello
       : 2 if the next of the temp is not null
      then we can assign the previous of next of temp to hello


  -   The insertInTheMiddle method is  used to insert a node with a given position in the middle

     if the position is given greater than the length of the list then insertion not possible
     if the positon given is 1 then we make a new node  and link the next of the new node to head
     and previous of the head to new node and atlast make the new node as head
     else
     we insert the node in the middle by traversing the list


  -  the show method will traverse the list and display all the values in it
     here in the while loop we didn't use while(temp.next!=null)
     because if do this then we would not be able to get the last element


 */


import java.util.*;
public class doubly_linked_list {

    doublenode head;

    void insert(int data)
    {
        if(head==null)
        {
            head=new doublenode(data);
        }
        else
        {
            doublenode temp=head;
            while(temp.next!=null)
                temp=temp.next;
            doublenode end=new doublenode(data);
            temp.next=end;
            end.previous=temp;
        }
    }



    void delete(int pos)
    {
        doublenode temp=head;
        doublenode other=null;
        int i=1;
        int len=0;
        while(temp!=null){
            len++;
            temp=temp.next;
        }
        if(head==null||pos>len) {
            System.err.print("deletion not possible\n");
            return;
        }

        if(i==pos)
        {
            head=head.next;
            head.previous=null;
        }
        else
        {
            temp=head;
            while(temp.next!=null&&i!=pos)
            {
                i++;
                temp=temp.next;
            }
            other=temp.previous;
            other.next=temp.next;
            if(temp.next!=null)
                temp.next.previous=other;
        }

    }



    void insertInTheMiddle(int data,int pos)
    {
         int len=0;
         doublenode temp=head;
         while(temp!=null)
         {
             temp=temp.next;
             len++;
         }
         if(pos>len)
         {
             System.err.println("insertion not possible not enough nodes");
          return;
         }
         if(pos==1)
         {
             temp=new doublenode(data);
             temp.next=head;
             head.previous=temp;
             head=temp;
             return;
         }
         temp=head;
         int i=1;
         while(temp.next!=null&&i!=pos)
         {
             i++;
             temp=temp.next;
         }
         doublenode temp2=temp.previous;
         doublenode newnode=new doublenode(data);
         temp2.next=newnode;
         newnode.previous=temp2;
         newnode.next=temp;
         temp.previous=newnode;

    }



    void show()
    {
        StringBuilder br=new StringBuilder("");
        doublenode temp=head;
        while(temp!=null)
        {
            br.append(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println(br);

    }



    public static void main(String args[])
    {
        doubly_linked_list ob=new doubly_linked_list();
        Scanner s=new Scanner(System.in);
        System.out.println("enter how many elements");
        int t=s.nextInt();
        while(t-->0)
        {
            ob.insert(s.nextInt());
        }
        System.out.println("do you want to insert in the middle");
        char ch=s.next().charAt(0);
        if(ch=='y'||ch=='Y')
        {
            System.out.println("enter the element and the position");
            ob.insertInTheMiddle(s.nextInt(),s.nextInt());
        }
        System.out.println("do you want to delete ?");
        ch=s.next().charAt(0);
        while(ch=='y'||ch=='Y')
        {
            System.out.println("enter the position you want to delete");
            t=s.nextInt();
            ob.delete(t);
            ob.show();
            System.out.println("do you want to continue");
            ch=s.next().charAt(0);
        }
        ob.show();
    }
}
