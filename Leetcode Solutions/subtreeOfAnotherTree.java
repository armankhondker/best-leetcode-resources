Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.


//USE PREORDER PROPERTY OF TREE, then chcek if tree 2 is substring of tree 1 
//append # to mark that 


// You can also note that we've added a '#' before every considering every value. If this isn't done, 
// the trees of the form s:[23, 4, 5] and t:[3, 4, 5] will also give a true result since the 
// preorder string of the t("23 4 lnull rull 5 lnull rnull") will be a substring of the 
// preorder string of s("3 4 lnull rull 5 lnull rnull"). Adding a '#' before the node's value solves this problem.

Time complexity : O(m^2+n^2+m*n). A total of nn nodes of the tree ss and mm nodes of tree tt are traversed. 
Assuming string concatenation takes O(k) time for strings of length k and indexOf takes O(m*n).

Space complexity : O(max(m,n). The depth of the recursion tree can go upto nn for tree t and m for tree s in worst case.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree1 = preorder(s,true);
        String tree2 = preorder(t, true); 
        return tree1.indexOf(tree2) >=0;
        
    }
    
    public String preorder(TreeNode t, boolean left)
    {
        if(t==null)
        {
            if(left) return "lnull";
            else return "rnull"; 
        }
        return "#"+ t.val + "" +preorder(t.left, true) + preorder(t.right ,false);
        
    }
}


O(n)

class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null) return true; 
        if(root == null || subRoot == null) return false;
        if(sameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean sameTree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null) return false;
        if(root.val != subRoot.val) return false; 
        return sameTree(root.left,subRoot.left) && sameTree(root.right, subRoot.right);
    }
}