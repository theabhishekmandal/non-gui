package effective_java.equals;

import java.util.Objects;

/**
 * Symmetric says that if x.equals(y) is true then y.equals(x) should also be true
 * Here cis.equals(string) will return true, but string.equals(cis) will return false.
 */
public class SymmetricDemo {
    static class CaseInsensitiveString {
        private final String string;

        CaseInsensitiveString(String string) {
            this.string = Objects.requireNonNull(string);
        }

        // violates symmetry
        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (o instanceof CaseInsensitiveString) {
                return string.equalsIgnoreCase(((CaseInsensitiveString) o).string);
            }

            if (o instanceof String) {
                // one way interoperability
                return string.equalsIgnoreCase((String)o);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        var cis = new CaseInsensitiveString("Hello");
        var string = "hello";
        System.out.println(cis.equals(string));
        System.out.println(string.equals(cis));
    }
}
