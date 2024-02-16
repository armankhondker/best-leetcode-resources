// You are given a doubly linked list which in addition to the next and previous pointers, 
// it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

// Flatten the list so that all the nodes appear in a single-level, doubly linked list. 
// You are given the head of the first level of the list.

// Example 1:

// Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
// Output: [1,2,3,7,8,11,12,9,10,4,5,6]


NOTE, THIS IS SAME AS PREORDER TRAVERSAL OF BINARY TREE,

JUST LOOK AT THE NODES WHEN THEY ARE FLIPPED 90 degrees!! PIC ON LEETCODE


ITERATIVE DFS SOLUTION, with a stack!!

TC: O(N) we will visit each node exactly one time (we are essentially searching the tree via DFS)
SC: O(N) in worst case of unbalanced tree the stack could grow to N/2 and be constraiend by N

class Solution {
    public Node flatten(Node head) {
        if(head == null) return null;
        Stack<Node> stack = new Stack<>();
        Node curr = head; 
        
        while(curr!=null){
            if(curr.child!=null){
                if(curr.next!=null){ //need to save the next nodes on the stack 
                    stack.push(curr.next);
                }
                curr.next = curr.child; //set next to child value as shown in pic
                
                curr.next.prev = curr; //set previous pointer of child to currr
                
                curr.child = null; //make sure to set child pointers to null after 
            } else if(curr.next == null && !stack.isEmpty()) { //we have reached end of this level 
                //we need to link with the element on the stack!
                curr.next = stack.pop();
                curr.next.prev = curr;   //make sure to link prev pointer 
            }
            
            
            curr = curr.next; //move our curr forward every iteration 
        }
        return head; 
    }
}



