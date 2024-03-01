// Given two binary search trees, return True if and only if there is a node in the first tree 
// and a node in the second tree whose values sum up to a given integer target.



//Intutiton, use a set to store the values of one tree and use inorder traversal to traverse
//Then, use a second function to see if set contains (target - root.val) like normal two sum 



//TC: O(M+N) where those are number of nodes in each
//SC: O(M+N) for the recursion stack 

//NOTE, searching the BSTS is not O(logn) because in worst case it wont be a balanced tree
//aka will just be a tree will parents in children in a line which will be O(H
)
class Solution {
        
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
            return false;
        Set<Integer> set = new HashSet<>();
        visit(root1, set);
        return check(root2, set, target);
    }
    
    private boolean check(TreeNode root, Set<Integer> set, int target) {
        if (root == null) 
            return false;
        if (set.contains(target - root.val))
            return true;
        return check(root.left, set, target) || check(root.right, set, target);
    }
    
    private void visit(TreeNode root, Set<Integer> set) {
        if (root == null)
            return;
        set.add(root.val);
        visit(root.left, set);
        visit(root.right, set);
    }
        
        
}