Given a string S, return the number of substrings of length K with no repeated characters.

//TC: O(n) time to loop trhough 
//SC: O(n) space for seen hashset

class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
        Set<Character> seen = new HashSet<>();
        int ans = 0;
        for (int hi = 0, lo = 0; hi < S.length(); ++hi) {
            while (!seen.add(S.charAt(hi))) // if duplicate char in window [lo, hi]
                seen.remove(S.charAt(lo++)); // shrink window by increasing low bound.
            if (seen.size() > K) // if the window wider than K.
                seen.remove(S.charAt(lo++)); // shrink the window by increasing low bound.
            if (seen.size() == K) // a solution found.
                ++ans;
        }
        return ans;
        
    }
}