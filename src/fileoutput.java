import java.io.*;

public class fileoutput {
    public static void main(String args[])throws IOException
    {
        BufferedWriter brout=new BufferedWriter(new FileWriter("F:\\non-gui\\src\\hello.txt"));
        int k=0;
        brout.write(100000);
       for(int i=0;i<100000;i++)
       {
          // if(k>10)
            // k=0;
           brout.write(9+"\n");
       }


       brout.flush();
       brout.close();
    }
}
