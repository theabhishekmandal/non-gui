package inheritance;

/**
 * When one class extends another class then the order in which constructor are called is from superClass to subClass
 */
class One {
    public One() {
        System.out.println("in one constructor");
    }
}

class Two extends One {
   public Two() {
       System.out.println("in two constructor");
   }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        // will call One() constructor and Two() constructor
        new Two();
    }
}
