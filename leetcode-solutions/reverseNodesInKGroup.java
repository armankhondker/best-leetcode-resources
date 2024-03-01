Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
           // Save the previous reversed pointer in prev and in wach iteration use prev.next to set the previous ptr to the current reversed.
        ListNode tempNode = new ListNode(0);
        tempNode.next = head;
        ListNode tempHead = head;
        ListNode prev = tempNode;
        while(tempHead!=null){
            // Starting of each reversed list, it will become the last after reversing
            ListNode klast = tempHead;
            int num = k;
            // Jump k 
            while(num>0 && tempHead!=null){
                tempHead = tempHead.next;
                num--;
            }
            // If cannot reverse
            if(num!=0) {
                prev.next = klast;
                break;
            }
            // start of each reversed group
            ListNode kstart = reverse(klast,k);
            
            // Use previous's next to point to curr reversed
            prev.next = kstart;
            // Set prev to current rev end.
            prev = klast; 
        }
        return tempNode.next;

    }
    
    
       // Standard reverse code
    public ListNode reverse(ListNode head, int k){
        ListNode prev = null;
        while(head!=null && k>0){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            k--;
        }
        return prev;
    }
}