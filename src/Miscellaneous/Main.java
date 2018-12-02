package Miscellaneous;
// fast input output in java
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main{
    //User defined class where the main code goes
    static class ProblemSolver
    {
        public static void multiply(String first,String second) {
            BigInteger one = new BigInteger(first);
            String dashes = "";
            String main_result = new BigInteger(first).multiply(new BigInteger(second)).toString();
            if (second.length() < 2) {
                second = '*' + second;
                int len = Math.max(first.length(), second.length());
                dashes = "";
                for (int i = 0; i < len; i++)
                    dashes += "-";
                len = Math.max(len, main_result.length());
                first = append_space(len - first.length()) + first;
                second = append_space(len - second.length()) + second;
                dashes = append_space(len - dashes.length()) + dashes;
                main_result = append_space(len - main_result.length()) + main_result;
                System.out.print(first + "\n" + second + "\n" + dashes + "\n" + main_result+"\n");


            } else {
                String partial_result[] = new String[second.length()];
                int i = second.length() - 1;
                int j = 0;
                while (i >= 0) {

                    String k = new BigInteger(first).multiply(new BigInteger(Character.toString(second.charAt(i)))).toString();
                    k = k + append_space(j);
                    partial_result[j] = k;
                    i--;
                    j++;
                }
                second = '*' + second;
                int len = Math.max(first.length(), second.length());
                dashes = "";
                for (i = 0; i < len; i++)
                    dashes += "-";
                len = Math.max(len, main_result.length());
                first = append_space(len - first.length()) + first;
                second = append_space(len - second.length()) + second;
                dashes = append_space(len - dashes.length()) + dashes;
                String part_result = "";
                for (i = 0; i < partial_result.length; i++) {
                    part_result += append_space(len - partial_result[i].length()) + partial_result[i] + "\n";
                }
                String newDashes = "";
                for (i = 0; i < len; i++)
                    newDashes += "-";
                System.out.print(first + "\n" + second + "\n" + dashes + "\n" + part_result + newDashes + "\n" + main_result+"\n\n");


            }
        }
        public  static void showResult(String first,String second,String result,char ch)
        {
            second = ch + second;
            String dashes = "";
            int len = 0;
            len = Math.max(first.length(), second.length());
            len = Math.max(len, result.length());
            for (int i = 0; i < len; i++)
                dashes += "-";
            first = append_space(len - first.length()) + first;
            second = append_space(len - second.length()) + second;
            result = append_space(len - result.length()) + result;
           System.out.println(first + "\n" + second + "\n" + dashes + "\n" + result+"\n");
        }
        public static String add(String first,String second)
        {
            BigInteger one=new BigInteger(first);
            BigInteger two=new BigInteger(second);
            one=one.add(two);
            return  one.toString();
        }
        public static String subtract(String first,String second)
        {
            BigInteger one=new BigInteger(first);
            BigInteger two=new BigInteger(second);
            one=one.subtract(two);
            return one.toString();
        }
        public static String append_space(int len)
        {
            String hello="";
            for(int i=0;i<len;i++)
                hello+=" ";
            return hello;
        }

        public void solveTheProblem(InputReader in,PrintWriter out) throws IOException
        {

            int t=in.nextInt();
            while(t-->0) {
                String check = in.nextString();
                char ch = ' ';
                String first = "";
                String second = "";
                int flag = 0;
                for (int i = 0; i < check.length(); i++) {
                    if (check.charAt(i) >= '0' && check.charAt(i) <= '9') {
                        if (flag == 0) {
                            first += check.charAt(i);
                        } else
                            second += check.charAt(i);
                    } else {
                        ch = check.charAt(i);
                        flag = 1;

                    }
                }
                String result = "";
                if (ch == '+') {
                    result = add(first, second);
                    showResult(first,second,result,ch);
                } else if (ch == '-')
                {
                    result = subtract(first, second);
                    showResult(first,second,result,ch);
                }
                else if (ch == '*')
                     multiply(first, second);
                //appending the sign to the first of the second number




            }
        }
    }

    //User defined class to read the input faster
    static class InputReader{
        final private int BUFFER_SIZE = 1 << 16;//To store a large buffer size
        private DataInputStream din;//This is used for fast IO in Java
        private byte[] buffer;//buffer array to store the elements in the buffer and byte is used as it takes less memory
        private int bufferPointer;//to keep track of the current index of the buffer
        private int bytesRead;//to keep track of the net size of the current buffer

        //Constructor for initialization
        public InputReader(){
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        //Method to read character by character
        private byte read() throws IOException{
            if(bufferPointer == bytesRead)fillBuffer();//if the buffer is fully read then take care of the buffer
            return buffer[bufferPointer++];//return the current buffer element
        }

        //Method to keep check of the buffer
        private void fillBuffer() throws IOException{
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);//to keep track of if the buffer has elements or not
            if(bytesRead == -1)buffer[0] = -1;//if the buffer is empty then return -1(or anything as we wish but we have to change the above program)
        }

        //Method to read an entire line
        public String readLine() throws IOException{
            int lineLengthMax=64; //maximum line length expected(could be changes as per the user necessity)
            byte[] buf = new byte[lineLengthMax];//array to store the line
            int cnt = 0;//starting index for the buf array
            byte c;//used for reading the input
            while((c = read()) != -1){
                if(c == '\n')break;//if found a new line then break out of the loop
                buf[cnt++] = (byte) c;//else put the element to the buffer
            }
            return new String(buf, 0, cnt);
        }

        //Method to return a string from the strea
        public String nextString() throws IOException{
            int stringLengthMax=(int)(1e6);//maximum necessary string length(could be changed if necessary)
            byte buf[]=new byte[stringLengthMax];//byte stream to store the string
            byte c=read();//to read the current input
            while(c<=' ')c=read();
            int cnt = 0;//starting index for the buf array
            do{
                if(c == '\n' || c<=' ')break;//if found a new line or space then break out of the loop
                buf[cnt++]=(byte)c;//else put the element into the buffer
            }while((c=read())!=-1);
            return new String(buf, 0, cnt);//return the string
        }

        //Method to read integers from the stream
        public int nextInt() throws IOException{
            int ret = 0;//Integer to be returned
            byte c = read();//read the starting element
            while(c <= ' ')c = read();//its main aim is to discard the white spaces
            boolean neg = (c == '-');//its there to check whether the number is negative or not
            if(neg)c = read();//if not included then the negative sign will be used as an integer
            //loop for reading till the last point of the integer
            do{
                ret = ret * 10 + c - '0';
            }while((c = read()) >= '0' && c <= '9');

            if(neg)return -ret;//return the negative number if found negative
            return ret;//used to return the non-negative number
        }

        //Method to read long from the stream
        public long nextLong() throws IOException{
            long ret = 0;//Long to be returned
            byte c = read();//read the starting element
            while(c <= ' ')c = read();//its main aim is to discard the white spaces
            boolean neg = (c == '-');//used to find if the element is negative
            if(neg)c = read();//if not included then the negative sign will be used as a long
            //loop for reading till the last point of the long
            do{
                ret = ret * 10 + c - '0';
            }while((c = read()) >= '0' && c <= '9');

            if(neg)return -ret;//return the negative number if it is found negative
            return ret;//used to return the non negative number
        }

        //Method to return double from the stream
        public double nextDouble() throws IOException{
            double ret = 0;//Double to be returned
            double div = 1;//Used for putting the numbers after the decimal point
            byte c = read();//read the starting element
            while(c <= ' ')c = read();//its main aim is to discard the white spaces
            boolean neg = (c == '-');//used to find if the element is negative
            if(neg)c = read();//if not included then the negative sign will be used as a double
            //loop for reading till the decimal of the double
            do{
                ret = ret * 10 + c - '0';
            }while((c = read()) >= '0' && c <= '9');
            //read after the decimal
            if(c == '.')
                while((c = read()) >= '0' && c <= '9')ret += (c - '0') / (div *= 10);

            if(neg)return -ret;//return the negative number if it is found negative
            return ret;//used to return the non negative number
        }

        //Wrapping up method
        public void close() throws IOException{
            if(din == null)return;//if there is no data input stream then no need to do anything
            din.close();//if there is a data input stream then flush it up
        }
    }


    //testing.Main method which is not to be touched at any case
    public static void main(String args[]) throws IOException{
        OutputStream outputStream = System.out;//Declaration of output stream
        InputReader in = new InputReader();//User defined InputReader class initialization
        PrintWriter out = new PrintWriter(outputStream);//Printwriter for fast output
        ProblemSolver problemSolver = new ProblemSolver();//User defined main class for solving the problem
        problemSolver.solveTheProblem(in, out);//User defined main method for solving the problem
        in.close();//Wrapping up
        out.close();//Wrapping up
    }
}