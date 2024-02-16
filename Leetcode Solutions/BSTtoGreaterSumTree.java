//Given the root of a binary search tree with distinct values, modify it so that every node has a new value 
//equal to the sum of the values of the original tree that are greater than or equal to node.val.


//The sum of values in the right sub-tree needs to be added to the current node value.

//Solution

//1. Traverse the tree in R-C-L order (reverse in-order), tracking the sum of values.
//2. Add the current sum to the value of the current node.

//We need to do the work from biggest to smallest, right to left.
//pre will record the previous value the we get, which the total sum of bigger values.
//For each node, we update root.val with root.val + pre.


//TC: O(N) to 
//SC: O(N) for recursive stack of size n

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
	   public TreeNode bstToGst(TreeNode root) {
        reversedInorder(root, 0);
        return root;
    }
    private int reversedInorder(TreeNode node, int sum) {
        if (node == null) { return sum; } // base case.
        node.val += reversedInorder(node.right, sum); // recurse to right subtree.
        return reversedInorder(node.left, node.val); // recurse to left subtree.
    }
}





//ITERATIVE
// Iterative version: use stack to pop out the nodes in reversed in order sequence.

// Initially, use cur to point to the root,

// push into Stack the right-most path of current subtree;
// pop out a node, update sum and the node value;
// point cur to the node's left child, if any;
// Repeat the above till the stack is empty and cur has no left child.

// Time & space: O(n)

 public TreeNode bstToGst(TreeNode root) {
        Deque<TreeNode> stk = new ArrayDeque<>();
        TreeNode cur = root;
        int sum = 0;
        while (cur != null || !stk.isEmpty()) {
            while (cur != null) { // save right-most path of the current subtree
                stk.push(cur);
                cur = cur.right;
            }
            cur = stk.pop(); // pop out by reversed in-order.
            sum += cur.val; // update sum.
            cur.val = sum; // update node value.
            cur = cur.left; // move to left branch.
        }    
        return root;
    }