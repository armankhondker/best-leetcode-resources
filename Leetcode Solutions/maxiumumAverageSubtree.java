// Given the root of a binary tree, find the maximum average value of any subtree of that tree.

// Input: [5,6,1]
// Output: 6.00000
// Explanation: 
// For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
// For the node with value = 6 we have an average of 6 / 1 = 6.
// For the node with value = 1 we have an average of 1 / 1 = 1.
// So the answer is 6 which is the maximum.




//Use Post Order Traversal to populate the end leafs first
// 


//TC:
//SC: 

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
    double res;
    public double maximumAverageSubtree(TreeNode root) {
        res = 0;
        sumAndNum(root);
        return res;
    }
    
    //{num,sum}
    public int[] sumAndNum(TreeNode node){
        if(node==null) return new int[]{0,0};
        int sum = node.val;
        int num = 1;   //number of nodes 
        int[] left = sumAndNum(node.left);  //sum of left subtrees
        int[] right = sumAndNum(node.right);
        num+=left[0]+right[0];
        sum+=left[1]+right[1];
        res = Math.max(res, (double)sum/(double)num);  //update max average sum 
        return new int[]{num,sum};  //return the number of elements and the sum
    }
}