// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
// Example:

// Input:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// Output: 1->1->2->3->4->4->5->6



// Brute force SOLUTION

// 1. go through all lists and put them into array
// 2. sort the arrays
// 3. creat the result and populate it from arrays
// Will take O(NlogN) time to sort the arrays , where N is total number of nodes 
// Will take O(N) for result list



Intution, can solve this by putting every Node into a minheap, and then remove every node, and then we will have
sorted list

MINHEAP SOLUTION

TC: O(NlogK) where k is the number of linked lists. because heap will be at most size k at any point
THIS IS MUCH BETTER, than brute force time complexity of O(NlogN)
SC: O(N) space to store new linkedlist will all the nodes

The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. 
But finding the node with the smallest value just costs O(1) time.
There are N nodes in the final linked list.

class Solution {
   class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->a.val - b.val);
        
        ListNode dummy = new ListNode(-1); //dummy.next will be the head to returns
        ListNode curr = dummy; 
        
        for(ListNode x: lists){
            if(x!=null){
                minHeap.add(x); 
            }
        }
        
        while(!minHeap.isEmpty()){
            curr.next = minHeap.poll(); 
            curr = curr.next; 
            
            if(curr.next!=null){
                minHeap.add(curr.next);
            }
            
        }
        
        return dummy.next; 
    }
}
}