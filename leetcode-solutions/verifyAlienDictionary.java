// In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. 
// The order of the alphabet is some permutation of lowercase letters.

// Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only 
// if the given words are sorted lexicographicaly in this alien language.

 

// Example 1:

// Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

//1. make a char map that makes from english dictionary to alien dictionary
//2. loop through array of words and check to see if the words are out of order, using compare method 



//TC: O(NM) where n is the number of strings in M and N is the number of chars in each string,
//can also so O(C) where C is total number of chars in every single string combined MxN

//SC: O(1) since the mapping is only of characters and can be represented with a 26 lenght array 


class Solution {
        int[] charmap = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        for(int i=0; i<order.length(); i++){
            charmap[order.charAt(i) - 'a'] = i; //get mapping from english dictionary to alien dictioanry
        }
        
        for(int i =1; i<words.length; i++){
            if(compare(words[i-1], words[i]) > 0) //if the comparisons are off, then need to return true
            {
                return false;
            }
        }
        return true;
        
    }
    public int compare(String word1, String word2){
        int i=0;
        int j=0;
        int char_compare_val = 0; 
        while(i<word1.length() && j<word2.length() && char_compare_val ==0){
            char_compare_val = charmap[word1.charAt(i) - 'a'] - charmap[word2.charAt(i) - 'a'];
            i++;
            j++;
        } 
        if(char_compare_val == 0){
            return word1.length() - word2.length(); //IN THE CASE WHERE [hel, hello] //hello needs to come first, this makes that true
        }else {
            return char_compare_val; 
        }
        
        
        
    }
}