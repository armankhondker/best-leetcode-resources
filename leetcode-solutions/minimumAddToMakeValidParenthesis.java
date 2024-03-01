Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and 
	in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.


//Increment openbraces every

//TC: O(N) where n is length of input string
//SC: O(1) since we use a variable to act as our "Stack"

class Solution {
    public int minAddToMakeValid(String S) {
        int openBraces = 0;
        int res =0;
        for(char c: S.toCharArray()){
            if(c == '('){
                openBraces++;
            } else if(c == ')') {
                if(openBraces>0){
                    openBraces--;  //"pop" off the stack
                } else {
                    res++;  //we have a closing brce without a match
                }
            }
        }
        
        res+=openBraces; //if we have left over openingbraces that are unmatched
        return res; 
    }
}