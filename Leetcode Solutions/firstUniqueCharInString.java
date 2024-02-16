// Given a string, find the first non-repeating character in it and return it's index.
//  If it doesn't exist, return -1.


//TC: O(n) to go through all letters in string
//SC: O(26) if we are limited to english alphabet, if not then O(N) with larger alphabet


//TWO pass solution
//pass one count the frequencies of each letter
//pass two go through the string and check if any char has a frequency of 1 and return that indexß

class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        
        for(char c: s.toCharArray())
        {
        hm.put(c, hm.getOrDefault(c,0) +1);  
        }
        for(int i=0; i<s.length(); i++)
        {
            
            if(hm.get(s.charAt(i)) == 1)
                return i;
        }
        
        return -1;
    }
}


TC: O(N)
SC: O(1) becaue map is only of size 26 
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}