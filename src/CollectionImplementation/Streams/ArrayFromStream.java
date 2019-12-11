package CollectionImplementation.Streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayFromStream {
    /*
    If we need to get an array out of the stream, we can simply use toArray()
     */
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 10).toArray();
        System.out.println(Arrays.toString(arr));

//        char[] charArray = IntStream.range(65, 65 + 26)
//                                    .collect(String::new,(x, y) -> x.concat(Integer.toString(y)), (x, y) -> );
    }
}
