d// You are given two non-empty linked lists representing two non-negative integers. 
// The most significant digit comes first and each of their nodes contain a single digit. 
// Add the two numbers and return it as a linked list.

// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

// Follow up:
// What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

// Example:

// Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 8 -> 0 -> 7

NAIVE SOLUTION, REVERSE BOTH LISTS, then add

TC:O(max(N,M)) to go through both the lists, will be constrained by the longer list 
SC: O(max(N,M)) for the stacks to store all elements in both lists 

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
        ListNode n2 = reverse(l2);
        int carry = 0;
        l1 = n1;
        l2 = n2;
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead; 
        while(l1 != null || l2 != null)
        {
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
            if(carry>0)
            {
                curr.next= new ListNode(carry);
            }
            return reverse(dummyHead.next); 
    }
    
public ListNode reverse(ListNode head){
        ListNode newHead = null;
        while(head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }


BEST SOLUTION 
IF YOU CANT MODIFY INPUTS (REVERSE THEM)
USE STACKS OF THE VALUES!!!!

TC:O(max(N,M)) to go through both the lists, will be constrained by the longer list 
SC: O(max(N,M)) for the stacks to store all elements in both lists 

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>(); 
        
        while(l1!=null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            s2.push(l2.val);
            l2 = l2.next; 
        }
        int carry = 0;
        ListNode dummy = null; 
        
        while(!s1.isEmpty() || !s2.isEmpty()){
            int total = 0;
            if(!s1.isEmpty()){
                total+=s1.pop();
            }
            if(!s2.isEmpty()){
                total+=s2.pop(); 
            }
            total+=carry; 
            ListNode newNode = new ListNode(total%10);
            carry = total/10; 
            
            newNode.next = dummy; 
            dummy = newNode; 
        }
        
        if(carry >0){
            ListNode newNode = new ListNode(carry);
            newNode.next = dummy;
            dummy = newNode; 
        }
        
        return dummy; 
    }
}