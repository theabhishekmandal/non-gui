package lambda_expressions;

/**
 * This is an example of METHOD REFERENCE to INSTANCE METHODS using Lambda Expressions.
 *
 * In the previous example we created a method reference to instance methods by using objects.
 * In that example the method reference was specific to the given object. This means every method
 * was independent of another object.
 *
 * But, here the instance method can be used with any object of a given class not just a specified object.
 * The general syntax is given by:
 *                              ClassName::instanceMethodName
 *
 * Notice here you don't have to create an object to access the instance methods.
 *
 * In the program, notice that HighTemp has two instance methods:
 * sametemp(), lowtemp()
 *
 * First returns true if two HighTemp objects contains the same temperature.
 * The second return true if the temperature of the invoking object is less than that of the passed object.
 *
 * Each method is compatible with the testFunc interface because the invoking object can be mapped to the
 * first parameter of func() and the second argument(passed as object in the count method) mapped to func()'s
 * second parameter.
 *
 * Thus, when the expression
 *
 * Hightemp::sametemp
 *
 * is passed to the count() method, an instance of the functional interface testFunc is created in which
 * the parameter type of the first parameter is that of the invoking object of the instance method, which is HighTemp.
 * The type of the second parameter is also HighTemp  because that is th type of the parameter to sameTemp(). The
 * same is true for the lowtemp() method.
 */

interface TestFunc<T> {
    boolean func(T one, T two);
}

class HighTemp{
    private final int htemp;

    HighTemp(int temp){
        this.htemp = temp;
    }

    public boolean lowTemp(HighTemp ob2){
        return this.htemp < ob2.htemp;
    }

    public boolean sameTemp(HighTemp ob2){
        return this.htemp == ob2.htemp;
    }
}

public class LambdaExpressionWithInstanceMethodReferenceDemo2 {

    static <T> int count(TestFunc<T> hel, T[] vals, T v){
        int counter = 0;
        for(T i : vals) {

            /*
            if you see you will find that lowTemp and sameTemp has one parameter,
            but the method below which is of the interface has two arguments. The basic
            explanation is that the invoking object is the first argument and the passed argument is
            the second
             */
            if(hel.func(i,v)) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {

        int count = 0;
        HighTemp[] arr = {new HighTemp(89), new HighTemp(89), new HighTemp(89), new HighTemp(90),
                            new HighTemp(92), new HighTemp(94), new HighTemp(60)};
        count = count(HighTemp::lowTemp, arr, new HighTemp(100));
        System.out.println(count + " days have less temp than 100");

        count = count(HighTemp::sameTemp, arr, new HighTemp(89));
        System.out.println(count + " days have same temp");
    }
}


