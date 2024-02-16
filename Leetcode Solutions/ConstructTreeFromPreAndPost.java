// Given preorder and inorder traversal of a tree, construct the binary tree.

//TC: O(N)
//SC: O(N)

// The basic idea is here:
// Say we have 2 arrays, PRE and IN.
// Preorder traversing implies that PRE[0] is the root node.
// Then we can find this PRE[0] in IN, say it's IN[5].
// Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end 
//is on the right side.
// Recursively doing this on subarrays, we can build a tree out of it


// I hope this helps those folks who can't figure out how to get the index of the right child:

// Our aim is to find out the index of right child for current node in the preorder array
// We know the index of current node in the preorder array - preStart (or whatever your call it), it's the root of a subtree
// Remember pre order traversal always visit all the node on left branch before going to the right ( root -> left -> ... -> right), therefore, we can get the immediate right child index by skipping all the node on the left branches/subtrees of current node
// The inorder array has this information exactly. Remember when we found the root in "inorder" array we immediately know how many nodes are on the left subtree and how many are on the right subtree
// Therefore the immediate right child index is preStart + numsOnLeft + 1 (remember in preorder traversal array root is always ahead of children nodes but you don't know which one is the left child which one is the right, and this is why we need inorder array)
// numsOnLeft = root - inStart.

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
                                        //inStart, inEnd are our boundaries for the inorder array,
                                        //prestart is where we want to set our root on the recurisve call
    if (preStart > preorder.length - 1 || inStart > inEnd) { //check for invalid conditions
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = 0; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {.   //we have found the root in the inorder array
            inIndex = i;    
        }
    }
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder); //check everything to left
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder); //check comment on finding index of right child
    return root;
}
}

USE HASHMAP TO STORE THE INDEXES IN THE INORDER ARRAY, slightly better solution 

public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

    for(int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    return root;
}

public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
    if(preStart > preEnd || inStart > inEnd) return null;

    TreeNode root = new TreeNode(preorder[preStart]);
    int inRoot = inMap.get(root.val);
    int numsLeft = inRoot - inStart; //number of elements in right subtree

    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

    return root;
}



ITERATIVE SOLUTION 

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0) {
            return null;
        }
        
        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);
        
        // for all remaining values...
        for (int i = 1; i < preorder.length; i ++) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            
            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        
        return root;
    }
}