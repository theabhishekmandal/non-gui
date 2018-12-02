package DataStructures;

/**
 *  This is a further optimisation for TrieDemo1. Here instead of using array we can use hashMap thereby saving space
 */

import java.util.HashMap;
class TrieNode{
    private boolean isWord;
    private HashMap<Character, TrieNode> children;
    TrieNode(){
        this.isWord = false;
        this.children = new HashMap<>();
    }
    public boolean getIsWord(){
        return this.isWord;
    }
    public HashMap<Character, TrieNode> getChildren(){
        return this.children;
    }
    public void setChildren(HashMap<Character, TrieNode> hashMap){
        this.children = hashMap;
    }
    public void setIsWord(boolean isWord){
        this.isWord = isWord;
    }
}
public class TrieDemo2 {
    private static TrieNode root;
    public static void insert(String str){
        TrieNode temp = root;
        for(int i = 0; i < str.length(); i++){
            // getting the hashmap
            HashMap<Character, TrieNode> tempchildren = temp.getChildren();
            // putting the value if not present
            tempchildren.putIfAbsent(str.charAt(i), new TrieNode());
            // setting the hashmap
            temp.setChildren(tempchildren);
            // making the children as the new temp
            temp = temp.getChildren().get(str.charAt(i));
        }
        temp.setIsWord(true);
    }
    public static boolean search(String str){
        TrieNode temp = root;
        for(int i = 0; i < str.length(); i++){
            HashMap<Character, TrieNode> tempchildren = temp.getChildren();
            // checking if the current node contains the character
            if(!tempchildren.containsKey(str.charAt(i))){
                // if not then the previous found letters must form a complete word
                if(!temp.getIsWord()) return false;
                // if not present then the character must be present in the root otherwise false
                if(root.getChildren().containsKey(str.charAt(i))){
                    temp = root;
                }
                else return false;
            }
            temp = temp.getChildren().get(str.charAt(i));
        }
        // return true if temp is not null and isWord is true
        return (temp != null && temp.getIsWord());
    }
    public static void main(String[] args) {
        root = new TrieNode();
        insert("abc");
        insert("abhi");
        System.out.println(search("abcabhi"));
    }
}

