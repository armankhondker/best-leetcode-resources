Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left as possible.
 It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6


NAIVE SOLUTION 
USE recursion on the tree and iterte on everything

TC: O(N) to visit every node
SC: O(depth of tree) = O(logn) since we have a balanced tree

class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) +1; 
    }
}

BEST APPROACH - BINARY SEARCH 
BECAUSE IT IS A COMPELTE BINARY TREE, WE CAN BEAT O(N) TIME COMPLEXITY, need to use this property 

In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.

That means that complete tree has 2^k nodes in the kth level if the kth level is not the last one. The last 
level may be not filled completely, and hence in the last level the number of nodes could vary from 1 to 2^d ,
where d is a tree depth.

ALGORITHM:

1. compute depth of tree
2. return 1 if d == 0
3. the number of nodes in tree expect last level is 2^d -1. the number of nodes in last level could vary,
from 1 to 2^d. enumerate potential nodes from 0 to 2^d-1 and perform binary search by node index and check
how many nodes are in last level
4. Use another binary search to implement exists as well
5. return 2^d-1 + number of nodes in last level

// TC: O(d^2) = (log^2(N)) where d is diameter 
// SC: O(1)

class Solution {
  // Return tree depth in O(d) time.
  public int computeDepth(TreeNode node) {
    int d = 0;
    while (node.left != null) {
      node = node.left;
      ++d;
    }
    return d;
  }

  // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
  // Return True if last level node idx exists. 
  // Binary search with O(d) complexity.
  public boolean exists(int idx, int d, TreeNode node) {
    int left = 0, right = (int)Math.pow(2, d) - 1;
    int pivot;
    for(int i = 0; i < d; ++i) {
      pivot = left + (right - left) / 2;
      if (idx <= pivot) {
        node = node.left;
        right = pivot;
      }
      else {
        node = node.right;
        left = pivot + 1;
      }
    }
    return node != null;
  }

  //MAIN FUNCTION
  public int countNodes(TreeNode root) {
    // if the tree is empty
    if (root == null) return 0;

    int d = computeDepth(root);
    // if the tree contains 1 node
    if (d == 0) return 1;

    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Perform binary search to check how many nodes exist.
    int left = 1, right = (int)Math.pow(2, d) - 1; //left and right boundaries
    int pivot;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      if (exists(pivot, d, root)) left = pivot + 1;
      else right = pivot - 1;
    }

    // The tree contains 2**d - 1 nodes on the first (d - 1) levels
    // and left nodes on the last level.
    return (int)Math.pow(2, d) - 1 + left;
  }
}