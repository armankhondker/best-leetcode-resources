// Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

// Calling next() will return the next smallest number in the BST.


CORE IDEA, iterators are extremely useful, because they allow us to abstract out the traversal of an Object
IN THIS CASE, a binary tree, and just use the iterator, to get next elements, etc. 

//UNOPTIMAL SOLUTION

NOTE, inorder traveseral of BST always returns the elements in order!!!!

1. Use an array to simualte an iterator! After we put all the nodes in an array
we can simply use an index to return the next and has next functionality. 
Note, this will require extra space for the array! To popualte array use an,
IN ORDER TRAVERSAL of the BST 

2. next will simply return the get of array value at the index;

//TC: O(N) time for construct, O(1) time for next and hasNext!
//SC: O(N) to store the array, where N is number of nodes in trees
class BSTIterator {
     
    ArrayList<Integer> nodesInSortedOrder; 
    int index;
    
    public BSTIterator(TreeNode root) {
        nodesInSortedOrder = new ArrayList<Integer>();
        index = -1;
        inOrder(root); 
    }
    
    public void inOrder(TreeNode root){
        if(root == null) return; 
        inOrder(root.left);
        nodesInSortedOrder.add(root.val);
        inOrder(root.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        
        return nodesInSortedOrder.get(++index);
        
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index+1 < nodesInSortedOrder.size();
    }
}



OPTMIAL SOLUTION - "CONTROLLED RECURSION"

WE willl basically control recurssion so that 

USE A STACK and HELPER FUNCTION, to only hold all of the left most elements at once,
when we call next, check if it has any right children (if so call helper again), else
simply return the value on top of stack and pop it. This allows us to not have to,
take any time to build up the stack in the contructor!!

1. USE a stack instead of array
2. Heleper function, leftMostInorder, which will add all left elements to stack
3. NextFunction will return top element of stack, and call helper function if necessary!
Note, this will take O(1) amortized time, because in average case is O(1) operation,
even though in some cases will take O(N) time

When analyzing amortized time complexities, I find it easiest to reason that each node gets pushed and popped exactly once in next() when iterating over all N nodes.
That comes out to 2N * O(1) over N calls to next(), making it O(1) on average, or O(1) amortized.

Basically, when you right out the cost, of doing a bunch of next calls, it goes to a geometric progression
which will sum to 2n*2 -2

4. HasNext is clearly going to be O(1) operation


//TC: O(1) for constructor, O(1) for next because of amortized time, O(1) for
//has next function;
//SC: O(H) where h is height of tree, because in worst case, the stack will only,
//hold up to H elements in the tree 

class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        
        // Stack for the recursion simulation
        stack = new Stack<TreeNode>();
        
        // Remember that the algorithm starts with a call to the helper function
        // with the root node as the input
        _leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {
      
        // For a given node, add all the elements in the leftmost branch of the tree
        // under it to the stack.
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        // Node at the top of the stack is the next smallest element
        TreeNode topmostNode = this.stack.pop();

        // Need to maintain the invariant. If the node has a right child, call the 
        // helper function for the right child
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}

