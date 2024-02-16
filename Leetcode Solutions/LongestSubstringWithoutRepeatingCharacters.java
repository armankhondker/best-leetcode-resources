//Given a string, find the length of the longest substring without repeating characters.

//TC: O(2n) = O(n) because in worst case every character will be visited by i and j 
//SC: O(n) for hashset of up to size n 

//Use two pointers that initially start at begginning of the array
//use hashset and sliding window technique to keep track of distinct chars in our current window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        //TWO POINTER METHOD
        int n = s.length();
        int i = 0, j=0, ans=0;
        HashSet<Character> set = new HashSet<Character>();
        while(i<n && j<n)
        {
            if(!set.contains(s.charAt(j)))   //found new character
            {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j-i);
            }
            else
            {
                set.remove(s.charAt(i));  //already have this character so need to remove
                i++;
            }
        }
        return ans;
        
    }
}

BETTER SOLUTION 

Instead of using a set to tell if a character exists or not, we could define a mapping of 
the characters to its index. Then we can skip the characters immediately when we found a 
repeated character.

//TC: O(n) because every character only visited 1 time!!
//SC: O(min(n,m)) hashmap size is min between length of s N and size of charset M
//NOTE, can use a integer array for map for make O(1) space if charset is limited 
 

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int n = s.length(); 
        HashMap<Character, Integer> map = new HashMap<>(); //map from char, to index in array
        for(int j=0, i=0; j<n; j++){
            if(map.containsKey(s.charAt(j))){ //we have found dupliate char, so increment i, based off map or its val
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1); //total length of window
            map.put(s.charAt(j) , j+1); //map from Character, to index in the array!!
        }
        return ans; 
    }
}

