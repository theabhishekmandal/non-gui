import java.util.regex.*;
import java.util.*;
import java.io.*;
import static java.lang.System.*;
public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String hel = br.readLine();
        String save = "";
        while(hel != null)
        {
            save += hel;
            hel = br.readLine();
        }
        Pattern p = Pattern.compile(br.readLine());
        Matcher m = p.matcher(save);
        while(m.find())
        {
            out.println(m.group());
        }
    }
}