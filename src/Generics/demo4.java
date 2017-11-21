package Generics;
interface Average
{
    double average();
}

class BoundDemo2<T extends Number &Average>
{
    T[] nums;
    BoundDemo2(T[] ob)
    {
        this.nums=ob;

    }
   public  double average()
    {
        double ans=0.0;
        for(T i:nums)
            ans+=i.doubleValue();

       return ans/nums.length;
    }
}
public class demo4 {
    static public void main(String args[])
    {
        Integer[] arr={1,2,3,4,5};
        BoundDemo2<Integer> iob=new BoundDemo2<>(arr);
        System.out.println(iob.average());
    }
}
