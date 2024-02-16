
TC: O(N+M) to go through length of both lists
SC: O(1)


class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       
        ListNode dummy= new ListNode(-1); 
        ListNode newHead = dummy;   
        if(l2==null)
        {
            return l1;
        }
        else if (l1==null)
        {
            return l2; 
        }
        
        while(l1!= null && l2!=null)
        {
            if(l1.val <= l2.val)
            {
                newHead.next=l1;
                newHead=newHead.next;
                l1=l1.next;
            }
            else
            {
                newHead.next=l2;
                newHead=newHead.next;
                l2=l2.next;
            }
        }
        
        newHead.next = l2 == null ? l1: l2; 
        return dummy.next; 
        
    }
}