Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.


Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]

Need to find LCA of deepest node 


RECURSIVE DFS SOLUTION

//TC: O(N) to visit every node  
//SC: O(H) for stack space 

class Solution {
    
    int deepestLevel = 0;
    TreeNode res = null;
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    
    private int dfs(TreeNode root, int level) {
        if (root == null) return level;
        
        int leftLevel = dfs(root.left, level + 1);
        int rightLevel = dfs(root.right, level + 1);
        
        int curLevel = Math.max(leftLevel, rightLevel);
        deepestLevel = Math.max(deepestLevel, curLevel);
        if (leftLevel == deepestLevel && rightLevel == deepestLevel)
            res = root;
        return curLevel;
    }
}


BETTER BFS SOLUTION: 

1. use bfs to find the leftmost node at deepest level, and find rightmost node at deepest level
2. find LCA between these two nodes, return!!


//TC: O(N) to perform BFS on tree, to populate parents hashmap, and to itereate to set leftMost and rightMost
//SC: O(N) to have the queue of size N and also hashet and the map of parents
class Solution {
  
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;
        
        TreeNode leftMost = null;
        TreeNode rightMost = null;
        
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int i=0; i<size; i++){
                
                TreeNode curr = q.poll();
                if(i==0){
                    leftMost = curr; 
                }
                if(i==size-1){
                    rightMost = curr;
                }
            
             if(curr.left!=null){
                parents.put(curr.left, curr);
                q.add(curr.left);
            }
            if(curr.right!=null){
                parents.put(curr.right,curr);
                q.add(curr.right);
            }
         }
            
        }
        
        HashSet<TreeNode> ancestors = new HashSet<>();
        while(leftMost!=null){
            ancestors.add(leftMost);
            leftMost = parents.get(leftMost);
        }
        while(!ancestors.contains(rightMost)){
            rightMost= parents.get(rightMost);
        }
        
        return rightMost; 
        
        
    }
    

}

