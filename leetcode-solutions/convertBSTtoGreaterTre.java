Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original 
BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13


//Recursive solution 

Time complexity : O(n)

A binary tree has no cycles by definition, so convertBST gets called on each node no more than once. 
Other than the recursive calls, convertBST does a constant amount of work, so a linear number of calls to convertBST
will run in linear time.

Space complexity : O(n)

Using the prior assertion that convertBST is called a linear number of times, 
we can also show that the entire algorithm has linear space complexity. Consider the worst case, 
a tree with only right (or only left) subtrees. The call stack will grow until the end of the longest 
path is reached, which in this case includes all nn nodes.


 public TreeNode convertBST(TreeNode root) {
    dfs(root, 0);
    return root;
}
public int dfs(TreeNode root, int val) {
    if(root == null) return val;
    int right = dfs(root.right, val);
    int left = dfs(root.left, root.val + right);
    root.val = root.val + right;
    return left;
}



//iterative solution 

class Solution {
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || node != null) {
            /* push all nodes up to (and including) this subtree's maximum on
             * the stack. */
            while (node != null) {
                stack.add(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;

            /* all nodes with values between the current and its parent lie in
             * the left subtree. */
            node = node.left;
        }

        return root;
    }
}