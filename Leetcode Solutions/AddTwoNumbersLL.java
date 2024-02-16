

//TC: O(N) to go throguh the length of the longest LL
//SC: O(1)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy; 
        int carry =0;
        while(l1 != null || l2!=null){
            int total = 0;
            if(l1!=null){
                total+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                total+=l2.val;
                l2=l2.next;
            }
            total+=carry; 
            
            curr.next = new ListNode(total%10);
            carry=total/10; 
            curr = curr.next; 
        }
        if(carry > 0){
            curr.next = new ListNode(carry); 
        }
        
        return dummy.next; 
    }
}