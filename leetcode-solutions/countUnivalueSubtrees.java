Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4



Go through the etnire tree, in a bottom up fashion, checking if the tree is unival 

TC: O(N) will visit every node exactly once
SC: O(N) for the recursive stack that will grow to at most N

public class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1]; //pass by reference in javas
        helper(root, count);
        return count[0];
    }
    
    private boolean helper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper(node.left, count); //check if left subtree is unival
        boolean right = helper(node.right, count); //check if right subtree is unival 
        if (left && right) { //if left tree and right tree are unival, then check the root val with left and right
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}