package INNER_CLASS;

public class test //outer class
{
    int a=0;         //outer class default variable
    static int b=0;    //outer class static variable
    class abc
    {
        int c;         //inner class default variable
        void hello()      // a method for accessing the outer class variables
        {
            a++;          //changing the outer class variables
            b++;  //changing the outer class variables
                     //cannot access the members of outer class from the object of inner class in main method
            display();//can only access the outer class members with the help of inner class methods
        }
        void display()
        {
            test.this.display();
            System.out.println("this is  hello from the inside");
        }


    }
    void display()
    {
        System.out.println("this is hello from the outside");
    }
    int hell()         //outer class method
    {                   //in which we make a inner class object and
        //through this object we call the inner class which will access it's variable
        abc ob3=new abc();//and returns the value of inner class variable
        return ob3.c++;


    }
    static class staticClass
    {
        int wow;
        static int black;
        void changedemo()
        {
            wow++;
            com.company.test o=new com.company.test();
        }
    }
    public static void main(String args[])
    {
        test ob1=new test();       //creating outer class object
        ob1.a++;
        ob1.b++;
        ob1.hell();
        test.abc ob2=ob1.new abc();//creating inner class object
        ob2.hello();//calling inner class method
        ob2.c++;
        test.staticClass obj5=new test.staticClass();
        // black++;
        obj5.black++;
        obj5.wow++;


    }
}


/*
* you cannot access the inner class members using the outer class objects
* to access the inner class members first :
* 1 make outer class object in main method
* 2 call the outer class method
* 3 create a inner class object in the outer class method definition
* 4 now from here you can change the values of inner class variables
*
* you cannot directly access outer class members using the inner class objects directly
* to access the outer class members first:
* 1 make a inner class object in the main method
* 2 call the inner class method
* 3 then in the method definition you can alter the values of outer class members
* 4 if you directly chose to alter the values of the outerclass members using innerclass objects in the main method
* then it would through compilation error
*
*
* at last it shows that inner class objects have access to outer class members , through inner class methods
* and outer class methods cannot access the inner class members , it can only be done through inner class objects
 */