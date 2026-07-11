package data_structures.strings;

public class _7MediumCountPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

    public static int countSubstrings(String s) {
        String string = "#" + String.join("#", s.split("")) + "#";
        int[] radius = new int[string.length()];
        int count = 0;
        int c = 0, r = 0;
        for (int i = 0; i < string.length(); i++) {
            int mirror = 2 * c - i;
            if (i < r) {
                radius[i] = Math.min(r - i, radius[mirror]);
            }
            int left = i - radius[i] - 1;
            int right = i + radius[i] + 1;
            while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
                radius[i]++;
                left = i - radius[i] - 1;
                right = i + radius[i] + 1;
            }

            if (i + radius[i] > r) {
                c = i;
                r = i + radius[i];
            }
        }


        for (int i = 0; i < radius.length; i++) {
            if (string.charAt(i) == '#') {
                count += radius[i] / 2;
            } else {
                // for odd string we have to add one for the center character.
                count += (radius[i] / 2 + 1);
            }
        }

        return count;
    }
}
