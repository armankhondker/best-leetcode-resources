Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7


PREORDER - root, left, right ----> therefore we know root node is first node in preorder 
INORDER - left, root, right -----> everything on left of node in this array, is in left subtree,
									everything on right of node in this array, is in right subtree



TC:
SC:



 public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
						//where we start in preOrder array, where we start in inOrder array, where we end in inOrder array
    if (preStart > preorder.length - 1 || inStart > inEnd) { //base cases
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);

    int inIndex = 0; // Index of current root in inorder array
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {   //we have found the root in the inorder array
            inIndex = i;    //set the var
        }
    }
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    				//this is index of left child of root
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder); 
    //(inIndex - inStart) is because we want to skip the LEFT SUBTREES, kind of complicated
    //this first value is index of right child of root
    return root;
}


1. Our aim is to find out the index of right child for current node in the preorder array
2. We know the index of current node in the preorder array - preStart (or whatever your call it), it's the root of a subtrees 									
3. Remember pre order traversal always visit all the node on left branch before going to the right 
( root -> left -> ... -> right), therefore, we can get the immediate right child index by skipping 
all the node on the left branches/subtrees of current node
4. The inorder array has this information exactly. Remember when we found the root in "inorder" array we 
immediately know how many nodes are on the left subtree and how many are on the right subtree
5. Therefore the immediate right child index is preStart + numsOnLeft + 1 (remember in preorder traversal
 array root is always ahead of children nodes but you don't know which one is the left child which  											
 one is the right, and this is why we need inorder array) numsOnLeft = root - inStart.
