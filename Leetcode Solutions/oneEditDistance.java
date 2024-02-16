Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.


//TC: O(n)
//SC: O(n) because checking if substrings are equals, requires us to create new substrings in Java/Python since,
//sstrings are immutable, therefore O(n) runtime complexity 


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        //we need s to be shorter than t
        if(s.length() > t.length()){
            return isOneEditDistance(t, s);
        }
        
        if(s.length() - t.length() > 1){ //the lengths differ by more than 1, so will require more than 1 insertion 
            return false;
        }
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) != t.charAt(i)) {

            if(s.length() == t.length()){
                return s.substring(i+1).equals(t.substring(i+1));           
            }
            else { //differnet lengths 
                return s.substring(i).equals(t.substring(i+1)); 
            }
                
            }
        }
        
  //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
    return Math.abs(s.length() - t.length()) == 1;   
        
        
        
    }
}