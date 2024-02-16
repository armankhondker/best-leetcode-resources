You are a professional robber planning to rob houses along a street. Each house has a certain amount 
of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses 
have security system connected and it will automatically contact the police if two adjacent houses were 
broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.





INTUITION: if we can fill out a dp array with max profit to rob the ith house, we can bottom up fill the array
and eventually find the max for n houses

BASE CASES: to rob 0 houses we can max profit 0, to rob 1 house we can max profit the value of that house,
to rob two houses, we can max pprofit the maximum between those two houses 


BOTTOM UP PROCESSING WITH DYNAMIC PROGRAMMING, NOT MOST OPTIMAL YET

//TC: O(N)
//SC: O(N)

class Solution {
    public int rob(int[] nums) {
        
        if(nums.length == 0 || nums == null){  // max money we can make robbing 0 houses 
            return 0;
        }
        if(nums.length == 1) { //max money to rob one house 
            return nums[0];
        }
        
        int [] dp = new int[nums.length]; //max amount of money to rob the ith house

        dp[0] = nums[0]; //max amount of money robbing 1 house
        dp[1] = Math.max(nums[0], nums[1]); //max amount of money robbing first 2 houses
        
        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]); // max amount of money we can make at ith house is, max between this house plus max of two houses ago,
                                                          //OR the max of the previous house 
        }
        return dp[nums.length-1]; //return last element in our dp array 
    }
}


OPTIMIZED FOR SPACE, remove the dp array, and just use two varibles for the dp values 


//TC: O(N)
//SC: O(1)

class Solution {
    public int rob(int[] nums) {
        
        if(nums.length == 0 || nums == null){  // max money we can make robbing 0 houses 
            return 0;
        }
        if(nums.length == 1) { //max money to rob one house 
            return nums[0];
        }
        
//         int [] dp = new int[nums.length]; //max amount of money to rob the ith house

//         dp[0] = nums[0]; //max amount of money robbing 1 house
//         dp[1] = Math.max(nums[0], nums[1]); //max amount of money robbing first 2 houses
        
        int twoHousesAgo = 0;
        int previousHouse = 0; 
        
        for(int i=0; i<nums.length; i++){
            int tmp = previousHouse; 
            previousHouse = Math.max(twoHousesAgo + nums[i], previousHouse);
            twoHousesAgo = tmp; 
                                                         
        }
        return previousHouse; //return last element in our dp array 
    }
}


