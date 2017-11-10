package codechef;
import java.util.*;
/**
 * This program is the problem of Cookoff beginner section codechef
 */
public class hashmap {
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            Map<String,Integer> arr=new HashMap<>();
            int n=s.nextInt();
            for(int i=0;i<n;i++)
            {
                String hel=s.next();
                if(arr.get(hel)!=null)
                {
                    arr.put(hel,arr.get(hel)+1);
                }
                else
                    arr.put(hel,1);
            }

            if(arr.get("cakewalk")!=null&&arr.get("simple")!=null&&arr.get("easy")!=null&&(arr.get("medium")!=null||arr.get("easy-medium")!=null)&&(arr.get("hard")!=null||arr.get("medium-hard")!=null))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
