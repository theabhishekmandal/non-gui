package strings_implementation;

import java.util.Arrays;

import static java.lang.System.*;
public class GetBytesDemo {
    public static void main(String[] args) {
        String hel = "Abhishek Mandal";
        byte[] blah = hel.getBytes();
        out.println(Arrays.toString(blah));
    }
}
