// Given a string s, find the longest palindromic substring in s. 
// You may assume that the maximum length of s is 1000.

// Example 1:

// Input: "babad"
// Output: "bab"
// Note: "aba" is also a valid answer.



BRUTE FORCE APPROACH - O(N^3)

The obvious brute force solution is to pick all possible starting and ending positions 
for a substring, and verify if it is a palindrome.

Since there are about O(N^2) possible substrings and verifying if each is a palndirome will be another O(N) TC



EXPAND AROUND CENTER SOLUTION 

Time complexity : O(n^2) 
Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)

Space complexity : O(1)

   public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);    //case 1 where its RACECAR
            int len2 = expandAroundCenter(s, i, i + 1); //case 2 where its AABBAA 
            int len = Math.max(len1, len2);   //length of the palindrome who is longer out of len1 and len2
            if (len > end - start) {  //set max, found new longest palindromic substring
                start = i - (len - 1) / 2;  //just math to get starting index;
                end = i + len / 2; //just math to get ending index!
            }
        }
        return s.substring(start, end + 1);
}

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;  //length of substring, since the loop will make L and R go past the palindrome

    }
    
    //2 cases of palindrome
    //"RACECAR"  E is in the middle and has no match
    //AABBAAA   every letter has a match 
    
    //BRUTE FORCE O(n^3) because O(n^2) to compute all possible substring combinations and then O(n) within that loop to check if a substring is a palindrome
    
    //Dynamic Programming O(n^2) time to loop through all possible comibations but will compute if palindrome at each step , dont need to repeatedly check 
    //O(n^2) space to store the table that we build
    
    //ExpandFromMiddle O(n^2) time
    //O(1) constant space




DP SOLUTION, LESS OPTIMAL 

We can improve upon brute force solution by avoiding recomputation while validating palindromes
Consider the case "ababa". If we already knew that "bab" is a palindrome, 
it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.

//TC: O(n^2)
//SC: O(n^2) for dp array

public String longestPalindrome(String s) {
  int n = s.length();
  String res = null;
    
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
      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

// j - i < 3 || dp[i + 1][j - 1] means : whether it's a new palyndrome (length 2 or 3 ) 
//or one that we already discovered before .
// any new palyndrome discovered must start from one of these two options.

      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
        res = s.substring(i, j + 1); //technically substring is O(N) operation, so just record i and j
                                      //and return the substring outside of this loop
      }
    }
  }
    
  return res;
}