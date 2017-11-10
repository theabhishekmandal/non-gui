interface hello
{
    void demo();

}


class Main
{
    public static void main(String args[])
    {
        hello arr;
        arr=() -> System.out.println("hello world");

        arr.demo();
    }
}

