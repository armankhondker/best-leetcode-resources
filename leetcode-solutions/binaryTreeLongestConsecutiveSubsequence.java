// Given a binary tree, find the length of the longest consecutive sequence path.

// The path refers to any sequence of nodes from some starting node to any node in the tree along the 
// parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

// Example 1:

// Input:

//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5

// Output: 3

// Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
// Example 2:

// Input:

//    2
//     \
//      3
//     / 
//    2    
//   / 
//  1

// Output: 2 

// Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.



LOGIC, use DFS on tree updating a max, and we will increment our count, if next node value
is equal to target+1;


//TC: O(N) to go through all nodes
//SC: O(N) to store the nodes in stack 
public class Solution {
    private int max = 0;  //can change this by passing in an arr of size 1, and updating it in helper, 
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0, root.val);
        return max;
    }
    
    public void helper(TreeNode root, int cur, int target){
        if(root == null) return;
        if(root.val == target) cur++;
        else cur = 1;
        max = Math.max(cur, max);
        helper(root.left, cur, root.val + 1);
        helper(root.right, cur, root.val + 1);
    }
}