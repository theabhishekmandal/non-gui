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

    /*
        inserting a word in a trie data structure, takes place from the root
        -   first check if root's children have the current character present or not, if not present then add it
        -   now make temp as children
     */
    public static void insert(String str){
        TrieNode temp = root;
        for(char c : str.toCharArray()){
            temp.getChildren().putIfAbsent(c, new TrieNode());
            temp = temp.getChildren().get(c);
        }
        temp.setIsWord(true);
    }
    public static boolean search(String str){
        TrieNode temp = root;
        for(char c : str.toCharArray()){
            HashMap<Character, TrieNode> tempchildren = temp.getChildren();
            if(!tempchildren.containsKey(c)){
                if(!temp.getIsWord()) return false;
                if(root.getChildren().containsKey(c)){
                    temp = root;
                }
                else return false;
            }
            temp = temp.getChildren().get(c);
        }
        return (temp != null && temp.getIsWord());
    }
    public static void main(String[] args) {
        root = new TrieNode();
        insert("abc");
        insert("abhi");
        System.out.println(search("abcabhi"));
    }
}

