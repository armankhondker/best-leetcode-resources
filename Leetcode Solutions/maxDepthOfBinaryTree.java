// Given a binary tree, find its maximum depth.
// The maximum depth is the number of nodes along the longest path from the root node down to the 
//farthest leaf node.



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//TC: O(N) we visit every node at least once
//SC: in worst case where we have unbalanced tree (for example every node just has left children) will be O(N)
// however if we have balance tree it will be O(logn)
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){return 0;}
        else
        {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1; 
        }
    }
}

//ITERATIVE
//TC: O(N) we visit every node at least once
//SC: in worst case where we have unbalanced tree (for example every node just has left children) will be O(N)
// however if we have balance tree it will be O(logn)


public int maxDepth(TreeNode root) {
    if(root == null) {
        return 0;
    }
    
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> value = new Stack<>();
    stack.push(root);
    value.push(1);
    int max = 0;
    while(!stack.isEmpty()) {
        TreeNode node = stack.pop();
        int temp = value.pop();
        max = Math.max(temp, max);
        if(node.left != null) {
            stack.push(node.left);
            value.push(temp+1);
        }
        if(node.right != null) {
            stack.push(node.right);
            value.push(temp+1);
        }
    }
    return max;
}