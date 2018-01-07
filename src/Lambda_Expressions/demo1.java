package Lambda_Expressions;

/**
 * Lambda Expressions are used on functional interfaces.
 *
 * Functional interfaces are those interfaces which have single abstract method .
 *
 * a general lambda expression is given by
 *    () -> //do something
 *
 *  the -> is called the arrow operator.
 *  left of this is the number of parameters required by the interface.
 *  right side is called the lambda body
 *
 *  While using the interface you do not need to implement the interface.
 *
 *  Below is an example of block lambda which means a lambda expression having a body
 *
 *  In the next example there is non-block lambda expression i.e which does not have a body
 *  but a single expression.
 */
public class demo1
{	
	public static void main(String args[])
	{
		Runnable r= ()->{
			System.out.println("Knock knock someone there");
		};
		new Thread(r).start();
	}
}