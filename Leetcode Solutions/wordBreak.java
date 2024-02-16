// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
//  determine if s can be segmented into a space-separated sequence of one or more dictionary words.

// Example 1:

// Input: s = "leetcode", wordDict = ["leet", "code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
// Example 2:

// Input: s = "applepenapple", wordDict = ["apple", "pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//              Note that you are allowed to reuse a dictionary word.
// Example 3:

// Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output: false


BRUTE FORCE - use recursion on all possible prefixes 
We check every possible prefix of that string in the dictionary of words, 
if it is found in the dictionary, then the recursive function is called for the remaining portion of that
 string. And, if in some function call it is found that the complete string is in dictionary, 
then it will return true.
//TC: O(2^n) because at each position we either split or dont split 
//SC: O(n)

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                return true;
            }
        }
        return false;
}


RECURSION WITH MEMOIZATION - GREAT APPRAOCH 

in previou approach we are calling the recusive function multiple times on same substrings 

//TC: O(N^2) to go through every possible combination 
//SC: O(N) for the recusrive calls and the boolean array to memoize what we have solved
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }


DYNAMIC PROGRAMMING APPROACH

// dp[i] stands for whether subarray(0, i) can be segmented into words from the dictionary. So dp[0] 
// means whether subarray(0, 0) (which is an empty string) can be segmented, and of course 
// the answer is yes.

// The default value for boolean array is false. Therefore we need to set dp[0] to be true.

// Time complexity : O(n^2) Two loops are their to fill dp array. check for word in dictionary
//takes O(1) time thanks to hashset

// Space complexity : O(n). Length of dp array is n+1.

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict); //USE SET TO REDUCE TC of checking dictionary
        boolean[] dp = new boolean[s.length() + 1];

        //DP[i]  epresents whether s(0...i) can be formed by dict (words can be split into all dict words)
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;   //no need to check the rest, we have found a word
                }
            }
        }
        return dp[s.length()];
    }
}

//CLEANER SOLUTION 
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return false; 
        int n = s.length(); 
        HashSet<String> dict = new HashSet<>(wordDict); 
        boolean [] dp = new boolean[s.length()+1];
        
        dp[0] = true; 
        for(int lo = 0; lo<n; lo++){
            if(!dp[lo]) continue; //we dont need to check starting from here, go to next iteration 
            for(int hi = lo+1; hi<=n; hi++){
                if(dict.contains(s.substring(lo,hi))){
                    dp[hi] = true;
                }
            }
        }
        return dp[s.length()]; 
        
    }
}







BFS SOLUTION - DONT IMPLEMENT THIS

//TC: O(N^2) to go through all possible sub arrays
//SC: O(N) for queue which can grow to size n, where n is length of s  

// Visualize the string as a tree where each node represents the prefix upto index endend. 
// Two nodes are connected only if the substring between the indices linked with those nodes 
// is also a valid string which is present in the dictionary. To form such a tree, 
// we start with the first character of the given string (say ss) which acts as the root of 
// the tree being formed and find every possible substring starting with that character which
//  is a part of the dictionary.

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }
}