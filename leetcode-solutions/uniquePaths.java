A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right 
corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


TC: O(MxN) to go through a fill out the dp array
SC: O(MxN) to store the DP array 


class Solution {
    public int uniquePaths(int m, int n) {
        int [][] dp = new int[m][n];
        dp[0][1] = 1;
        dp[1][0] = 1;
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}


TECHNICALLY ONLY NEED ONE DP ARRAY, optimize space here

TC: O(NxM)
SC: O(N)

    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        if(m == 1|| n ==1) return 1;
        int [] dp = new int[n];
        Arrays.fill(dp,1);//The only place I change
        for(int i = 1;i<m;i++){//loop m-1 times
            for(int j=1;j<n;j++){
                dp[j]=dp[j]+dp[j-1];
            }
        }
        return dp[n-1];
    } 