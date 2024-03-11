You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. 
More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

Input: order = "cba", s = "abcd"

Output: "cbad"


1. keep count of each character in s
2. Iterate on the ordering, and append our count of each character to string StringBuilder
	Make sure to remove keys as we iterate
3. Now we need to add the chars which may not be in ordering, iterate on remaining keys

TC: O(N + M) to iterate on order string and s string
SC: O(M) to store the map for s occurences 

class Solution {
    public String customSortString(String order, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
        }
        StringBuilder res = new StringBuilder();
        for(int i=0; i<order.length();i++){
            char c = order.charAt(i);
            if(map.containsKey(c)){
                int num = map.get(c);
                for(int j=0; j<num; j++){
                    res.append(c);
                }
                map.remove(c);
            }
        }
        for(char c: map.keySet()){
            int num = map.get(c);
            for(int i=0; i<num; i++){
                res.append(c);
            }
        }

        return res.toString();
    }
}

SMALLER CODE, EXACT SAME LOGIC
1. use char count map 
2. append cahrs based on ordering
   public String customSortString(String ordering, String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) { ++count[c - 'a']; }  // count each char in T.
        StringBuilder sb = new StringBuilder();
        for (char c : ordering.toCharArray()) {                            
            while (count[c - 'a']-- > 0) { sb.append(c); }    // sort chars both in T and S by the order of S.
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            while (count[c - 'a']-- > 0) { sb.append(c); }    // group chars in T but not in S.
        }
        return sb.toString();
   }