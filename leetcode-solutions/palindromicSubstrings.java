// Given a string, your task is to count how many palindromic substrings in this string.

// The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

// Example 1:

// Input: "abc"
// Output: 3
// Explanation: Three palindromic strings: "a", "b", "c".
 

// Example 2:

// Input: "aaa"
// Output: 6
// Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

USE EXPAND AROUND CENTER

A very easy explanation with an example
Lets take a string "aabaa"

Step 1: Start a for loop to point at every single character from where we will trace the palindrome string.
checkPalindrome(s,i,i); //To check the palindrome of odd length palindromic sub-string
checkPalindrome(s,i,i+1); //To check the palindrome of even length palindromic sub-string

Step 2: From each character of the string, we will keep checking if the sub-string is a palindrome and increment the palindrome count. To check the palindrome, 
keep checking the left and right of the character if it is same or not.

//TC: O(N^2) to loop through the string (IN WORST CASE ex. AAAAAAAAAA)
//SC: O(1) no extra space needed 
public int countSubstrings(String str) {
        if(str == null || str.length() < 1) return 0;
        int count = 0;
        for(int i=0;i<str.length();i++){
            count += countPalindromes(str, i, i); //Count odd sized palindromes starting here
            count += countPalindromes(str, i, i+1); //Count even sized starting here
        }
        return count;
    }
    

    //EXPAND THE WINDOW while we still have a palindrome!!
    private int countPalindromes(String str, int left, int right){
        int count = 0;
        while(left>=0 && right<str.length() && str.charAt(left) == str.charAt(right)){
            left--;
            right++;
            count++; //everytime we expand this is a palindrome so incremenet count
        }
        return count;
    }


DYNAMIC PROGRAMMING SOLUTION
similar to longest palindromic substring 

//TC: O(N^2) to build dp array
//SC: O(N^2) to hold to dp array 

public int countSubstrings(String s) {
    int n = s.length();
    int res = 0;
    boolean[][] dp = new boolean[n][n];

//EXPLAINATION OF FOR LOOPS AND WHY THEY ARE THE WAY THEY ARE
// j must be greater than or equal i at all times. Why? i is the start index of the substring, 
// j is the end index of the substring. It makes no sense for i to be greater than j. Visualization helps me,
//  so if you visualize the dp 2d array, think of a diagonal that cuts from top left to bottom right. We are 
//  only filling the top right half of dp.

   // Why are we counting down for i, but counting up for j? Each sub-problem dp[i][j] depends on 
   // dp[i+1][j-1] (dp[i+1][j-1] must be true and s.charAt(i) must equal s.charAt(j) for dp[i][j] to be true).
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i  + 1 < 3 || dp[i + 1][j - 1]);
            if(dp[i][j]) ++res;
        }
    }
    return res;
}