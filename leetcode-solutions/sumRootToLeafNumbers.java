You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.



SOLUTION, keep track of total and add

TC: O(N)
SC: O(H) in worst case O(N) for unbalanced tree

class Solution {
    int total =0;
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return total; 
    }
    public void helper(TreeNode root, int curVal){
        if(root !=null){
           curVal = curVal*10 + root.val; 
           if(root.left == null && root.right == null){
               total+=curVal; 
           }
        helper(root.left, curVal);
        helper(root.right, curVal);
        }
    }
}

 public int sumNumbers(TreeNode root) {
    return sumNodes(root, 0);
  }

  private int sumNodes(TreeNode root, int currentSum) {
    if (root == null) return 0;
    currentSum = currentSum * 10 + root.val;
    if (root.left == null && root.right == null) return currentSum;
    int leftSum = sumNodes(root.left, currentSum);
    int rightSum = sumNodes(root.right, currentSum);
    return leftSum + rightSum;
  }

  ITERATIVE SOLUTION

  public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> nodePath = new Stack<>();
        nodeStack.push(root);
        nodePath.push(""+root.val);
        int runningSum = 0;
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            String currentPath = nodePath.pop();
            if(node.right != null){
                nodeStack.push(node.right);
                nodePath.push(currentPath + (""+node.right.val));
            }
            if(node.left != null){
                nodeStack.push(node.left);
                nodePath.push(currentPath+ (""+ node.left.val) );
            }
            if( node.left == null && node.right == null){
                runningSum = runningSum + Integer.valueOf(currentPath);
            }
          
        }
        return runningSum;
        
    }

BEST SOLUTION SAVE SPACE, MORRIS TRAVERSAL


