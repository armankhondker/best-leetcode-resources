// Find the kth largest element in an unsorted array. Note that it is the kth 
// largest element in the sorted order, not the kth distinct element.

//Naive solution, sort the array and then find kth largest. O(nlogn)

//TC: O(Nlogk), for every N item we will insert into heap of size k O(logk) operation 
//SC: O(k) to store the heap elements.

class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        if(nums == null) return -1; 
        int ans =0; 
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();  //minheap 
        
        
        for(int x: nums)
        {
            heap.add(x);
            if(heap.size()>k)
            {
                heap.remove();
            }
        }
    
        return heap.peek(); 
    }
}