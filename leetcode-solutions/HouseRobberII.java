
You are a professional robber planning to rob houses along a street. Each house has a certain amount of 
money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor
 of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact 
 the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum 
amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.


INTUITION, same as house robber 1 EXCEPT we have a circle at the end. SO, we can split this problem into two house 
robber 1 sub problems. Find max between, house robber one including the first element to second to last,
OR second element to last element,

BASICALLY, just make houseRobberOne a helper function, and call with those bounds, and return the max 

This is because, the answer can either include the first house, or not include it