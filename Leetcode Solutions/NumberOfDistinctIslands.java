// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) 
//connected 4-directionally (horizontal or vertical.) 
// You may assume all four edges of the grid are surrounded by water.

// Count the number of distinct islands. An island is considered to be the same as another
// if and only if one island can be translated (and not rotated or reflected) to equal the other.

//explore islands using DFS is easy part
//need a way to keep track of shapes that are the same (SAME ISLAND)

// Since two islands are the same if one can be translated to match another, let's 
// translate every island so the top-left corner is (0, 0) For example, if an island is made 
// from squares [(2, 3), (2, 4), (3, 4)], we can think of this shape as [(0, 0), (0, 1), (1, 1)]
//  when anchored at the top-left corner.

class Solution {
     int[][] grid;
    boolean[][] seen;
    Set<Integer> shape;
    
    public void explore(int r, int c, int r0, int c0) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add((r - r0) * 2 * grid[0].length + (c - c0));
            explore(r+1, c, r0, c0);
            explore(r-1, c, r0, c0);
            explore(r, c+1, r0, c0);
            explore(r, c-1, r0, c0);
        }
    }


    //TC: O(MxN) where we must iterate over every item in our grip
    //SC: O(MxN) for our recursion stack
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<HashSet<Integer>>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new HashSet<Integer>();
                explore(r, c, r, c);
                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }
}