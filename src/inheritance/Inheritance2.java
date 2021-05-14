package inheritance;

public class Inheritance2 extends C {
    public static void main(String[] args) {

        //in this  C class extends A class
        //now let suppose A class is Animal class
        //and suppose C class is cat class
        /*
         now if we do this
         now we assign the reference of superclass animal to class cat
         C c=new A();
         this will give compilation error
         because : every animal cannot be a cat
                   during compile time it checks the reference type of the reference variable
                   and tells which all methods it has

                   during runtime it checks the object type and tells which all methods it can have

                   for example:
                      Employee class is superclass and salesperson is it's subclass

                      then
                      Employee e=new salesperson();

                      compile time type is "Employee" and runtime type is of "salesperson"
          but this is possible
          A a=new C();
          because every cat is animal

          ALSO REMEMBER THAT IF THE METHODS ARE SAME THEN
          AR RUN TIME THE METHOD OF CLASS C WILL BE CALLED AND NOT THAT OF A

         */
        var c = new C();
        c.showABC();
        A a = new C();
        a.sayHello();
    }
}
