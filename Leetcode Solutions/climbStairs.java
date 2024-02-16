


Brute Force - take all possible step combinations at every step, eventually will
TC: O(2^n) - can make 2 decisions at each step in which branches out 
SC: O(n) for stack space which tree can do depth of n 

    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

 EXAMPLE RECURSION TREE - 

       		   (0,5)
     	   /   		  \
        (1,5) 		  (2,5)
      	/   \			/\
   (2,5)   	(3,5)   (3,5) (4,5)
   	/\		  /\
(3,5) (4,5)

ETC......


Dynamic Programming - TOP DOWN (Recursion with Memoization)

In brute force we were redundantly storing the result at every step, can use
memoization array to build and remember results, build TOP down 

In this way we are pruning recursion tree with the help of memomemo array and 
reducing the size of recursion tree upto nn.

    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }



Dynamic Programming - BOTTOM UP 

TC: O(N) to go through all n steps 
SC: O(N) for the dp array

class Solution {
    public int climbStairs(int n) {
        if(n==1) return 1; 
        
        int [] dp = new int[n]; 
        //to reach the nth step, you have to come from either n-1 or n-2 step 
        dp[0]=1;
        dp[1]=2;
        for(int i=2;i<n; i++){
            dp[i] = dp[i-1]+dp[i-2]; 
        }
        
        
        return dp[n-1];
    }
}
FIBANACI NUMBER WAY- recognize we are just doing FIBANACI

TC: O(N) to calculate nth fib number
SC: O(1) since we only use two variables 

public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
