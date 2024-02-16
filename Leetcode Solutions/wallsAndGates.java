// You are given a m x n 2D grid initialized with these three possible values.

// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you 
// may assume that the distance to a gate is less than 2147483647.

// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, 
// it should be filled with INF.

// Example: 

// Given the 2D grid:

// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF

// After running your function, the 2D grid should be:

//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4


 INTUTION, run dfs from every gate on the grid, keep track of distance that we go, and populate array!
 NOTE, since we are calculating shortest distance, BFS MAY BE PREFFERED BY INTERVIEWER 

//TC: O(NM) to go through the entire grid and run dfs
//SC: O(NM) because our stack depth could be at max every cell in the grid 

class Solution {
	//run dfs on every cell with a gate and populate array!
    public void wallsAndGates(int[][] rooms) {
        for(int i=0; i<rooms.length; i++){
            for(int j=0; j<rooms[0].length; j++){
                if(rooms[i][j] == 0){
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }
    public void dfs(int[][] rooms, int i, int j, int distance){
        int nr = rooms.length;
        int nc = rooms[0].length;
        if(i < 0 || j < 0 || i >= nr || j >= nc || rooms[i][j] < distance){ //also checks -1
                            //out of bounds OR we have already updated with smaller value
            return; 
        }
        rooms[i][j] = distance; 
        dfs(rooms , i+1, j, distance+1);
        dfs(rooms, i-1, j, distance+1);
        dfs(rooms, i, j+1, distance+1);
        dfs(rooms, i, j-1, distance+1);

    }
}


BFS Solution

//TC: O(NM) to go through the etnire grid and check if a cell is a gate 
//SC: O(NM) because the queue can have at most every cell in grid 
public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] dirs = {{-1,0}, {0,1}, {0,-1}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i,j});
                }
            }
        }
        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir: dirs) {
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                if (X<0 || Y <0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE) continue;
                rooms[X][Y] = rooms[curPos[0]][curPos[1]]+1;
                queue.offer(new int[] {X, Y});
            }
        }
    }