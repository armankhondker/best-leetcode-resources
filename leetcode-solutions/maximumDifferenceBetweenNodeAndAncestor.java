// Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B 
// where V = |A.val - B.val| and A is an ancestor of B.

// (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

 

// Example 1:

// Input: [8,3,10,1,6,null,14,null,null,4,7,13]
// Output: 7
// Explanation: 
// We have various ancestor-node differences, some of which are given below :
// |8 - 3| = 5
// |3 - 7| = 4
// |8 - 1| = 7
// |10 - 13| = 3
// Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7


TAKE A TOP DOWN SOLUTION,
we will pass to every children, the max and min so far in their subtree,
if we reach a null, we will return max-min




//TC: O(N) to run dfs on all the nodes
//SC: O(N) for the stack space to run dfs

class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
        
    }
    public int dfs(TreeNode root, int max, int min){
        //1. check conditions
        if(root == null){
            return max-min; //this will take care of negatives implicitly
        }
        //2. process node
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        
        //3 call dfs 
        return Math.max(dfs(root.left,max,min), dfs(root.right,max,min)); 
    }
}