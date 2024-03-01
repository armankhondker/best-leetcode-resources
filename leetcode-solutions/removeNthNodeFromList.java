Given a linked list, remove the n-th node from the end of list and return its head.


//TWO PASS, get the length of the list, then go through again length-n times to get to the correct position
//then rewire the next pointers 

TC: O(n) where n is length of linked list, BUT need to do in two passes 
SC: O(1)
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int length  = 0;
    ListNode first = head;
    while (first != null) {
        length++;
        first = first.next;
    }
    length -= n; 
    first = dummy;
    while (length > 0) {
        length--;
        first = first.next;
    }
    first.next = first.next.next;  //rewire the next pointers 
    return dummy.next;
}



//ONE PASS ALGORITHM, use slow pointer and fast pointer. Advance fast to the node we are intersetd in
//beetter tc by just a little bit

TC: O(N) where n is length of linkedlist, ONLY ONE PASS :) 
SC: O(1)

public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n + 1; i++) {
        first = first.next;
    }
    // Move first to the end, maintaining the gap, by the time first is null we will have reached the position with second 
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    second.next = second.next.next;
    return dummy.next;
}