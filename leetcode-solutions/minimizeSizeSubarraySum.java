// Given an array of n positive integers and a positive integer s, find the 
// minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

// Example: 

// Input: s = 7, nums = [2,3,1,2,4,3]
// Output: 2
// Explanation: the subarray [4,3] has the minimal length under the problem constraint.






BEST SOLUTION, TWO POINTER, similar to sliding window String

//TC: O(N)
//SC: O(1)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE; //want to minimize this 
        
        int left = 0;
        int right =0;
        
        int curSum = 0;
        
        while(right < nums.length){
            curSum+= nums[right];

           while(curSum >= s){ //keep checking window size
               res = Math.min(res, right-left+1); //see if window size is minum
               curSum-= nums[left]; //subtract out the left value
               left++; //make window smaller
           }
            
           right++; //keep moving right now  
       }
        
        if(res == Integer.MAX_VALUE) return 0;
        return res; 
    }
}



LESS EFFICIENT BINARY SEARCH WAY

logN immediately reminds you of binary search. In this case, 
you cannot sort as the current order actually matters. How does one get an ordered array then? 
Since all elements are positive, the cumulative sum must be strictly increasing. Then, 
a subarray sum can expressed as the difference between two cumulative sum. Hence, 
given a start index for the cumulative sum array, the other end index can be searched using binary search.

//TC: O(NlogN)
//SC: O(N) space for sums array 

  private int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }