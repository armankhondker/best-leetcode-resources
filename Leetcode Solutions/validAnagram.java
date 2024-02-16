Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.




//TC:O(N+M) where n is length of s and m is length of t
//SC:O(1) for constant space of map of chars 

class Solution {
    public boolean isAnagram(String s, String t) {
        char[] tmap = new char[26];
        for(char c: t.toCharArray()){
            tmap[c-'a']++;
        }
        
        char[] smap = new char[26];
        for(char c: s.toCharArray()){
            smap[c-'a']++;
        }
        
        return Arrays.equals(tmap,smap); 
    }
}