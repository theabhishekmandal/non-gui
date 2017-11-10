package spoj;

/**
 * Created by abhishek mandal on 7/15/2017.
 */
import java.util.*;
public class Spoj_transform_the_expression {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        Stack<Character> st=new Stack<Character>();
        int t=s.nextInt();
        while(t-->0)
        {
            char k[]=s.next().toCharArray();
            String alpha="";
           // String op="";
            for(int i=0;i<k.length;i++)
            {
                if(k[i]>='a'&&k[i]<='z')
                    alpha+=k[i];
                else
                    if(k[i]!=')')
                    {
                        st.push(k[i]);
                    }
                else
                    if(k[i]==')')
                    {
                        while(st.peek()!='(')
                        {
                            alpha+=st.pop();
                        }
                        if(st.peek()=='(')
                            st.pop();
                    }
            }
            System.out.println(alpha);
        }
    }
}
