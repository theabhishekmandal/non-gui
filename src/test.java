
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class test {
    private String floor;
    public static void main(String[] args) {
//        solve3();
//        call();
        solve4();
    }
    static int characterWidth;
    private static void solve4() {
        Scanner s = new Scanner(System.in);
        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < 5; i++) list.add(new StringBuilder());
        getS(list);
        getA(list);
        getS(list);
        System.out.println(String.join("\n", list));
    }

    private static void getS(List<StringBuilder> list){
        list.get(0).append(" ").append("******");
        list.get(1).append(" ").append("*     ");
        list.get(2).append(" ").append("******");
        list.get(3).append(" ").append("     *");
        list.get(4).append(" ").append("******");
    }

    private static void getA(List<StringBuilder> list){
        list.get(0).append(" ").append("******");
        list.get(1).append(" ").append("*    *");
        list.get(2).append(" ").append("******");
        list.get(3).append(" ").append("*    *");
        list.get(4).append(" ").append("*    *");
    }

    private static void solve3(){
        System.out.println(Optional.ofNullable(null).orElse("null"));
    }

    private static void solve(){
        String hello = "hello world";
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Users/amanda16/Desktop/network drive folders/non-gui/src/hello.txt"));
            String k = null;
            StringBuilder builder = new StringBuilder();
            while((k = br.readLine()) != null){
                k = k.trim();
                if(k.startsWith("CREATE")){
                    builder.append(k).append("\n");
                    while(((k = br.readLine()) != null) && !k.trim().equals(")")){
                        builder.append(k.trim()).append("\n");
                    }
                    builder.append(")\n");
                }
            }
            System.out.println(builder);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void solve2(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Users/amanda16/Desktop/network drive folders/non-gui/src/hello.txt"));
            String k = null;
            StringBuilder builder;
            ArrayDeque<Character> array = new ArrayDeque<>();
            List<String> list = new ArrayList<>();
            while((k = br.readLine()) != null){
                k = k.trim();
                if(k.startsWith("CREATE")){
                    builder = new StringBuilder();
                    builder.append(k).append("\n");
                    String nextLines;
                    boolean isBracketsBalanced = true;
                    while((nextLines = br.readLine()) != null){
                        nextLines = nextLines.trim();
                        for(int i = 0; i < nextLines.length(); ++i){
                            char currchar = nextLines.charAt(i);
                            if(currchar == '(') array.push(currchar);
                            else if(currchar == ')') {
                                if(array.isEmpty()) throw new Exception("not enough opening brackets");
                                array.pop();
                            }
                            builder.append(currchar);
                        }
                        builder.append("\n");
                        if(!array.isEmpty()){
                            isBracketsBalanced = false;
                            break;
                        }
                    }
                    if(!isBracketsBalanced){
                        while((nextLines = br.readLine()) != null){
                            nextLines = nextLines.trim();
                            for(int i = 0; i < nextLines.length() && !array.isEmpty(); ++i){
                                char currchar = nextLines.charAt(i);
                                if(currchar == '(') array.push(currchar);
                                else if(currchar == ')') array.pop();
                                builder.append(currchar);
                            }
                            if(array.isEmpty()) break;
                            builder.append("\n");
                        }
                        if(!array.isEmpty())
                            throw new Exception("not enough closing brackets");
                    }
                    list.add(builder.toString());
                }
            }
            System.out.println(list);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void call(){

        Map<String, Object> map = new HashMap<String, Object>(){
            {
                Map<String, Object> inside = new HashMap<String, Object>(){
                    {
                        put("abhishek", "mandal");
                    }
                } ;
                put("hello", inside);
            }
        };
        System.out.println(map);

    }

    private static void print(Object...args){

        //System.out.println(Arrays.deepToString(args));
        printAgain(args);
    }

    private static void printAgain(Object...args){
        //System.out.println(Arrays.toString(args));
        Object[] newarr = Arrays.copyOf(args, args.length + 2);
        newarr[newarr.length - 2] = 1;
        newarr[newarr.length - 1] = 2;
        System.out.printf("%s %s %s %s\n", newarr);
    }

    private static void printall(String...args){
        int min = 1;
        int sec = 12;
        System.out.printf("%d %d %d", args, min, sec);
    }
}
