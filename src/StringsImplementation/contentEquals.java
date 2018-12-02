package StringsImplementation;

/**
 *  contentEquals(CharSequence str) or contentEquals(StringBuilder br) checks the contents of the two
 *  Strings irrespective of the fact that one of the object can be StringBuilder or StringBuilder.
 *
 *  Whereas equals(String str) checks whether the two objects have the same object as well as they are of String
 *  class
 */

public class contentEquals {
    public static void main(String[] args) {
        StringBuilder br =new StringBuilder("Abhishek");
        System.out.println("Abhishek".equals(br));
        System.out.println("Abhishek".contentEquals(br));
    }
}
