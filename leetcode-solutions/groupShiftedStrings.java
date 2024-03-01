Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same 
shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

Basically we need to form some sort of key for each word to group them. (Remember the idea of group all anagrams?)

Consider acf and pru. Now notice the differnce between each characters.
acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers
ASCII difference to form key.

// Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, just make it 
// positive(rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.



INTUITON, use a haspmap to store every "key" to valid ordering of that key

//TC: O(N*M) where n is the number of strings in array, and M is the number of chars per each strings 
//could also so, O(C) where c is the total number of chars in the input (mxn) if all strings are length n

//SC: O(N) where n is the number of strings, because in worst case, the map will need to put all different N keys,
//into the map 

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>(); 
        List<List<String>> res = new ArrayList<>();
        if(strings == null || strings.length ==0) return res;
        
        for(String s : strings){
            
        String key = getKey(s);
        List<String> list = map.getOrDefault(key, new ArrayList<>());
        list.add(s);
        map.put(key, list);
        }
        
        return new ArrayList<>(map.values()); 
    }
    
 private String getKey(String s) {
    char[] chars = s.toCharArray();
    String key = "";
    for(int i = 1; i < chars.length; i++) {
        int diff = chars[i] - chars[i-1];
        key += diff < 0 ? diff + 26 : diff; //take care of negative values, 
        key += ",";
    }
    return key;
}
}