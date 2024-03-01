Given the root node of a binary search tree, return the sum of values of all nodes with 
value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23


USE BFS, traverse tree, keep track of all nodes which are between L and R 


//TC: O(n) to go through every node in the tree
//SC: O(n) to store all nodes in queue 
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if( curr.val <= R && curr.val >=L){
                sum+=curr.val;
            }
            if(curr.right!=null){
                q.add(curr.right);
            }
            if(curr.left!=null){
                q.add(curr.left);
            }
        }
        return sum; 
        
    }
}

//RECURSIVE MANNER

    public int rangeSumBST(TreeNode root, int L, int R) {    
    if( root == null) return 0;
    int n  = 0;
    if(root.val >= L && root.val <= R) n = root.val;
    n += rangeSumBST(root.left, L, R);
    n += rangeSumBST(root.right, L, R);
    return  n;
    }

