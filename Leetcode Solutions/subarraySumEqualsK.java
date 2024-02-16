Given an array of integers and an integer k, you need to find the total number
of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2


BRUTE FORCE - O(n^3) to go through all possible sub arrays, and then extra O(n) to compute sum




BRUTE FORCE- optimized, use a cumlativeSum array to keep track of sums, and then dont need extra n traversal

//TC: O(n^2) to go through all possible subarrays
//SC: O(n)

class Solution {
    public int subarraySum(int[] nums, int k) {
        int length = nums.length; 
        int [] cumulativeSum = new int[length]; //O(N) space for the extra array of cumulative sum 
        cumulativeSum[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            cumulativeSum[i] = cumulativeSum[i-1] + nums[i];
        }
        
        int res = 0; 
        
        //O(n^2) to go through all possible sub arrays
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<=nums.length; j++){
                if(cumulativeSum[j] - cumulativeSum[i] == k){
                    res++;
                }
            }
        }
        
        return res; 
        
    }
}


BRUTE FORCE - OPTIMIZED EVEN FURTHER, dont need array to store cumlative sum, constant space
//TC: O(N^2)
//SC: O(1)

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}



BEST SOLUTION - sum hashmap, plus two Sum problem 

//TC: O(N)
//SC: O(N)

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 0)    return 0; 
        // Sliding window
        // hashmap + preSum
        HashMap<Integer, Integer> map = new HashMap<>();  //cumuatlive sum, number of times we have seen that sum
        int sum = 0;
        int result = 0;
        map.put(0, 1);  //have seen a sum 0 zero, 1 time at beggining, need this for edge cases!!
        for(int cur : nums) {
            sum += cur;
            if(map.containsKey(sum - k))  // there exist a key, that [hashmap-key  =  sum - k]
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result; 
    }



// MORE INTUTIVE SOLUTION - CODE THIS ONE

//TC: O(N) the entire nums array is processed exactly once
//SC: O(N) hashmap contains up to n distinct elements

      public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        int sum = 0;
        // compute prefix sum array
        for(int i=0; i<nums.length; i++)
        {
            sum+=nums[i]; 
            sums[i+1] = sum;
        }
        
        //now the problem becomes find two items from this sums array so that sums[j]-sums[i] = k (similar to two sum)
        int count = 0; 
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<sums.length; i++)
        {
            //make sure to check sumes[i] here, not target
            if(map.containsKey(sums[i]))
                count+=map.get(sums[i]);
            int target = sums[i]+k;
            map.put(target, map.getOrDefault(target, 0)+1);
        }
        return count;        
    }