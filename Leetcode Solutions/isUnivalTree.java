 A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.



A tree is univalued if the root and its children have same values, recurisvely check on this


TC: O(N) to visit every node
SC: O(H) where h is height of tree, in worst case will be O(N) when we have a tree in a line

class Solution {
    public boolean isUnivalTree(TreeNode root) {
          if(root == null) return true;
        if(root.left != null && root.left.val != root.val) return false;
        if(root.right != null && root.right.val != root.val) return false;
        return isUnivalTree(root.left) && isUnivalTree(root.right); //check both left and right
    }
}