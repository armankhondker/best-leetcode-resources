
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.


SLIDING WINDOW APPROACH

//TC: O(N) in worst case because, each step (n iteration)
//SC: O(k) space used in hashmap for at most k elements 

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>(); //key will be a char in the current sliding window, 
                                                        //value will be index of rightmost position of char
        int res = 0; 
        int left = 0;
        
        for(int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            map.put(c , map.getOrDefault(c,0) +1);
            while(map.size() > k)
            {
                char leftChar = s.charAt(left);
                if(map.containsKey(leftChar)){
                map.put(leftChar, map.get(leftChar)-1);
                    if(map.get(leftChar) == 0)
                    {
                        map.remove(leftChar);
                    }
                    
                }
                left++;
            }
            res = Math.max(res, i-left+1);
        }
        
        return res; 
    }
}