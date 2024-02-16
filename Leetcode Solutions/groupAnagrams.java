//Given an array of strings, group anagrams together.

// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
// Output:
// [
//   ["ate","eat","tea"],
//   ["nat","tan"],
//   ["bat"]
// ]

//KEY OBSERVATION:: all of the words apart of an anagram look the SAME when sorted 

//TC: O(nmlogm) where n is length of strs array, m is max length of string in strs.  outer loop runs
//n times and inner loop takes O(mlogm) for sorting the string of max lenght m
//SC: O(NM) for total space to store the grouped anagrams 

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> groupedAnagrams = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();  //map to hold sorted word to all possible combinations that are equal 
        
        for(String current : strs)
        {
            char[] characters = current.toCharArray();
            Arrays.sort(characters); 
            String sorted = new String(characters);
            if(!map.containsKey(sorted))
            {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(current);
        }
        
        groupedAnagrams.addAll(map.values());  //add all of our values to list to return 
        return groupedAnagrams; 
        
    }
}

BETTER Solution - categorize by count!!

Intuiton - Two strings are anagrams if and only if their character 
counts (respective number of occurrences of each character) are the same.

//TC: O(NM) where n is length of strs, and M is max length of a string in strs
//SC: O(NM) for space stored in map 

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList(); // just return empty array
        HashMap<String, List> ans = new HashMap<String, List>(); //map to hold, string to all
                                                                //valid anagrams
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0); //reset count on every iteration
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder(""); //will hold number of occurences of each char
            for (int i = 0; i < 26; i++) {
                sb.append('#'); // just to split it up and make it easier to debug
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}