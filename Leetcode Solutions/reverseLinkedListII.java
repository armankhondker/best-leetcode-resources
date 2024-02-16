Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

Intution, 
1. loop until we get to the first node we want to reverse
2. Do regular reverse linked list
3. connect the left half to newly reversed portion, and connect right half to newly reversed portion 

TC: O(N) since we visit each node at most 1 time
SC: O(1) since we only use pointers 

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null; 
        
        ListNode prev = null; //to reverse nodes later
        ListNode curr = head; 
        
        while(m>1){ //to get to the mth node, node the first node is 1, 2nd node is 2, etcc. 
            prev = curr; 
            curr = curr.next;
            m--;
            n--; 
        } //now curr points to the mth node 
        
        ListNode connection = prev; //to connect left half with start of reversed portion 
        ListNode tail = curr; //to connect right half of list with end of reversed portion
        
        
        //REVERSE LINKEDLIST
        while(n>0){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next; 
            n--; 
        }
        
        if(connection!=null){
            connection.next = prev; 
        } else {
            head = prev; //in the case where m==0, this is just regular reverse linkedlist 
        }
        
        tail.next = curr;
        return head; 
        
    }
}