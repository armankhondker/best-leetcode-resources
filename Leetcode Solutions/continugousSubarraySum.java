// Given a list of non-negative numbers and a target integer k, write a function to check if the array 
// has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to
//  n*k where n is also an integer.

// Example 1:

// Input: [23, 2, 4, 6, 7],  k=6
// Output: True
// Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.


BRUTE FORCE, go through every possible subarray and check if sum mod k is 0.
TC: O(n^3) to go through every possible subarray, and then go through all elements and get sum 
sc: O(1)

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {

        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++)
                    sum += nums[i];
                if (sum == k || (k != 0 && sum % k == 0))
                    return true;
            }
        }
        return false;
    }
}

BRUTE FORCE OPTIMIZED, same logic, but store the prefix sums into an array, so dont have to recompute repeatedly
TC: O(n^2) to go through every possible subarray
SC: O(n) to store the prefix sums of array


public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return false;
        
        int[] preSum = new int[nums.length+1];
        
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+2; j <= nums.length; j++) {
                if (k == 0) {//special logic for this case
                    if (preSum[j] - preSum[i] == 0) { 
                        return true;
                    }
                } else if ((preSum[j] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}



BEST SOLUTION!! HARD TO FOLLOW LOGIC, implement first solution!!
INTUTION, need to keep track of prefix sum and mod value!!!

/** Key point: if we can find any two subarray of prefix sum have same mod value, then their difference MUST be
 * divisible by k. So we can use a map to store mod value of each prefix sum in map, with its index. Then check
 * if map contains the same mod value with size > 2 when we have new mod value in every iteration */

EXPLAINATION OF WHY

// this is one of those magics of remainder theorem :)

// (a+(n*x))%x is same as (a%x)

// For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the remainders are [5,1,1,5,0]. 
// We got remainder 5 at index 0 and at index 3. That means, in between these two indexes we must have added a number 
// which is multiple of the k. H


EXPLAINATION FOR (0, -1)
// <0,-1> can allow it to return true when the runningSum%k=0,
// In addition, it also avoids the first element of the array is the multiple of k, since 0-(-1)=1 
//is not greater than 1.
// I think it's really beautiful and elegant here!

//TC: O(N) where n is length, to go through the entire input array
//SC: O(N) to keep a map of the 

public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};; //running sum, index of that sum

    int runningSum = 0;
    for (int i=0;i<nums.length;i++) {
        runningSum += nums[i];       //keep iterating running run
        if (k != 0) runningSum %= k;     //so we can store the REMAINDER in the map 
        Integer prev = map.get(runningSum); //previous occurence of this sum 
        if (prev != null) {
            if (i - prev > 1) return true;  //NEED WINDOW OF LENGTH 2!!! THATS WHY THIS LINE
        }
        else map.put(runningSum, i); //store running sum, to its index found 
    }
    return false;
}