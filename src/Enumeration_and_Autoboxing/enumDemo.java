package Enumeration_and_Autoboxing;
/*
   This program is an example of enumeration
   In this we use some of the basic things that we can do with enum constants
   we can compare them by using == operator
   we can use switch statements in them.
   Also we have two methods values() and valuesOf(String)
   the value() method returns an array that contains the list of enumerations constants
   and the valueOf(String) method returns the matching enum constant that is passed as an argument

 */
enum Apple
{
    Jonathan,GoldenDel,RedDel,Winesap,Cortland;
}
public class enumDemo {
    public  static void main(String args[])
    {
        Apple ap=Apple.RedDel;
        System.out.println("value of ap: "+ap);
        ap=Apple.GoldenDel;
        //compare two enum values
        if (ap==Apple.GoldenDel)
            System.out.println("ap contains: RedDel");

        //use a enum to control enum statement
        switch(ap)
        {
            case Jonathan:
                           System.out.println("Jonathan is Red");
                           break;
            case GoldenDel:System.out.println("Golden Delicious is yellow");
                            break;
            case RedDel:System.out.println("Red Delicious is red");
                        break;
            case Winesap:System.out.println("winesap is red");
            break;
            case Cortland: System.out.println("Cortland is red");
            break;
        }

        Apple app=Apple.valueOf("Winesap");
        System.out.println(app);


        Apple arr[]=Apple.values();
        for(Apple i:arr)
            System.out.print(i+" ");

    }
}
