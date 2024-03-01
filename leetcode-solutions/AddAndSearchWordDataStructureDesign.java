Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A .
 means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true


INTUITION, build a trie data strucutre!!

FOR ADD, just loop through all of the trie starting from root, if that current char isnt in trie,
create a new trienode, once we reach last char, set the isWord field to true

FOR SEARCH, use a helper function that we call on if we reach a *, so we can iterate on rest of string


//TC: ADD O(N) where is n is length of word, SEARCH O(M) where m is the total number of chars in trie
//SC: ADD O(N) because we may have to allocate n new nodes for newly inserted word,
//    SEARCH O(M) stack space for recurisve calls

class WordDictionary {
    class Node{
        Node[] dict;
        boolean isWord;
        public Node() {
            dict = new Node[26];
            isWord = false;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {  //O(1)  O(1)
         root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {   //O(len) O(26 * len)
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.dict[i] == null) cur.dict[i] = new Node();
            cur = cur.dict[i];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word);
    }
    private boolean search(Node root, String word) { // O(26^len)
        if (word.length() == 0) return root.isWord;
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {  // len
            char c = word.charAt(i);
            if (c == '.') {
                for (int j = 0; j < 26; j++) {  //
                    if (cur.dict[j] != null && search(cur.dict[j], word.substring(i + 1))) return true; 
                }
                return false;
            }
            int ii = c - 'a';
            if (cur.dict[ii] == null) return false;
            cur = cur.dict[ii];
        }
        return cur.isWord;
    }
}