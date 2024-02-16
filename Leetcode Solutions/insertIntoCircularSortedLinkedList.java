// Given a node from a Circular Linked List which is sorted in ascending order, write a function to
//  insert a value insertVal into the list such that it remains a sorted circular list. 
//  The given node can be a reference to any single node in the list, and may not be necessarily 
//  the smallest value in the circular list.

// If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
// After the insertion, the circular list should remain sorted.

// If the list is empty (i.e., given node is null), you should create a new single circular list and 
// return the reference to that single node. Otherwise, you should return the original given node.


USE TWO POINTERS, prev and curr!


3 CASES: 
1. the insert value is between two values in linked list, so search for that
2. the insert val is greater/less than the maximum in linked list, THEREFORE needs to be inserted at tail 
3. SPECIAL CASE, if entire linked list is the same value, then we can insert the node into any positon in the list 


//TC: O(N)
//SC: O(1) 
class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
      Node newNode = new Node(insertVal, null);
      newNode.next = newNode;
      return newNode;
    }

    Node prev = head;
    Node curr = head.next;
    boolean toInsert = false;

//convert this to standard while loop in interview
    do {
      if (prev.val <= insertVal && insertVal <= curr.val) {
        // Case 1).
        toInsert = true;
      } else if (prev.val > curr.val) {
        // Case 2).
        if (insertVal >= prev.val || insertVal <= curr.val)
          toInsert = true;
      }

      if (toInsert) {
        prev.next = new Node(insertVal, curr);
        return head;
      }

      prev = curr;
      curr = curr.next;
    } while (prev != head);

    // Case 3).
    prev.next = new Node(insertVal, curr);
    return head;
    }
}
