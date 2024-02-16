Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3


//O(n) because we traverse all of the nodes in the tree 
//O(n) because the recursive calls will be the height of thee true, and in the worst case, tree is unbalanceed list 
   class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true; 
        
        return isSymetric(root.left, root.right);
    }
    
    public boolean isSymetric(TreeNode left, TreeNode right)
    {
        if(left == null || right == null)
        {
            return left == right; 
        }
        
        if(left.val!=right.val) //different values so cant be symetric 
        {
            return false; 
        }
        
        return isSymetric(left.left, right.right) && isSymetric(left.right, right.left); 
    }
}

//iteerative

public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        Queue<TreeNode> q = new LinkedList();
       
        q.add(root.left);
        q.add(root.right);
        while(!q.isEmpty()){
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if(left == null && right == null)
                continue;
            if(left == null || right == null ||left.val != right.val )
                return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
            
        }
        return true;
            
    }