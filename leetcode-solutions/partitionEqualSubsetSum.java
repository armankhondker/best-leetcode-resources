// Given a non-empty array containing only positive integers, find if the array can be partitioned into two 
// subsets such that the sum of elements in both subsets is equal.

// Note:

// Each of the array element will not exceed 100.
// The array size will not exceed 200.

// Example 1:

// Input: [1, 5, 11, 5]

// Output: true

// Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

// Example 2:

// Input: [1, 2, 3, 5]

// Output: false

// Explanation: The array cannot be partitioned into equal sum subsets.

INTUITION, simulate making different subsets of the array 

Base case, if we sum up all the elements in the array, and the sum isnt even, automatically cant do it!!

UNOPTIMAL, RECURSIVE SOLUTION, go through all of the different subsets of items 

//TC: O(2^n) time to go through each combination
//SC: O(N) for recursions stack space

class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int num: nums){
            total += num; 
        }
        if(total % 2 == 1) return false; //we have odd sum, so not possible ever
        return canPartitionHelper(nums, 0, 0, total); 
    }
    
    
    //helper function to find all possible 2^n combinations of the elements, 2^n because at each element 
    //we can either take it or not take it 
    public boolean canPartitionHelper(int [] nums, int index, int sum, int total){
        if(sum *2 == total){ //we have a subset, that can split this evenly
            return true;  
        }
        if(sum > total/2 || index >= nums.length){  //out of bounds, or out of range 
            return false; 
        }
        
        return canPartitionHelper(nums, index+1, sum, total) || 
        canPartitionHelper(nums, index+1, sum+nums[index], total); //EITHER TAKE THE ITEM OR DONT
    }
}


CAN OPTIMIZE THIS, but not computing the same recursive problems over again, USE DYNAMIC PROGRAMMING,
WE can use DP here by storing STATE, to see if we computed 

O(N*M) OUR constraining factor is how many different states can we have and we wont need to recompute, 
where n is the length of nums, and M is the number of different ways we can have a sum, STATE is what 
determines our runtime, so diff combinations of state are NxM
O(N) for the recursions stack and the hashmap space 

class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int num: nums){
            total += num; 
        }
        if(total % 2 == 1) return false; //we have odd sum, so not possible ever    
        return canPartitionHelper(nums, 0, 0, total, new HashMap<String, Boolean>()); 
    }
    
    public boolean canPartitionHelper(int [] nums, int index, int sum, int total, HashMap<String, Boolean> state){
        String current = index + "" + sum; //These are the two things that define our state, if we have ever computed 
        								 //this combaination
        
        if(state.containsKey(current)){    //if we have computed this state before, JUST RETURN IT, 
                                           // save us recomputing 
            return state.get(current);
        }
        if(sum *2 == total){ //we have a subset, that can split this evenly
            return true;  
        }
        if(sum > total/2 || index >= nums.length){  //base cases where we have gone out of bounds, or out of range 
            return false; 
        }
        
        boolean foundPartition = canPartitionHelper(nums, index+1, sum, total, state) 
            || canPartitionHelper(nums, index+1, sum+nums[index], total, state); //EITHER TAKE THE ITEM OR DONT
        state.put(current, foundPartition); //store what we have found in state 
        return foundPartition; 
    }
}


ITERATIVE DP

// It's similar to Subset Sum Problemwhich asks us to find if there is a subset whose sum equals to 
// target value. For this problem, the target value is exactly the half of sum of array. Let's see the code.


public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 == 1) return false;
        
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        // deal with the first row
        if(nums[0] <= target) dp[0][nums[0]] = true;
        
        // deal with the first col
        for(int i = 0; i < nums.length; i++) dp[i][0] = true;
        
        // deal with the rest
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
