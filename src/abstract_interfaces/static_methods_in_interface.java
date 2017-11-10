package abstract_interfaces;
/*
an interface having a default method and as well as a static method
in it
 */
interface number
{
    default String getString()
    {
        return "returning string";
    }
    static String getname()
    {
      return "Abhishek mandal";
    }
}
public class static_methods_in_interface implements number {
    public static void main(String args[])
    {
        static_methods_in_interface ob=new static_methods_in_interface();
        System.out.println(ob.getString());    //calling interface default method
        System.out.println(number.getname());   //calling interface static method
    }
}
