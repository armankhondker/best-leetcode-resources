// This question is about implementing a basic elimination algorithm for Candy Crush.

// Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

// If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at 
// the same time - these positions become empty.
// After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, 
// then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop
//  outside the top boundary.)
// After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the 
// above steps.
// If there does not exist more candies that can be crushed (ie. the board is stable), then return the 
// current board. You need to perform the above rules until the board becomes stable, then return the 
// current board.

The idea is to traverse the entire matrix again and again to remove crush until no crush can be found.

For each traversal of the matrix, we only check two directions, rightward and downward. 
There is no need to check upward and leftward because they would have been checked from previous cells.

For each cell, if there are at least three candies of the same type rightward or downward, set them all 
to its negative value to mark them.

After each traversal, we need to remove all those negative values by setting them to 0, then 
let the rest drop down to their correct position. A easy way is to iterate through each column, 
for each column, move positive values to the bottom then set the rest to 0.


1. loop continously while we have 3 or more consenctuive
2. mark all 3 conseutive candies wil a negative value 
2. collapse the board
3. return board once we break out 


//TC: O((n*m)^2) because 

// O((N*M)^2) complexity because each function call scans the board three times so it's 
// 3(R*C). If we only crush 3 candies each time, the function will be called (R*C)/3 times. 
// Multiply those two terms together you get O((R*C)^2).

//SC: O(1) constant space
class Solution {
    public int[][] candyCrush(int[][] board) {
        int N = board.length, M = board[0].length;
        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = Math.abs(board[i][j]);
                    if (val == 0) continue;
                    if (j < M - 2 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                        found = true;
                        int ind = j;
                        while (ind < M && Math.abs(board[i][ind]) == val) board[i][ind++] = -val;
                    }
                    if (i < N - 2 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                        found = true;
                        int ind = i;
                        while (ind < N && Math.abs(board[ind][j]) == val) board[ind++][j] = -val;           
                    }
                }
            }
            //collapse the board, could break into helper function 
            if (found) { // move positive values to the bottom, then set the rest to 0
                for (int j = 0; j < M; j++) {
                    int storeInd = N - 1;
                    for (int i = N - 1; i >= 0; i--) {
                        if (board[i][j] > 0) {
                            board[storeInd--][j] = board[i][j]; //start from bottom and set to 0
                        }
                    }
                    for (int k = storeInd; k >= 0; k--) board[k][j] = 0;
                }
            }
        }
        return board;
    }
}