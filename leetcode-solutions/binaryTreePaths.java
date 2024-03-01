Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3



USE DFS
//O(n) to do DFS on all the nodes in the tree
//O(n) space complexity for stack space 


class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>(); 
        if(root == null) return res; 
        dfs(root,"",res);
        return res; 
    }
    
    public void dfs(TreeNode root, String path, List<String> res)
    {
        path+=root.val; 
        if(root.left==null && root.right==null)
        {
            res.add(path);
            return; 
        }
        if(root.left!=null)
        {
            dfs(root.left, path+"->", res);
        }
          if(root.right!=null)
        {
            dfs(root.right, path+"->", res);
        }
    }
}