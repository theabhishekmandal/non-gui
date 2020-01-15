package Inheritence;


// static methods cannot be overriden
class a{
    public static void hello(){
        System.out.println("static hello from a");
    }
}

class b extends a{
    public static void hello(){
        System.out.println("static hello from b");
    }
}
public class InheritenceProblem1 {
    public static void main(String[] args) {
        b.hello();
    }
}
