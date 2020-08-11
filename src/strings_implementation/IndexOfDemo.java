package strings_implementation;

/**
 *  indexOf(char ch) method finds the first occurrence of the passed character in the String
 *  lasIndexOf(char ch) method finds the last occurrence of the passed character in the String.
 *
 *  indexOf(String str) and lastIndexOf(String str) methods can also be used to search for strings.
 *
 *  indexOf(String str, int startIndex) and lastIndexOf(String str) methods help to search a string in the
 *  given string from a specified index.
 */

public class IndexOfDemo {
    public static void main(String[] args) {
        String hel = "aajajajabbjbjbjfslkkjsdljlkjlkb";
        System.out.println(hel.indexOf('a'));
        System.out.println(hel.lastIndexOf('a'));
        System.out.println(hel.indexOf('b'));
        System.out.println(hel.lastIndexOf('b'));


        String wish = "I wish the wish the wish you wish to wish, but you wish the wish the wish I wish to wish";
        System.out.println(wish.indexOf("wish"));
        System.out.println(wish.lastIndexOf("wish"));

        System.out.println(wish.indexOf("wish", 10));
        System.out.println(wish.lastIndexOf("wish", 10));



    }
}
