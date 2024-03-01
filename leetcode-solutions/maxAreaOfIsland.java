// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) 
// connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid 
// are surrounded by water.

// Find the maximum area of an island in the given 2D array. (If there is no island, 
//     the maximum area is 0.)

// Example 1:

// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,1,1,0,1,0,0,0,0,0,0,0,0],
//  [0,1,0,0,1,1,0,0,1,0,1,0,0],
//  [0,1,0,0,1,1,0,0,1,1,1,0,0],
//  [0,0,0,0,0,0,0,0,0,0,1,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,0,0,0,0,0,0,1,1,0,0,0,0]]

TIME COMPLEXITY VERIFICATION 
// You're right that we do iterate across the MN items in the grid, but for each element we don't 
// actually visit MN items. Each cell has at the worst case 4 neighbors. So overall we visit at 
// most O(4*M*N) cells (which is still O(MN)).

// Of course, that's a large overestimate. Not every cell has 4 neighbors 

// Additionally, because we mark cells as visited (either by converting them to '0' or using a set/hasmap),
// we won't visit the same cells multiple times. In fact, for both BFS and DFS we're 
// guaranteed to visit every cell exactly once. So our runtime in the worst case is strictly O(M*N)


TC: O(MN) to visit every cell and determine if it is 
SC: O(MN) because our dfs recursive calls and visit the entire board in worst case where we have 
all 1s

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid == null) return 0 ;
        int maxArea = 0; 
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                int area = 0;
                if(grid[i][j] == 1){
                    area += dfs(grid, i, j); 
                    maxArea = Math.max(maxArea, area); 
                }
            }
        }
        
        return maxArea; 
    }
    public int dfs(int [][] grid, int i, int j){
        if(i>=grid.length || i<0 || j>=grid[0].length || j<0 || grid[i][j] == 0){
            return 0; 
        }
        grid[i][j] = 0; 

        return 1 +   dfs(grid, i-1, j)
        + dfs(grid, i+1, j)
        + dfs(grid, i, j-1)
        + dfs(grid,i,j+1);
    }
}