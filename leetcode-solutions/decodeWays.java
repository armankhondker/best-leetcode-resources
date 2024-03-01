// A message containing letters from A-Z is being encoded to numbers using the following mapping:
// Given a non-empty string containing only digits, determine the total number of ways to decode it.

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26

// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).

// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


USE BOTTOM UP PROCESSING, DP

//TC: O(n) to go throguh entire string, n is length of string 
//SC: O(n) to store dp array

// dp[i]: represents possible decode ways to the ith char(include i), whose index in string is i-1
// Base case: dp[0] = 1 is just for creating base; dp[1], when there's one character, if it is not zero, it can only be 1 decode way. If it is 0, there will be no decode ways.
// Here only need to look at at most two digits before i, cuz biggest valid code is 26, which has two digits.
// For dp[i]: to avoid index out of boundry, extract substring of (i-1,i)- which is the ith char(index in String is i-1) and substring(i-2, i)
// First check if substring (i-1,i) is 0 or not. If it is 0, skip it, continue right to check substring (i-2,i), cuz 0 can only be decode by being together with the char before 0.
// Second, check if substring (i-2,i) falls in 10~26. If it does, means there are dp[i-2] more new decode ways.

class Solution {
    public int numDecodings(String s) {
        if(s==null || s.length() == 0) return 0; 
        int[] dp = new int[s.length()+1]; //need 1 extra space because 0 spaced
        dp[0] = 1; //dp[i] = # of ways to decode a string of length i 
        dp[1] = s.charAt(0) == '0' ? 0 : 1; //if string is 0, there will ony be one way to decode it
        
        
        for(int i =2; i<=s.length(); i++){
            int oneDigit = Integer.valueOf(s.substring(i-1, i)); //ONLY GET CURRENT DIGIT, convert char to int
            int twoDigit = Integer.valueOf(s.substring(i-2, i)); //GET LAST TWO DIGITS,since we can only go up to 26
            if(oneDigit >= 1 && oneDigit <= 9) {
               dp[i] += dp[i-1];  
            }
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()]; 
    }
}

EVEN MORE OPTIMIZED - BUT IMPLEMENT TOP FIRST!!!!

//Since we are only using dp[i-1] && dp[i-2] we can get rid of DP array and just use two variables!!!
//This will give us constant space, similar to fibincci/climbing stairs 

Bottom UP Processing - With constant spce 

//TC: O(N)
//SC: O(1) 

class Solution {
    public int numDecodings(String s) {
        if(s==null || s.length() == 0) return 0; 
        int[] dp = new int[s.length()+1]; //need 1 extra space because 0 spaced

        //dp[0] = 1; //dp[i] = # of ways to decode a string of length i 
        //dp[1] = s.charAt(0) == '0' ? 0 : 1; //if string is 0, there will ony be one way to decode it

        int pre1 = 1
        int pre2 = s.charAt(0) == '0' ? 0 : 1;
        int curr = 0; 
        
        for(int i =2; i<=s.length(); i++){
            int oneDigit = Integer.valueOf(s.substring(i-1, i)); //ONLY GET CURRENT DIGIT, convert char to int
            int twoDigit = Integer.valueOf(s.substring(i-2, i)); //GET LAST TWO DIGITS,since we can only go up to 26
            curr = 0; 
            if(oneDigit >= 1 && oneDigit <= 9) {
               curr += pre2; 
            }
            if(twoDigit >= 10 && twoDigit <= 26) {
                curr += pre1;
            }

            //INCREMENET THE DP POINTERS, something like this 
            pre1 = pre2;
            pre2 = curr; 
        }
        return curr; 
    }
}


