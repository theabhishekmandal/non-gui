package StringsImplementation;


public class JoiningStringsDemo {
    public static void main(String[] args) {
        String[] arr = {"hey", "hi", "hello", "hola"};
        String val = String.join("-", arr);
        System.out.println(val);
    }
}
