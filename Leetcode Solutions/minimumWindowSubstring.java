// Given a string S and a string T, find the minimum window in S which will contain all the characters in T 
// in complexity O(n).

// Example:

// Input: S = "ADOBECODEBANC", T = "ABC"
// Output: "BANC"
// Note:

// If there is no such window in S that covers all characters in T, return the empty string "".
// If there is such window, you are guaranteed that there will always be only one unique minimum window in S.


USE SLIDING WINDOW!!

1. populate hashmap with counts of each 
2. have two points, expand the right pointer until we have a window that includes everything in String T 
3. then move left pointer inward, checking if we still have valid string, then update min is approriate
4. If we make window to small, continue with moving right, until we have all characters in T again!
5. return our minimum substring!!

class Solution {
    public String minWindow(String s, String t) {
        // corner case
        if(s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        
        // construct model
        int minLeft = 0;
        int minRight = 0;
        int min = s.length();
        boolean flag = false;
        
        Map<Character, Integer> map = new HashMap<>();
        int count = t.length(); // the number of characters that I need to match
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        
        // unfixed sliding window, 2 pointers
        int i = 0;
        int j = 0;
        while(j < s.length()){
            char c = s.charAt(j);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) >= 0) count--; // if still unmatched characters, then count--
            }
            
            // if found a susbtring
            while(count == 0 && i <= j)
                // update global min
                flag = true;
                int curLen = j + 1 - i;
                if(curLen <= min){
                    minLeft = i;
                    minRight = j;
                    min = curLen;
                }
                
                // shrink left pointer
                char leftC = s.charAt(i);
                if(map.containsKey(leftC)){
                    map.put(leftC, map.get(leftC) + 1);
                    if(map.get(leftC) >= 1) count++;
                }
                i++;
            } 
            j++;
        }
        
        return flag == true ? s.substring(minLeft, minRight + 1): "";
    }
}