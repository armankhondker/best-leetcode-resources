You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice 
so the sum of the face up numbers equals target.


//Intuition, very similar to coin change, need dynamic programming 

//TC: O(f*d*target)
//SC: O(d+target)

class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        int MOD = 1000000007; //prevent integer overflow
        int[][] dp = new int[d + 1][target + 1]; 
        dp[0][0] = 1;
		//how many possibility can i dices sum up to j;
        for(int i = 1; i <= d; i++) {
            for(int j = 1; j<= target; j++) {
                if(j > i * f) {
                   continue;         //If j is larger than largest possible sum of i dices, there is no possible ways.        
                } else {                      //watch out below condition, or NPE
                    for(int k = 1; k <= f && k <= j ; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;   //dp[i][j] means how many ways that using i dices to sum to target j.
																	// And the state function is dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - k].

                    }
                }
            }
        }
        return dp[d][target];
    }
}

Let dp(d, f, target) be the number of possible dice rolls for the given parameters.

// As an initial example, pretend we have 5 dice with 6 faces each and we want to determine how many ways to make 18.
// In other words, what is dp(5, 6, 18)?

// At first glance, this is seems difficult and overwhelming. But if we make one simple observation, we can reduce 
// this big problem into several smaller sub-problems. We have 5 dice, but let's first just look at ONE of 
// these dice (say the last one). This die can take on f=6 different values (1 to 6), so we consider what happens 
// when we fix its value to each possibility (6 cases):

Case 1: The last die is a 1. The remaining 4 dice must sum to 18-1=17. This can happen dp(4, 6, 17) ways.
Case 2: The last die is a 2. The remaining 4 dice must sum to 18-2=16. This can happen dp(4, 6, 16) ways.
Case 3: The last die is a 3. The remaining 4 dice must sum to 18-3=15. This can happen dp(4, 6, 15) ways.
Case 4: The last die is a 4. The remaining 4 dice must sum to 18-4=14. This can happen dp(4, 6, 14) ways.
Case 5: The last die is a 5. The remaining 4 dice must sum to 18-5=13. This can happen dp(4, 6, 13) ways.
Case 6: The last die is a 6. The remaining 4 dice must sum to 18-6=12. This can happen dp(4, 6, 12) ways.

dp(5, 6, 18) = dp(4, 6, 17) + dp(4, 6, 16) + dp(4, 6, 15) + dp(4, 6, 14) + dp(4, 6, 13) + dp(4, 6, 12)

We sum up the solutions these 6 cases to arrive at our result. Of course, each of these cases branches off into 
6 cases of its own, and the recursion only resolves when d=0. The handling of this base case is explained below.