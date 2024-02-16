//2 SOLUTIONS

// O(n) solution

// It is only the last step that triggers the win so we just need to check the row/col/diagonal where 
//the last step/"cell" belongs to, which means we don't really need to check all the rows/cols/diagonals for 
//each move. This will help us to get O(n) for each move instead of O(n^2)

// TC: O(n)
// SC: O(n^2)
class TicTacToe {
    
    int[][] grid;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        grid = new int[n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        // validation TODO
        if (row >= grid.length || col >= grid.length) return 0; // out of the grid
        if (grid[row][col] != 0) return 0; // cell is used
        grid[row][col] = player == 1 ? 1 : 2;
        if (checkVerticallyWin(col, player)) return player;
        if (checkHorizontallyWin(row, player)) return player;
        if (checkDiagonallyWin(row, col, player)) return player;
        return 0;
    }
    
    private boolean checkVerticallyWin(int col, int player) {
        for (int i=0; i<grid.length; i++) {
            if (grid[i][col] != player) return false;
        }
        return true;
    }
    
    private boolean checkHorizontallyWin(int row, int player) {
        for (int j=0; j<grid[0].length; j++) {
            if (grid[row][j] != player) return false;
        }
        return true;
    }
    
    private boolean checkDiagonallyWin(int row, int col, int player) {
        if (row != col && row+col != grid.length-1) return false;
        boolean topLeftToBottomRight = true;
        boolean topRightToBottomLeft = true;
        for (int i=0; i<grid.length; i++) {
            if (grid[i][i] != player) topLeftToBottomRight = false;
        }
        for (int i=0; i<grid.length; i++) {
            if (grid[i][grid.length-1-i] != player) topRightToBottomLeft = false;
        }
        return topRightToBottomLeft || topLeftToBottomRight;
    }
}




//Can we do better? The anwser is yes! We can also keep track of the sum of each row/col/diagonals which makes 
//us to achieve O(1):

// TC: O(1)
// SC: O(n)
class TicTacToe {
    int[] rows;  //keep track of row sum
    int[] cols;   //keep track of col sum 
    int topLeftToBottomRight;
    int topRightToBottomLeft;
    
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        topLeftToBottomRight = 0;
        topRightToBottomLeft = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        rows[row] += player == 1 ? 1 : -1; // player 1 --> +1 / player 2 --> -1     //either subtract or add one to the row
        cols[col] += player == 1 ? 1 : -1;      
        if (row == col) topLeftToBottomRight += player == 1 ? 1 : -1;
        if (row+col == rows.length-1) topRightToBottomLeft += player == 1 ? 1 : -1;
        
        if (rows[row] == rows.length || cols[col] == rows.length 
            || topLeftToBottomRight == rows.length || topRightToBottomLeft == rows.length) return 1;   //if any rows sum is equal to n
        if (rows[row] == -rows.length || cols[col] == -rows.length                                      //then we have a winner 
            || topLeftToBottomRight == -rows.length || topRightToBottomLeft == -rows.length) return 2;
        
        return 0;    
    }
}


