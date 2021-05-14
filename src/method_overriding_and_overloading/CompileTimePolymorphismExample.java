package method_overriding_and_overloading;

/**
 * Method overloading is compile time polymorphism
 * When an overloaded method is called, which of the overloaded method is to be called is determined through-
 *
 *     Types of the parameters - For example test(int a, int b) and test(float a, int b).
 *                              In these overloaded methods name is same but the types of the method parameters differ.
 *
 *     Number of the parameters - For example test(int a, int b) and test(int a, int b, int c).
 *                              In these overloaded methods name is same but the number of parameters is different.
 */
class Class {
    private static final String HELLO = "hello with ";
    public void hello() {
        System.out.println(HELLO + "nothing");
    }
    /* clashes with public void hello();
    public String hello() {
        return HELLO + "nothing";
    }

    private void hello() {

    }

    public static void hello() {

    }
     */

    public void hello(String name) {
        System.out.println(HELLO  + name + " from string");
    }

    // if a number is passed then it will call the primitive parameter hello
    public void hello(Integer integer) {
        System.out.println(HELLO  + integer + " from integer");
    }

    public void hello(int i) {
        System.out.println(HELLO + i + " from int");
    }

    public void hello(Double doubleNum) {
        System.out.println(HELLO  + doubleNum + " from double");
    }

    public void hello(Object name) {
        System.out.println(HELLO  + name + " from object");
    }


    // parameters with different ordering is also considered for overloading
    public void hello(String name, int age) {
        System.out.println(HELLO  + name + " " + age);
    }


    public void hello(int age, String name) {
        System.out.println(HELLO  + name + " " + age);
    }

    public void hello2(String one) {
        System.out.println(HELLO + one + " from hello2 string");
    }

    public void hello2(Object one) {
        System.out.println(HELLO + one + " from hello2 object");
    }

}
public class CompileTimePolymorphismExample {
    public static void main(String[] args) {
        var clazz = new Class();

        // will call the primitive one
        clazz.hello(1);
        clazz.hello((Integer)1);

        // clazz.hello(null); will cause ambiguity between Integer parameter and String parameter

        // this will call hello2(String) not the hello2(Object) as String is subclass of Object
        clazz.hello2(null);

    }
}
