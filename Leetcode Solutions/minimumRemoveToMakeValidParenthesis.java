Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

INTUITON, we need to have balanced number of opening and closing braces

()( -----> remove last one 
)() -----> cant have CLOSING parenthsis before OPEN parenthsis
))(( ----> need to remove them all, cant form valid

//TC: O(N) to go through the entire string
//SC: O(N) to store the new string in a SB which will hold string with extra s
class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(); 
        
        int openBraces = 0 ;
        for(char c : s.toCharArray()){
            if(c == '('){
                openBraces++;
            }else if (c== ')'){  //we have found closing brace so decreement
                
                if(openBraces == 0){  //WE HAVE NOT seen an openeing brace yet, so exlude this from our output string
                    continue;
                }
                openBraces--;
            }
                      
            sb.append(c);
        }
        
        //NOW NEED TO ACCOUNT FOR IF WE HAVE EXTRA OPENING BRACES,
        //MUST START AT END, of sb because, we wouldnt want to delete the BALANCED opening braces
        //all of the excess opening braces will be at the end
        
        StringBuilder res = new StringBuilder();
        for(int i=sb.length()-1; i>=0; i--){
            if(sb.charAt(i) == '(' && openBraces-- > 0) continue; //SKIP and dont appened to result
            res.append(sb.charAt(i));
        }
        
         //since we had to loop backwards, need to return res in reverse
        
        return res.reverse().toString(); 
    }
}



MORE CLASSIC SOLUTION, using a STACK!!

//TC: O(N) to loop through the entire string 
//SC: O(N) to store the sb result, and hashset of indexes to remove 

The parentheses in a string are balanced if and only if these 2 conditions are met:

There are the same number of "(" and ")" in the string.
Scanning through the string from left to right and counting how many "(" and ")" there are so far, 
there should never be a time where there are more ")" than "(". We call count("(") - count(")") the 
balance of the string..

class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}