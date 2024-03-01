// Two elements of a binary search tree (BST) are swapped by mistake.

// Recover the tree without changing its structure.

// Example 1:

// Input: [1,3,null,null,2]

//    1
//   /
//  3
//   \
//    2

// Output: [3,1,null,null,2]

//    3
//   /
//  1
//   \
//    2


UNOPTIMAL, linear time and space solution 

USE inorder travesral of BST (which will lead to all elements in order) 
and

1. helper function for inorderTraversal
2. helper function to find twoSwapped indexeses 
3. helper function to recover to actual tree - traverse the tree again
until we reach those two nodes and SWAP THEIR VALUES

TC: O(N) to perform an inorder travesal on N nodes in the tree
SC: O(N) to keep an array of the inorder travseral of the nodes

class Solution {

  public void recoverTree(TreeNode root) {
    List<Integer> nums = new ArrayList();
    inorder(root, nums);
    int[] swapped = findTwoSwapped(nums);
    recover(root, 2, swapped[0], swapped[1]);
  }

  public void inorder(TreeNode root, List<Integer> nums) {
    if (root == null) return;
    inorder(root.left, nums);
    nums.add(root.val);
    inorder(root.right, nums);
  }

  public int[] findTwoSwapped(List<Integer> nums) {
    int n = nums.size();
    int x = -1, y = -1;
    for(int i = 0; i < n - 1; ++i) {
      if (nums.get(i + 1) < nums.get(i)) {
        y = nums.get(i + 1);
        // first swap occurence
        if (x == -1) x = nums.get(i);
        // second swap occurence
        else break;
      }
    }
    return new int[]{x, y};
  }

  public void recover(TreeNode r, int count, int x, int y) {
    if (r != null) {
      if (r.val == x || r.val == y) {
        r.val = r.val == x ? y : x;
        if (--count == 0) return;
      }
      recover(r.left, count, x, y);
      recover(r.right, count, x, y);
    }
  }
}


WE can improve on this by identifying the swapped nodes, WHILE DOING
the in order traversal, will save us space in average case! (combine step 1 and 2)

//TC: O(N) in worst case where one of swapped nodes is right leaves
//SC: O(H) because we only have a stack which will be the height of tree, 
//we will intialyl store all the left elements
// in worst case of unbalanced tree this could be O(N) space

class Solution {
  public void swap(TreeNode a, TreeNode b) {
    int tmp = a.val;
    a.val = b.val;
    b.val = tmp;
  }

  public void recoverTree(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque();
    TreeNode x = null, y = null, pred = null; //predecessor value

    while (!stack.isEmpty() || root != null) {//we are performing inorder travseral with stack
      while (root != null) {
        stack.add(root);
        root = root.left;  //push all the left nodes onto stack first 
      }
      root = stack.removeLast();  //evaluate current node
      if (pred != null && root.val < pred.val) { //if root is less than previous node
        y = root;                               //we have found the swap nodes
        if (x == null) x = pred;
        else break;
      }
      pred = root;
      root = root.right; //finally push the right elements onto stack 
    }

    swap(x, y);
  }
}



BEST SOLUTION, MORRIS TRAVERSAL (tree travesral techinuqe to traverse 
a tree with O(1) space using predecssor pointers)

TC: O(N)
SC: O(1)