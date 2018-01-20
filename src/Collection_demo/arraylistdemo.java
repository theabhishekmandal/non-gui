package Collection_demo;

import java.util.*;
public class arraylistdemo
{
    public static void main(String args[])
    {
        ArrayList<Object> arr=new ArrayList<>();
        arr.add(1);
        arr.add("hello");
        arr.add(12.00000000);

        //Storing an integer to an ArrayList
        ArrayList<Integer> foo=new ArrayList<Integer>();
        foo.add(4);
        foo.add(5);
        foo.add(1);
        foo.add(2);
        foo.add(3);
        foo.add(7);
        foo.add(8);
        Collections.sort(foo);
        ListIterator it=arr.listIterator();
        StringBuilder st=new StringBuilder();
        while(it.hasNext())
        {
            st.append(it.next()).append('\n');
        }
        System.out.println(st);
    }
}
