Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false



//TC: O(n)
//SC: O(n) in worst caase unbalanced tree for recursive stack 
//WITH REALLY LARGE TREES, can havea stack overflow 
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null & q == null) //both null so same tree
        {
            return true;
        }
        else if (p == null || q == null){ //only one is null so return false 
            return false;  
        }
        else if(p.val != q.val) //not same tree 
        {
            return false; 
        }else //now recursive case to check if leftsubtrees and right subtrees are same
        {
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right); 
        }
        
    }
}