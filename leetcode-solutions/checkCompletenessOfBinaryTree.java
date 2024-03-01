Given a binary tree, determine if it is a complete binary tree.

//USE BOOLEAN FLAG, to see when you reach first null node
//DO LEVEL ORDER TRAVESRAL, of the tree,
//SHOULDNT SEE ANY MORE, null nodes after seeing first null 

//TC : O(N) 
//SC: O(N)
class Solution {
    public boolean isCompleteTree(TreeNode root) {
         boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur == null) end = true;  //we haev seen first null, so if we see another node after this, return false
            else{
                if(end) return false;
                queue.add(cur.left); //level order traversal 
                queue.add(cur.right);
            }
        }
        return true;
    }
}