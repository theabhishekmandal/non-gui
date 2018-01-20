package abstract_interfaces;
/*
this code is example of nested interface
 */
/*
a outer interface having another interface as a memeber
both the interface having method definitions
 */
interface A
{
    void Ahello();
    interface B
    {
         void Bhello();
    }
}

/*
a class which implements the method of interface A
and also implements the method of the inner interface by using the outer interface name
 */
class C implements A,A.B
{
    public void Ahello()
    {
        System.out.println("Ahello");
    }
   public void Bhello()
    {
        System.out.println("Bhello");
    }
  interface D
    {
         void Dhello();
    }
}
/*
 a class Nested_interface extending class C and implementing the inner interface D

 If { you don't implement the C.D interface then the Dhello() method will be treated as the
     instance method of Nested_interface }
 ELSE {the void Dhello method is treated as the method of the interface D}


 */
public class Nested_interface extends C implements C.D
{
    public void Dhello()
    {
        System.out.println("hello D");
    }
    public static void main(String args[])
    {
        Nested_interface ob=new Nested_interface();
        ob.Dhello();ob.Ahello();ob.Bhello();

    }
}
