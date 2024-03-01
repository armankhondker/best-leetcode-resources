// Given a m x n grid filled with non-negative numbers, 
// find a path from top left to bottom right which minimizes the sum of all numbers along its path.



//intiution is if we can find the smallest sum to get to any point in grid, we can find smallest to get to
//bottom right 


DYNAMIC PROGRAMMING

// TC : O(mn). We traverse the entire matrix once.
// SC: O(mn). Another matrix of the same size is used.'

class Solution {
    public int minPathSum(int[][] grid) {
           if(grid == null || grid.length ==0) return 0;
        
        int [][] dp = new int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++)
            {
                dp[i][j] += grid[i][j];
                if(i>0 && j>0) //not on 0th row or 0th column 
                {
                    dp[i][j] += Math.min(dp[i-1][j], dp[i][j-1]);
                }
                else if (i >0) //on 0th column
                {
                    dp[i][j] += dp[i-1][j];
                }
                else if (j > 0) //on 0th row 
                {
                    dp[i][j] += dp[i][j-1]; 
                }
            }
        }
        
        return dp[dp.length-1][dp[0].length-1]; 
        
        
    }
    }
}

OPTIMIZED CONSTANT SPACE
JUST USE the input array to store to DP values, exact same code without dp array 

//TC: O(MN)
//SC: O(1)

class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length ==0) return 0; 
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(i >0 && j > 0){
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
                } else if (i>0){
                    grid[i][j] += grid[i-1][j];
                } else if (j>0){
                    grid[i][j] += grid[i][j-1];
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1]; 
        }
}