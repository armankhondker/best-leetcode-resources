


USE BFS like solution by with queue 

//TC: O(M*n) because in worst case complete traversal of maze will occur  
//SC: O(M*n) for the visted array


class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> q = new LinkedList<>(); //queue to hold all the different possible coordiantes
        q.offer(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length]; //keep track of visited cells
        visited[start[0]][start[1]] = true;
        int[][] dirs = {{0,1} , {1,0} , {0,-1}, {-1, 0}}; // UP, RIGHT, DOWN, LEFT
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            if(curr[0] == destination [0] && curr[1] == destination[1]) return true; 
            for(int [] dir : dirs){  //we will check in each direction 
                int x = curr[0];
                int y = curr[1];
                while(isValidCell(x+dir[0], y+dir[1], maze)){ //let the ball keep rolling
                    x+=dir[0];
                    y+=dir[1];
                }
                
                if(!visited[x][y]){  //mark as visited and add into the queue 
                    visited[x][y] = true; 
                    q.offer(new int[]{x,y}); 
                }
                 
            }
        }
        return false; 
    }
    
    public boolean isValidCell (int x, int y, int[][] maze){
        return x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y] != 1;
        //if all this holds, then we have a valid cell
    }
}