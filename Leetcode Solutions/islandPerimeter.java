// You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

// Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
// and there is exactly one island (i.e., one or more connected land cells).

// The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
// One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
//  Determine the perimeter of the island.



// add to perimeter sum, by this formula
//if an item in any direction of it is NOT a piece of land, will need to add one side 

Counting the number of edges that adjacent to the water

//TC: O(mn)
//SC: O(1)

class Solution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
    
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) num++; // UP
                    if (j == 0 || grid[i][j - 1] == 0) num++; // LEFT
                    if (i == rows -1 || grid[i + 1][j] == 0) num++; // DOWN
                    if (j == cols -1 || grid[i][j + 1] == 0) num++; // RIGHT
                }
            }
        }
        return num;
    }
    
}