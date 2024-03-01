Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal 
letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer 
is unique.


Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the 
only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, 
so the final string is "ca".



USE A STACK, SIMILAR TO PARENTHSIS

//TC: O(N)
//SC: O(N-D) where D is total length of all duplicates, because this will be amount of space in stack

class Solution {
  public String removeDuplicates(String S) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for(char character : S.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1))
        sb.deleteCharAt(sbLength - 1);
    	sbLength--; 
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }
}