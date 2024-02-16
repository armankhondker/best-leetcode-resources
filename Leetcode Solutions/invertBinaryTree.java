Invert a binary tree




Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

//BEST METHOD, to avoid stack overflow , BFS

// Since each node in the tree is visited / added to the queue only once, 
// the time complexity is O(n), where nn is the number of nodes in the tree.

// Space complexity is O(n), since in the worst case, the queue will contain all nodes 
// in one level of the binary tree. For a full binary tree, the leaf level has O(n) leaves.

public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;
        if (current.left != null) queue.add(current.left);
        if (current.right != null) queue.add(current.right);
    }
    return root;
}


//WORSE SOLUTION, using dfs and will potentially cause stack overflow

Since each node in the tree is visited only once, the time complexity is O(n), 
where n is the number of nodes in the tree. We cannot do better than that, since at the very 
least we have to visit each node to invert it.

Because of recursion, O(h) function calls will be placed on the stack in the worst case,
 where h is the height of the tree. Space complexity is O(n).


class Solution {
    public TreeNode invertTree(TreeNode root) {
          if (root == null) {
        return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
    }
}