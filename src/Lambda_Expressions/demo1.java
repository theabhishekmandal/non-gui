package Lambda_Expressions;
public class demo1
{	
	public static void main(String args[])
	{
		Runnable r=()->{
			System.out.println("Knock knock someone there");
		};
		new Thread(r).start();
	}
}