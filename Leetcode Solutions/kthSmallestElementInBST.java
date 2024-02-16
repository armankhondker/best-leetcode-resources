// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

// Note:
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3


//can use in order traversal, and then return kth element 
//need to use nums array to store, KTH position we are on and the value of that node! will populate that array
//then return the second value of it 

TC: O(N) because it needs to visit every node to go through the in order traversal
SC: O(N) stack space will hold all nodes in worst case of linkedlist

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int [] nums = new int[2]; //to store where we are in tree and the value of that node!!!
        inorder(root, nums, k);
        return nums[1];
    }
    
    public void inorder(TreeNode root,int [] nums,int k)
    {
        if(root == null) return; 
        inorder(root.left,nums,k);
        if(++nums[0] == k){   //we jave found kth smallest element
            nums[1] = root.val;
            return;
        }
        inorder(root.right,nums,k); 
    }
}


//iterative DFS

TC: O(N)
SC: O(N)

public int kthSmallest(TreeNode root, int k) {
      Stack<TreeNode> st = new Stack<>();
      
      while (root != null) {
          st.push(root);
          root = root.left;
      }
          
      while (k != 0) {
          TreeNode n = st.pop();
          k--;
          if (k == 0) return n.val;
          TreeNode right = n.right;
          while (right != null) {
              st.push(right);
              right = right.left;
          }
      }
      
      return -1; // never hit if k is valid
}