// Given a non-empty binary tree, find the maximum path sum.

// For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the 
// parent-child connections. The path must contain at least one node and does not need to go through the root.


// Example 1:

// Input: [1,2,3]

//        1
//       / \
//      2   3

// Output: 6
// Example 2:

// Input: [-10,9,20,null,null,15,7]

//    -10
//    / \
//   9  20
//     /  \
//    15   7

// Output: 42



INTUTION, maximums will get updated from the bottom, then we can find max gain from each node

//TC: O(N) to recurse on the entire tree
//SC: O(H) where h is height of tree, because in worst case will keep recursion stack that is height of tree 
class Solution {

    int maxPathSum;
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        pathSum(root);
        return maxPathSum; 
    }
    
    //helper function which comptues the MAXIMUM contribution of a node  (node plus one/zero of its subtrees can add). CAN only use one subtree because it isnt possible to BACKTRACK and add both left/right in valid path 
    public int pathSum(TreeNode node)
    {
        if(node==null) return 0;
        int left = Math.max(0, pathSum(node.left));
        int right = Math.max(0, pathSum(node.right));
        maxPathSum=Math.max(maxPathSum, node.val + left + right); //maximum contribution of node is val+L+R
        return Math.max(left,right) + node.val;     // for recursion :
                                                    // return the max gain if continue the same path
                                                    //THIS IS USED TO PRUNE THE BRANCHES 
    }
}


Just want to add a explanation about the last two lines based on my comprehension.

maxValue = Math.max(maxValue, left + right + node.val);
return Math.max(left, right) + node.val;

maxValue is the value which recording whether this current root is the final root, so we use left + right + node.val.
But to the upper layer(after return statement), we cannot choose both left and right brunches, 
so we need to select the larger one, so we use max(left, right) + node.val to prune the lower brunch.
In the end, very elegant solution, thank you for your sharing!