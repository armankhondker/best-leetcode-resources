Given a binary tree. THIS IS THE DIFFERENCE

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }
// Populate each next pointer to point to its next right node. 
// If there is no next right node, the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.



LEVEL ORDER BFS, same as popNextrightI problem

TC: O(N) to process each node once
SC: O(N) This is a binary tree which means the last level could contain N/2 nodes. 
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
                } else{
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

SPACE EFFICIENT CONSTANT SPACE Solution

BASICALLY NEED TO MAKE USE OF A PREVIOUS NODE!!

WE CAN GET CONSTANT SPACE BECAUSE, the next pointers allow us to perform
level order BFS by use of next pointers (which act as a sort of queue)

//populate pointers on next level by the two following cases
//1)
//2) 

TC: O(N)
SC: O(1)

public class Solution {

    public void connect(TreeLinkNode root) {
        TreeLinkNode head=root;//The LEFTMOST node in the lower level
        TreeLinkNode prev=null;//The LEADING NODE node in the lower level, that iterates through it
        TreeLinkNode curr=null;//The current node in the upper level
        while (head!=null){
            curr=head; //reset the curr to be the leftMost node on the lower level, and iterate again
            prev=null; //reset these to null
            head=null; //reset these to null
            while (curr!=null){
                if (curr.left!=null){
                    if (prev!=null)
                        prev.next=curr.left;
                    else 
                        head=curr.left;
                    prev=curr.left;
                }
                if (curr.right!=null){
                    if (prev!=null)
                        prev.next=curr.right;
                    else 
                        head=curr.right;
                    prev=curr.right;
                }
                curr=curr.next;
            }
        }
    }
}