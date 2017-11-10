package Inheritence;
/*
this program shows how to call overridden method of superclass

 */
class hello
{
    void show()
    {
        System.out.println("this is the super class");
        return;
    }

}
class hi extends hello
{
    void show()
    {
        super.show();
        System.out.println("this is the sub class");
        return;
    }
    }
class DemoShipment
{
    public static void main(String args[])
    {
        hi object=new hi();
        hello obj2=object;
        object.show();

    }
}