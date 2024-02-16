//Trie better than hashtable because gives us the ability to 
//1. Find ALL keys with a given prefix
//2. Go through a dataset of strings in lexographical order 
//3. Uses less space because as hash table increases in size could be a lot of collisions and search time
//could deteriorate to O(N)


//[c-'a']
//is a way to get the position of the character in the alphabet. 'a' - 'a' would give you 0. 'b' - 'a' would give you 1. 'c' - 'a' would give you 2, and so on.


//TC: INSERT: O(N) where n is length of word,  we iteratethrough the entire length of word
//    SEARCH: O(N) where n is length of word, we will have to go through each char in input word 
//    STARTSWITH: O(M) where m is length of prefix 

//SC: INSERT: O(N) where n is length of word, in worst case, we need to create N new nodes, for a 
//    word that is completely new and not in our Trie!
//    SEARCH: O(1) we dont need any extra space since trie is already created
//    STARTSWITH: O(1) we dont need any extra space since trie is created 
class TrieNode {
    boolean isEndOfWord;
    TrieNode[] children;
    
    // Initialize your data structure here.
    public TrieNode() {
        this.isEndOfWord = false;
        this.children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode runner = root;
        for(char c : word.toCharArray()){
            if(runner.children[c-'a'] == null) {
                runner.children[c-'a'] = new TrieNode();
            }
            runner = runner.children[c-'a'];
        }
        runner.isEndOfWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode runner = root;
        for(char c : word.toCharArray()) {
            if(runner.children[c-'a'] == null) {
                return false;
            } else {
                runner = runner.children[c-'a'];
            }
        }
        return runner.isEndOfWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode runner = root;
        for(char c : prefix.toCharArray()) {
            if(runner.children[c-'a'] == null) {
                return false;
            } else {
                runner = runner.children[c-'a'];
            }
        }
        return true;
    }
}