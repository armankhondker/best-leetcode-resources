// Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
// the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches 
// the right and bottom edges.

// Water can only flow in four directions (up, down, left, or right) from a cell to another one with height 
// equal or lower.

// Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

// Note: The order of returned grid coordinates does not matter. Both m and n are less than 150.

// Example:

// Given the following 5x5 matrix:

//   Pacific ~   ~   ~   ~   ~ 
//        ~  1   2   2   3  (5) *
//        ~  3   2   3  (4) (4) *
//        ~  2   4  (5)  3   1  *
//        ~ (6) (7)  1   4   5  *
//        ~ (5)  1   1   2   4  *
//           *   *   *   *   * Atlantic

// Return:

// [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).


USE DFS TRAVERSAL, FROM all of the ocean boudaries, and mark every cell that is reachable from perspective ocean
all the cells that are reachable from both pacific and atlantic oceans, are in answer!!

//TC: O(N*M) to loop through the grid and check if visited by both atlantic and specific
//SC: O(N*M) to store the boolean arrays for pacific and atlantic oceans
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if(matrix.length == 0){
            return new ArrayList<List<Integer>>(); 
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length; 
        
        int [][] pacific = new int[rows][cols];
        int [][] atlantic = new int[rows][cols];
        
        
        List<List<Integer>> res = new ArrayList<>(); 
        
        //top and bottom row, call DFS and mark all reachable cells from there 
        for(int col = 0; col < matrix[0].length; col++){
            dfs(0, col, matrix, Integer.MIN_VALUE, pacific);
            dfs(matrix.length-1, col, matrix, Integer.MIN_VALUE, atlantic);
        }
        
        //left and right row, call DFS on these borders and mark cells which can explore to
        for(int row = 0; row < matrix.length; row++){
            dfs(row, 0, matrix, Integer.MIN_VALUE, pacific);
            dfs(row, matrix[0].length-1, matrix, Integer.MIN_VALUE, atlantic);
        }
        
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(pacific[i][j] == 1 && atlantic[i][j] == 1){
                    ArrayList<Integer> point = new ArrayList<>();
                    point.add(i);
                    point.add(j);
                    res.add(point); 
                }
            }
        }
        return res; 
    }
    public void dfs(int row, int col, int[][] matrix, int prevVal, int[][] ocean){
        
        //1. check necessary conditions
        if(row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length)
        {
            return;
        } else if(matrix[row][col] < prevVal){ //we can't travel to 
            return; 
        } else if(ocean[row][col] == 1){ //we have already reached here 
            return;
        }
        
        //2. process cell - mark cell as reachable 
        
        ocean[row][col] = 1;
        
        //3. call dfs on neighbors 
    
        dfs(row + 1, col , matrix, matrix[row][col], ocean);
        dfs(row - 1, col , matrix, matrix[row][col], ocean);
        dfs(row , col + 1 , matrix, matrix[row][col], ocean);
        dfs(row , col  - 1, matrix, matrix[row][col], ocean);
    }
}


BFS SOLUTION, same time complexity!

//TC: O(N*M) to loop through the grid and check if visited by both atlantic and specific
//SC: O(N*M) to store the boolean arrays for pacific and atlantic oceans
public class Solution {
    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        //One visited map for each ocean
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for(int i=0; i<n; i++){ //Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, m-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for(int i=0; i<m; i++){ //Horizontal border
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{n-1, i});
            pacific[0][i] = true;
            atlantic[n-1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i,j});
            }
        }
        return res;
    }
    public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
        int n = matrix.length, m = matrix[0].length;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] d:dir){
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            } 
        }
    }
}