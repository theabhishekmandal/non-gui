package LambdaExpressions;

/**
 * This program is an example of variable capture in lambda expressions.
 *
 * Variable Capture means variables defined by the enclosing scope of a lambda expression are accessible
 * within the lambda expression.
 *
 * In this, a lambda expression can have access to an instance or static variable and call the method by its
 * enclosing class.
 *
 * A lambda expression also has access to "this" (both implicitly and explicitly).
 *
 * However, when a lambda expression uses a local variable from its enclosing scope, a
 * special situation is created that is referred to as a VARIABLE CAPTURE.
 *
 * In this case, a lambda expression may use local variables that are EFFECTIVELY final
 * (you don't need to make the variable final and doing so would not cause an error).
 *
 * Also note that the a local variable of the enclosing scope cannot be modified by the lambda expression.
 * Doing so would remove its effectively final status, thus rendering it illegal for capture.
 */

interface myFunc{
    int func(int n);
}
public class demo7 {
    // an instance variable of the class
    int instance = 23;
    public static void main(String[] args) {
        // a local variable that can be captured.
        int num = 10;

        demo7 demob = new demo7();

        myFunc mylambda = (n) -> {
          // This use of num is okay. It does not modify num
          int v = num + n;

          // However the following is illegal because it attempts to modify
          // the value of num
          // num++;

          // lambda expression can have access to instance variable and can MODIFY it unlike local variable.
          demob.instance++;
          return v;
        };

        System.out.println(mylambda.func(20) + " " + demob.instance);

        // This is also wrong because it will remove the effective final status from num.
        // num = 9;
    }
}
