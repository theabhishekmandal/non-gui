package StringsImplementation;
/**
 *  This is an example of how toString() method can be overriden inside the class.
 */

import static java.lang.System.*;
public class toStringDemo {
    static class Class{
        private String Firstname;
        private String Lastname;
        Class(String  Firstname, String Lastname){
            this.Firstname = Firstname;
            this.Lastname = Lastname;
        }
        public String toString(){
            return "hello " + this.Firstname + " " + this.Lastname;
        }
    }
    public static void main(String args[]){
        Class ob = new Class("Abhishek", "Mandal");
        out.println(ob);
    }
}
