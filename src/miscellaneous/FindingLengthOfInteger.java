package miscellaneous;
/**
	This is the logarithmic approach to find the length of the digits
*/
	public class FindingLengthOfInteger {
		public static void main(String[] args){
			int n = 123456;
			System.out.println((int)(Math.log10(n) + 1));
		}
	}