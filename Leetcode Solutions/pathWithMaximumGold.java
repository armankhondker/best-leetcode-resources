// In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold 
// in that cell, 0 if it is empty.

// Return the maximum amount of gold you can collect under the conditions:

// Every time you are located in a cell you will collect all the gold in that cell.
// From your position you can walk one step to the left, right, up or down.
// You can't visit the same cell more than once.
// Never visit a cell with 0 gold.
// You can start and stop collecting gold from any position in the grid that has some gold


// Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
// Output: 24
// Explanation:
// [[0,6,0],
//  [5,8,7],
//  [0,9,0]]
// Path to get the maximum gold, 9 -> 8 -> 7.



// Explain
// The idea is so simple, using DFS vs Backtracking, try all possible paths, return the path with maxGold

// Complexity

// - Time: O(4 * 3^k), k is the number of cells that have gold. k maximum is 25
// Because the first cell has up to 4 choices, the (k-1) remain cells have up to 3 choices. 
// And we make k different gold cells as first cell. So k * 4*3^(k-1) = 4 * 3^k
//O(m*n ) IN INTERVIEW!!

// - Space: m*n -> DFS stack depth
// m is the number of rows, n is the number of columns in the matrix
// 1 <= m, n <= 1

class Solution {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                maxGold = Math.max(maxGold, findMaxGold(grid, r, c));

        return maxGold;
    }

    private static final int[] directions = new int[]{0, 1, 0, -1, 0};

    private int findMaxGold(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;

        int origin = grid[r][c];
        grid[r][c] = 0; // mark as visited

        int maxGold = 0;
        for (int i = 0; i < 4; i++)
            maxGold = Math.max(maxGold, findMaxGold(grid, directions[i] + r, directions[i+1] + c));

        grid[r][c] = origin; // backtrack
        
        return maxGold + origin;
    }
}