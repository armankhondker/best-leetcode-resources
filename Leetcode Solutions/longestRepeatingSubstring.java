// Given a string S, find out the length of the longest repeating substring(s). 
// Return 0 if no repeating substring exists.


//TC: O(n^2)
//SC: O(n^2) for dp array 
class Solution {
    public int longestRepeatingSubstring(String S) {
         int n = S.length();
        int[][] dp = new int[n + 1][n + 1];//dp[i][j] means # of repeated chars for substrings ending at i and j
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}


//can improve on this by using binary search and sorting the string 
//use a hashset to keep track of seen strings 


//TC: O(NLogN)
//SC: O(N^2) for hashseet of all the comibinations of length L

class Solution {
  /*
    Search a substring of given length
    that occurs at least 2 times.
    Return start position if the substring exits and -1 otherwise.
    */
  public int search(int L, int n, String S) {
    HashSet<String> seen = new HashSet();
    String tmp;
    for(int start = 0; start < n - L + 1; ++start) {
      tmp = S.substring(start, start + L);
      if (seen.contains(tmp)) return start;
      seen.add(tmp);
    }
    return -1;
  }

  public int longestRepeatingSubstring(String S) {
    int n = S.length();
    // binary search, L = repeating string length
    int left = 1, right = n;
    int L;
    while (left <= right) {
      L = left + (right - left) / 2;
      if (search(L, n, S) != -1) left = L + 1;
      else right = L - 1;
    }

    return left - 1;
  }
}