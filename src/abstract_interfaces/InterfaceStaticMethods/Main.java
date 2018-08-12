package abstract_interfaces.InterfaceStaticMethods;
/**
An interface can have a default method and as well as a static method
in it
 */

interface number {
    default String getString(){
        return "returning string";
    }
    static String getname() {
      return "Abhishek mandal";
    }
}


public class Main implements number {
    public static void main(String args[]) {
        Main ob=new Main();

        //calling interface default method
        System.out.println(ob.getString());

        //calling interface static method
        System.out.println(number.getname());
    }
}
