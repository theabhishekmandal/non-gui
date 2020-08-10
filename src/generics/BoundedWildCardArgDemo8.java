package generics;

/**
 * This program is an example of lower bounded wild card.
 * Lower bounded wild card allow only those which have a specific type or super type of that type using "?" with super
 * keyword.
 *
 * Important points:
 * 1 While upper bounding all the subclasses are allowed
 * 2 While Lower bounding following points should be remembered:
 *
 *          class Hierarchy             :   Animal -> Dog -> Spitz
 *
 *       1  if generic class is this    :   class Gen<T extends Animal>
 *          if method accepts this      :   methodName(Gen<? super Spitz> ob)
 *          Then                        :   ob can be be casted to Animal Object only
 *
 *       2  if generic class is this    :   class Gen<T extends Dog>
 *          if method accepts this      :   methodName(Gen<? super Spitz> ob)
 *          Then                        :   ob can be casted to Animal and Dog only
 *
 *       2  if generic class is this    :   class Gen<T extends Spitz>
 *          if method accepts this      :   methodName(Gen<? super Spitz> ob)
 *          Then                        :   ob can be casted to Animal,Dog and Spitz
 *
 */

class Animal{
    private final String sound;
    Animal(String sound){
        this.sound = sound;
    }
    public String makeNoise(){
        return this.sound;
    }
}

class Dog extends Animal{
    private final String classname;
    Dog(String sound, String classname){
        super(sound);
        this.classname = classname;
    }
    public String getClassname(){
        return this.classname;
    }
}

class Spitz extends Dog{
    private final String feature;
    Spitz(String sound, String classname, String feature){
        super(sound, classname);
        this.feature = feature;
    }
    public String getFeature(){
        return this.feature;
    }
}

class GenAnimal<T extends Spitz>{
    private final T ob;
    GenAnimal(T ob){
        this.ob = ob;
    }
    public T getOb(){
        return this.ob;
    }
}

public class BoundedWildCardArgDemo8 {
    private static void makeNoise(GenAnimal<? super Spitz> ob){
        Spitz spitz = ob.getOb();
        System.out.println(spitz.getFeature());
        System.out.println(spitz.getClassname());
        System.out.println(spitz.makeNoise());
    }
    public static void main(String[] args) {
        // Can only pass Spitz object and not Animal or Dog type because generic class extends Spitz.
        GenAnimal<Spitz> ob = new GenAnimal<>(new Spitz("woof", "Dog", "hairy"));
        makeNoise(ob);
        //makeNoise(new GenAnimal<Dog>(new Dog("woof", "Dog")));
    }
}
