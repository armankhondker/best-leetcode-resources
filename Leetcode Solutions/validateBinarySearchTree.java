//Use in order order traversal and BST property
//O(n) time to go through all of the nodes
//O(n) space to store the result of the traversal
     
public boolean isValidBST(TreeNode root) {
  if(root == null) return true; 
  ArrayList<Integer> result = new ArrayList<Integer>();
  inOrderTraversal(root, result); 
  for(int i=0; i<result.size()-1; i++)
  {
    if(result.get(i) >= result.get(i+1)) return false; 
  }
  return true; 

}

public void inOrderTraversal(TreeNode root, ArrayList<Integer> result)
{
  if(root == null) return;
  inOrderTraversal(root.left, result);
  result.add(root.val);
  inOrderTraversal(root.right,result);
}


///ITERATIVE version 

public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null;
   while (root != null || !stack.isEmpty()) {
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;
      pre = root;
      root = root.right;
   }
   return true;
}




RECURSIVE SOLUTION, USING LIMITS, a binary tree node cannot go over its upper and lower limits 
TC: O(N) to go through all of the nodes
SC: O(N) for stack space in worst case of tree will all right pointers

class Solution {
  public boolean helper(TreeNode node, Integer lower, Integer upper) {
    if (node == null) return true;

    int val = node.val;
    if (lower != null && val <= lower) return false;
    if (upper != null && val >= upper) return false;

    if (! helper(node.right, val, upper)) return false;
    if (! helper(node.left, lower, val)) return false;
    return true;
  }

  public boolean isValidBST(TreeNode root) {
    return helper(root, null, null);
  }
}

SHORTER RECURSIVE SOLUTION 
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}