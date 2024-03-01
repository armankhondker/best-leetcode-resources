//Possibly can use backtracking, however since we will compute every possible decision we can make
//and exchaust the decision space, we will take O(2^n) time which we dont want

//DP approach can be used - 
//Top-down Dynamic Programming can be thought of as optimized backtracking. 
//It relies on the observation that once we determine that a certain index is good / bad,
// this result will never change. This means that we can store the result and not need to recompute it every time


TC :O(n^2) For every element in the array, say i, we are looking at the next nums[i] 
elements to its right aiming to find a GOOD index. nums[i] can be at most nn, where nn is the length of 
array nums.
Space complexity : O(n). This comes from the usage of the memo table.


//Greedy approach- most optimal
//Can start from end of array, and see if previous position is able to reach 

//TC: O(N)
//SC: O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int lastGoodIndexPosition = nums.length-1;
        for(int i=nums.length-1; i>=0; i--)   //start from back 
        {
            if(nums[i] + i >= lastGoodIndexPosition)
            {
                lastGoodIndexPosition = i;
            }
        
        }
        return lastGoodIndexPosition == 0; 
    }
}