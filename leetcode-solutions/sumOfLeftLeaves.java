Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

TC: O(N) for bfs on every node
SC: O(N) for queue to store up to all the nodes
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0; 
        Queue<TreeNode> q = new LinkedList<>(); 
        q.add(root);
        int ans = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode curr = q.poll();
                if(curr.left != null && curr.left.left == null && curr.left.right == null){
                    ans+= curr.left.val;
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right); 
                }
            }
        }
        return ans; 
    }
}