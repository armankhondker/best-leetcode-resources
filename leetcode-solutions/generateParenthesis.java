Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


INTUTION, BACKTRACKING


The way I like to think about the runtime of backtracking algorithms is O(b^d), where b is 
the branching factor and d is the maximum depth of recursion.

Backtracking is characterized by a number of decisions b that can be made at each level of recursion. 
If you visualize the recursion tree, this is the number of children each internal node has. You can also 
think of b as standing for "base", which can help you remember that b is the base of the exponential.

If we can make b decisions at each level of recursion, and we expand the recursion tree to d levels 
(ie: each path has a length of d), then we get b^d nodes. Since backtracking is exhaustive and must 
visit each one of these nodes, the runtime is O(b^d).


//TC: O(2^n) we have decision to either include or not include
//SC: O(2n) because the recursion stack will grow to that maximum height 

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0,n);
        return res;
    }
    
    public void backtrack(List<String> res, String curr, int open, int close, int max ){  
    												//keep track of maximum parenthsis pairs 
                                                    //and amount of open/close in current string
        if(curr.length() == max * 2){  
            res.add(curr);
            return; 
        }
        
        if(open < max) backtrack(res, curr + "(", open+1, close, max); //open needs to be less than max
        if(close<open) backtrack(res, curr + ")", open, close+1,max);  //dont add combinations where close count
        																//is less than opening 
    }
}