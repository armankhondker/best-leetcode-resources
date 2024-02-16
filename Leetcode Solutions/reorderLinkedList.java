// Given a singly linked list L: L0→L1→…→Ln-1→Ln,
// reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

// You may not modify the values in the list's nodes, only nodes itself may be changed.

//Example:
// Given 1->2->3->4->5->6->7->8->null, reorder it to 1->8->2->7->3->6->4->5->null

//go to middle of linked list and seperate the halfs

// 1->2->3->4->null
// 5->6->7->8->null

//Now just reverse second half of list 

// 1->2->3->4->null
// 8->7->6->5->null

//merge lists togehter 

//TC: O(N) to go throguh all nodes
//SC: O(1) dont need to store anything 

class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;

        //after iterations the will point to.........
        ListNode l1 = head; //head of first half, DONT WANT TO LOSE REFERENCE
        ListNode slow = head; // head of second half
        ListNode fast = head;  //tail of second half
        ListNode prev = null; //tail of first half!
        
        //1. Cut the list in half 
        while(fast!=null && fast.next !=null)
        {
            prev = slow; 
            slow = slow.next; 
            fast=fast.next.next; 
        } //at end of iteration slow will will be at midpoint, aka head of second list 
        prev.next =null;
        
        //2. now reverse the second list
        
        ListNode l2 = reverse(slow); 
        
        //3. now merge the linked lists together 
        merge(l1, l2);
        
    }
    
    
    public ListNode reverse(ListNode head)
    {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null)
        {
            ListNode next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next; 
        }
        return prev; //points to begginning of list 
    }

    public void merge(ListNode l1, ListNode l2) {
      while (l1 != null) {
        ListNode n1 = l1.next, n2 = l2.next;
        l1.next = l2;
        
        if (n1 == null)
          break;
            
        l2.next = n1;
        l1 = n1;
        l2 = n2;
      }
    }

}