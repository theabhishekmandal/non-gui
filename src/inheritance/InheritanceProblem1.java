package inheritance;


/**
 * when reference variable is of base class and object is allocated using the extended class then
 *   -  When methods are static then runtime polymorphism does not happen
 *   -  Runtime polymorphism does happen for instance methods
 *   -  final methods cannot be overridden
 */
class First {
    public final void hello(){
        System.out.println("final hello from a");
    }
    public void instanceHello(){
        System.out.println("instance hello from a");
    }
    public static void staticHello(){
        System.out.println("static hello from a");
    }
}

class Second extends First {
    @Override
    public void instanceHello(){
        System.out.println("instance hello from b");
    }
    public static void staticHello(){
        System.out.println("static hello from b");
    }
}

public class InheritanceProblem1 {
    public static void main(String[] args) {

       First obj = new Second();
       First.staticHello(); // will call static hello a
       obj.instanceHello(); // will call instance hello b
       obj.hello();
    }
}
