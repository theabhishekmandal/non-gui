package lambda_expressions;

/**
 * This program is an example of empty array exception. If the lambda expression throws a checked exception
 * then the exception must declare in the abstract method of the functional interface.
 */

interface DoubleNumericArrayFunc{
    double func(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception{
    EmptyArrayException() {
        super("empty array");
    }
}

public class HandlingExceptionInLambdaExpressionDemo {
    public static void main(String[] args) throws EmptyArrayException{
        double[] values = {1.0, 2.0, 3.0 ,4.0};

        // This block is finding the average of double values
        DoubleNumericArrayFunc average = n -> {
            double sum = 0;

            if(n.length == 0) {
                throw new EmptyArrayException();
            }

            for (double v : n) {
                sum += v;
            }
            return sum / n.length;
        };
        System.out.println("The average is " + average.func(values));

        // This causes the exception to be thrown
        System.out.println("The average is " + average.func(new double[0]));
    }
}
