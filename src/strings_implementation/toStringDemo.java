package strings_implementation;
/**
 *  This is an example of how toString() method can be overriden inside the class.
 */

import static java.lang.System.*;
public class toStringDemo {
    static class Class{
        private final String firstName;
        private final String lastName;
        Class(String  firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String toString(){
            return "hello " + this.firstName + " " + this.lastName;
        }
    }
    public static void main(String[] args){
        Class ob = new Class("Abhishek", "Mandal");
        out.println(ob);
    }
}
