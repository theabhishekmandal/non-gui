package Generics;

/**
 * This program is an example of lower bounded wild card.
 * Lower bounded wild card allow only those which have a specific type or super type of that type using "?" with super
 * keyword.
 */

class Animal{
    public String makeNoise(){
        return "buzzz";
    }
}

class Dog extends Animal{
    public String makeNoise(){
        return "woof woof";
    }
}

class Cat extends Animal{
    public String makeNoise(){
        return "meow meow";
    }
}
public class BoundedWildCardArgDemo8 {
   // private static <T super Dog> String makeNoise(T ob){
   //     return "";
   // }
    private static <T extends Dog> String makeNoise(T ob){
        return "";
    }
    public static void main(String[] args) {

    }
}
