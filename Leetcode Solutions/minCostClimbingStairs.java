On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum 
cost to reach the top of the floor, and you can either start from the step with index 0, 
or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].


BOTTOM UP DP APPROACH

TC: O(N) to go through csots array 
SC: O(N) to store dp array 

// dp[i] represents the minimum cost that we can reach i-th stair.
// We can either come from (i - 2)-th stair or (i - 1)-th stair.
// Notice: return the minimum of the last and second-last stair's cost.

class Solution {
   public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1]+ cost[i], dp[i - 2] + cost[i]);
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }
}

BOTTOM UP SAVE SPACE - with variables

TC: O(N) to go through cost array
SC: O(1) we only use variable

public int minCostClimbingStairs(int[] cost) {
        int twoStepBefore = cost[0];
        int oneStepBefore = cost[1];
        int curr = 0;
        for(int i = 2;i< cost.length;i++){
            curr = Math.min(twoStepBefore,oneStepBefore) + cost[i];
            twoStepBefore = oneStepBefore;
            oneStepBefore = curr;
        }
        return Math.min(oneStepBefore,twoStepBefore);
    }