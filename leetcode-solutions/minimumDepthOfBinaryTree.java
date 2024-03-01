
JUST DO SIMPLE BFS ON TREE!!!

//TC: O(N)
//SC: O(N)
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0; 
        
        int level = 1; 
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size(); //need to iterate level by level 
            for(int i=0; i<size; i++) {
            TreeNode curr = q.poll(); //current node we are processing
            if(curr.left==null & curr.right == null) return level; 
            
            if(curr.left!=null){
                q.add(curr.left);
            }
            if(curr.right!=null){
                q.add(curr.right); 
            }
            }
            level++; 
 
        }
        
        return -1; 
    }
}