Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space



SOLUTION 1 - Copy into Arraylist, then 2 pointer method!!


TC: O(N) to go through the entire list
SC: O(N) for the arraylist space

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true; 
        
        ArrayList<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while(curr!=null){
            list.add(curr.val);
            curr = curr.next; 
        }
        
        int left = 0;
        int right = list.size()-1;
        
        while(left < right){
            
            if(!list.get(left).equals(list.get(right))){ //need .equals since we are comparing Integers s
                return false;
            }
            left++;
            right--; 
        }
        return true; 
    }
}



SOLUTION 2 - Reverse the list in place, ONLY IF WE ALLOWED TO MODIFY INPUT

Only issue is that, if we do this, in a concurrent environment (multiple threads and processes), 
access to the linkedlist would hve to be locked while running this function, to avoid
issues of the linkedlist being modifyed

TC: O(N) - to go through the entire list
SC: O(1) - constant space, we modify the input

1.Find the end of the first half.
2. Reverse the second half.
3. Determine whether or not there is a palindrome.
4. Restore the list. //not necessary for input/output, but good programming practice
5. Return the result.


class Solution {

    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }        

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}