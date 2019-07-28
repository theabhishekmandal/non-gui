package Miscellaneous;
import java.util.*;
public class test
{
    static ArrayList<ArrayList<Integer>> arr;
    static boolean visited[];
    static HashSet<Integer> count=new HashSet<Integer>();

    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        String hello[]=new String[t];
        Set<String> another=new HashSet<String>();
        for(int i=0;i<hello.length;i++)
        {
            hello[i]=s.next();
            String count[]=hello[i].split("#");
            another.add(count[0]);
            another.add(count[1]);

        }

        System.out.println(getoutput(hello,another.size()+1));
    }

    private static int getoutput(String[] hello,int size) {
        arr=new ArrayList<>();

        int max=0;
        int min=0;


        for(int i=0;i<hello.length;i++)
        {
            String coun[]=hello[i].split("#");
            int one=Integer.parseInt(coun[0]);
            int two=Integer.parseInt(coun[1]);
            arr.get(one).add(two);
            arr.get(two).add(one);
            min=Math.min(one,two);
            max=Math.max(one,two);
        }

        visited=new boolean[size];
        Arrays.fill(visited,false);
        int paths[]=new int[size-1];
        int pathindex=0;
        printallpaths(min,max,visited,paths,pathindex);
        return count.size();

    }

    private static void printallpaths(int min, int max, boolean[] visited, int[] paths, int pathindex)
    {
        visited[min]=true;
        paths[pathindex++]=min;
        if(min==max)
        {
             count.add(paths[paths.length-2]);
        }
        else
            for(int i: arr.get(min))
            {
                if(!visited[i])
                    printallpaths(i,max, visited,paths,pathindex);

            }
        pathindex--;
        visited[min]=false;

    }
}