// Given a binary tree, return the preorder traversal of its nodes' values.

// Example:

// Input: [1,null,2,3]
//    1
//     \
//      2
//     /
//    3

// Output: [1,2,3]
// Follow up: Recursive solution is trivial, could you do it iteratively?


Recursive solution

TC: O(N)
SC: O(N)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root,res);
        return res;
    }
    public void preorder(TreeNode root, List<Integer> res){
        if(root == null) return; 
        
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res); 
    }
}

Iterative solution

TC: O(N)
SC: O(N)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>(); 
        if(root == null) return res; 
        
        Stack<TreeNode> s = new Stack<>();
        
        s.push(root);
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            res.add(curr.val);
            
            if(curr.right!=null){
                s.push(curr.right);
            }
            if(curr.left!=null){
                s.push(curr.left); 
            } 
        }
        return res; 
    }
 
}