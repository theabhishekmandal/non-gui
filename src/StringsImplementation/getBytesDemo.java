package StringsImplementation;

import java.util.Arrays;

import static java.lang.System.*;
public class getBytesDemo {
    public static void main(String[] args) {
        String hel = "Abhishek Mandal";
        byte[] blah = hel.getBytes();
        out.println(Arrays.toString(blah));
    }
}
