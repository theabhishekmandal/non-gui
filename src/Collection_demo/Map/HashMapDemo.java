package Collection_demo.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  HashMap extends AbstractMap and implements the Map interface.
 *  A map is an object that stores associations between key value pairs.
 *  Given a key you can find its value.Both keys and values are objects.
 *  The keys must be unique but the values can be duplicated.
 *  Some maps can accept null values and null keys while some cannot.
 *  Also Maps don't implement the Iterable interface i.e you cannot iterate through a map using a for-each
 *  for  loop
 *  The order of the elements is not stored in hashMap
 */

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String , String> arr = new HashMap<>();
        arr.put("Abhishek", "Mandal");
        arr.put("hello", "world");
        arr.put("Mangekyo", "Sharingan");
        arr.put("Rasen", "Shuriken");
        arr.put("Taijuu", "kage bunshinn jutsu");

        // Getting the collections view of the map as map does not implements collections interface
        // entrySet() method provides a set of key value pairs
        Set<Map.Entry<String, String>> set = arr.entrySet();

        // now looping and displaying the keys and values
        for(Map.Entry<String, String> ob : set)
            System.out.println("Key: " + ob.getKey() + " Value:" + ob.getValue());
        
        System.out.println(); 
        
        // Getting the keyset and printing the values
        for(String i : arr.keySet()){
        	System.out.println("Key: "+ i + " " + "Value:" + arr.get(i));
        }
    }
}
