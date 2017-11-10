package Enumeration_and_Autoboxing;

/**
Remember you cannot declare "enum constants" after the instance variables and methods of the enumeration
 */
enum Orange
{
    Green(10),yellow(12),red(20),RedDel;

    private int price;
    Orange()
    {

    }
    Orange(int p)
    {
        price=p;
    }
    int getPrice()
    {
        return price;
    }
}
public class enumdemo2 {
    public static void main(String args[])
    {
        //Orange or;
        //Getting all the values of the enum constants
        for(Orange i:Orange.values())
            System.out.println("Price of "+i+" is "+i.getPrice());


        //using the equals method to compare two different enumeration values
        System.out.println(Orange.RedDel.equals(Apple.RedDel));





    }
}
