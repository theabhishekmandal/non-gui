package Collection_demo;

import java.util.*;
public class arraylistdemo {
    public static void main(String args[])
    {
        ArrayList<Object> arr=new ArrayList<>();
        arr.add(1);
        arr.add("hello");
        arr.add(12.00000000);

        ListIterator it=arr.listIterator();
        StringBuilder st=new StringBuilder();
        while(it.hasNext())
        {
            st.append(it.next()).append('\n');
        }
        System.out.println(st);
    }
}
