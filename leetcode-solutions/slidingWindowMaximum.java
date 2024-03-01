Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7]

Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7



UNOPTIMAL MAX HEAP SOLUTOON 

TC: O(N*logk) because we will have to add al elements to heap, which is N*logk operation
SC: O(k) for a heap of size k

 public class Solution {
public int[] maxSlidingWindow(int[] nums, int k) {
    int len = nums.length;
    int[] result = new int[len - k + 1]; //amount of windows we have 
    if(nums.length == 0) return new int[0];
    Queue<Integer> queue = new PriorityQueue<Integer>((a,b)->b-a); //MAXHEAP
    
    for(int i = 0; i < k; i ++){ //add k window of elements to the heapelements to the heap
        queue.add(nums[i]);
    }
    
    result[0] = queue.peek();  //the first element will be top element in max heap initially 

    for(int i = k; i < len; i++){
        queue.remove(nums[i - k]);    //worst case O(logk) time
        queue.add(nums[i]);
        result[i - k + 1] = queue.peek();
    }
   
    return result;
}

OPTMIAL DEQUE Solution
TC: O(N)
SC: O(k) for the space of the deque

ALGORITHM: 

1. Process the first k elements separately to initiate the deque.

2. Iterate over the array. At each step :

		Clean the deque :

			Keep only the indexes of elements from the current sliding window.

			Remove indexes of all elements smaller than the current one, since they will not be the maximum ones.

		Append the current element to the deque.

		Append deque[0] to the output.

3. Return the output array.


public int[] maxSlidingWindow(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] result = new int[n-k+1];
		int ri = 0; //index for result
		
		Deque<Integer> q = new ArrayDeque<>(); //deque of indexes
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				result[ri++] = a[q.peek()];
			}
		}
		return result;
	}


