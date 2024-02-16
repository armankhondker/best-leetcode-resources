
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6

The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6


UNOPTIMAL SOLUTION,
Use a STACK to do a DFS on the nodes
1. on each node, push right first, then left, then check top of stack and set its left equal to top 
element in stack
2. set left node to null

//TC: O(n) for the DFS traversal, PROCESS EACH NODE exactly once
//SC: O(n) stack size depending on nodes

//Basically, at a given node we want to
//1. push its right and left onto the stack for later processing
//2. if it has a left node, that will be its next (curr.right) in the new flattened list
//3. set the left to null, so we can flatten
//4. continue iteration on next node 
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root); 
        
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            
            if(curr.right!=null){     //NEED TO PUSH RIGHT FIRST!
                s.push(curr.right);
            }
            if(curr.left!=null){      //THEN LEFT, so will be on top of stack 
                s.push(curr.left);
            }
            if(!s.isEmpty()){ //dont want to peek on empty stack, EX tree with only root node, nothing will be in stack
                curr.right=s.peek();
            }
            curr.left=null; //SET THE LEFT TO NULL 
        }
        
        return; 
    
    }
}



OPTIMAL SOLUTION - "MORRIS TRAVERSAL"

INTUTION is to find a way to do this in constant space!!!
USE POINTERS

CALL THIS, REVERSE PREORDER TRAVERSAL!!! basically just do a reverse preorder,!!!

//TC: O(N)
//SC: O(1)


private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}


