package DataStructures;
/**
 * This program is an implementation of the TRIE data structure.
 * A Trie is tree in which the nodes represents the alphabets.
 * Rather than storing the full word inside an arraylist or an array
 * we store the alphabet and a boolean value, which denotes that the
 * the path from root to the given node forms a word or not.
 * It is more memory efficient as compared to storing full words.
 * It works on the principle that we store the more common prefixes of word of
 * the dictionary:
 *        For eg: if we "abhi" and "abcd" the trie tree will look as follows
 *          ""
 *         /
 *        a
 *         \
 *          b
 *         / \
 *  true  c   h
 *             \
 *              i  true
 *   here we can see that the root having is an empty string
 *   and we store the COMMON PREFIXES of the words into the trie
 *   and marking the complete word as TRUE.
 */

import java.util.*;
import static java.lang.System.*;
public class TrieTest{
    // Total words
    private final static int alphabetsize = 26;
    static class Trie{
        // to mark if the word is complete
        boolean isWord;
        Trie[] children = new Trie[alphabetsize];
        Trie(){
            this.isWord = false;
            Arrays.fill(children, null);
        }
    }
    static Trie root;
    static void insert(String hel){
        // root not to be changed
        Trie temp = root;
        // traversing the characters of the string and storing it in the tree
        for(int i = 0; i < hel.length(); i++){
            int index = hel.charAt(i) - 'a';
            if(temp.children[index] == null)
                temp.children[index] = new Trie();
            temp = temp.children[index];
        }
        temp.isWord = true;
    }


    /*
    Now This is a special type of searching. In this we are searching
    if a concatenated word i.e word made by concatenating two or more
    strings exists as separated words in the dictionary. For eg:
    if the dictionary item contains {"i", "love", "indian", "food", "spicy"}.
    Then can we match "ilovespicy" or "spicyfoodindian" in the dictionary

    Now how the matching works. For eg: if I have a string "iloveindianfood".
    So we traverse the string character by character and check whether it is not null.
    If the character is found then we continue traversing the tree and increasing the
    character index.

    Now as we see in the above string case after matching "i" then we check for "l".
    But in the trie tree there is no "l" after "i". So there can be two possibilities
    1. if the word exist before the alphabet "l". (In this case "i" which is a complete word of a dictionary)
        if it exists then:
        1.1 we check if the current alphabet exist's in the array of the root.(In this case we
            check if "l" is in the root children's array). If the alphabet is in the starting of the root children
            array then we continue the search process.
        1.2 if the current alphabet does not exist in the array of the root then there is no word in the dictionary
            that starts with the given alphabet and we return false.

    2. if the word does not exist in dictionary then we return false. For eg; if the dictionary contains "children"
        and we are searching for "childred". So till "childre" the given string matches with the word in the
        dictionary. But notice that "childre" is not a complete word and so the boolean value is still false.
        so after "childre" we check "d" in the dictionary. But it does not exist and the previous word is also not
        in the dictionary and so we return false.


     */
    static boolean search(String arr){
        Trie temp = root;
        for(int i = 0; i < arr.length(); i++){
            int index = arr.charAt(i) - 'a';
            if(temp.children[index] == null){
                if(!temp.isWord) return false;
                else if(root.children[index] != null){
                        temp = root;
                        i--;
                        continue;
                    }
                    else return false;
            }
            temp = temp.children[index];
        }
        return (temp != null && temp.isWord);
    }
    public static void main(String args[]){
        root = new Trie();
        insert("i");
        insert("love");
        insert("indian");
        insert("spicy");
        insert("food");
        // search("iloveindianfood");
        out.println(search("iloveindiannfood"));
        out.println(search("iloveindianfood"));
        out.println(search("indianfoodspicy"));
        out.println(search("indianfoodspicyyy"));
        out.println(search("ilovespicy"));
        out.println(search("loove"));
        out.println(search("iiiiiii"));
        /*Scanner s = new Scanner(in);
        root = new Trie();
        out.println("enter the words of the dictionary seperated by space");
        String[] dict = s.nextLine().split(" ");
        for(int i = 0; i < dict.length; i++) insert(dict[i]);
        out.println("enter the concatenated word to be searched");
        String word = s.next();

        // search("iloveindianfood");
        out.println(search(word));*/
    }
}