public ListNode reverseLinkedList (ListNode head)
{
	ListNode prev = null;
	ListNode curr = head;
	while(curr!=null)
	{
		ListNode next = curr.next;
		curr.next=prev;
		prev=curr;
		curr =next; 

	}
	return prev; 
}