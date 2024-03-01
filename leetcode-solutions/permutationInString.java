// Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
// In other words, one of the first string's permutations is the substring of the second string.

 
// Example 1:

// Input: s1 = "ab" s2 = "eidbaooo"
// Output: True
// Explanation: s2 contains one permutation of s1 ("ba").
// Example 2:

// Input:s1= "ab" s2 = "eidboaoo"
// Output: False

USE SLIDDING WINDOW!!!

NOTE, a string will contain a permutation of another IFFFF:
the count of every char in the string is THE SAME!! Just like anagrams

//TC: O(l1 + (l2-l1)) where l1 is length of string 1 and l2 is length of string 2 
//SC: O(1) space saved using array for map

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;
        
        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;  //every element has exact same count, SO SAME!!
        
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;  //so the entire substring length is of len1
            if (allZero(count)) return true;
        }
        
        return false;
    }
    
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}