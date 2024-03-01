Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.



TWO POINTER APPROACH 

SOLUTION:
//1. Iterate through the string until you find first pair of chars that dont match!!
//2. DO ANOTHER palindrome check (through helper function) to check if the rest of string is palindrome EITHER
//witout the ith leter, or without the jth letter 


//TC: O(N), because we go through the string at MAX 3 times!!
//SC: O(1) no extra space 

class Solution {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length()-1;
        
        while(start < end){
            
            if(s.charAt(start) != s.charAt(end))
            {
                return isPalindromeWithoutThese(s, start+1, end) || isPalindromeWithoutThese(s,start,end-1); 
            }
            start++;
            end--; 
            
        }
        
        return true; 
    }
    public boolean isPalindromeWithoutThese(String s, int i, int j){
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true; 
    }
}