You are given a perfect binary tree where all leaves are on the same level, and every parent has
 two children. THIS IS THE DIFFERENCE

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }

// Populate each next pointer to point to its next right node. If there is no next right node, 
// the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.


LEVEL ORDER BFS SOLUTION

TC: O(N) to visit every node once and processs it 
SC: O(N) This is a perfect binary tree which means the last level contains N/2 nodes. 
The space complexity for breadth first traversal is the maximum space occupied and the space 
occupied by the queue is dependent upon the maximum number of nodes in particular level. So, 
in this case, the space complexity would be O(N).

class Solution {
    public Node connect(Node root) {
        if(root == null) return null; 
        
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i=0; i<size; i++){
                Node curr = q.poll();
                if(i == size-1){
                    curr.next = null;
                } else {
                    curr.next = q.peek();
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right); 
                }
                 
            }  
        }
        return root; 
    }
}

SPACE EFFICIENT SOLUTION FOR FOLLOW up 
Can do this in constant space by going level by level and populating next pointers,
We can populate the next pointers of next deeper level by looking at 
1) the left child next will be, the curr.right
2) the right child next will be, the curr.next.left 

TC: O(N) we go through all the nodes in the tree
SC: O(1) no extra space required

public Node connect(Node root) {
        if (root == null) return root;
        
        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;
        
        // Once we reach the final level, we are done
        while (leftmost.left != null) { //we can use this logic because we have complete binary tree
            
            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the 
            // corresponding links for the next level
            Node head = leftmost;
            
            while (head != null) {
                
                // CONNECTION 1
                head.left.next = head.right;
                
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            
            // Move onto the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
