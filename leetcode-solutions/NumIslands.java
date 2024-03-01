//O(M*N) runtime where M is rows, N is columns
//O(M*N) grid maps is filled with space


// We do iterate across the MN items in the grid, but for each element we don't 
// actually visit MN items. Each cell has at the worst case 4 neighbors. So overall we 
// visit at most O(4*M*N) cells (which is still O(MN)).


    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    res += dfsSink(grid, i, j);
                }
            }
        }
        return res; 
    }
    
    public int dfsSink(char[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == '0'){
            return 0;
        }
        grid[i][j] = '0';
        dfsSink(grid, i+1, j);
        dfsSink(grid, i-1, j);
        dfsSink(grid, i, j+1);
        dfsSink(grid, i, j-1);
        return 1; 
    }


