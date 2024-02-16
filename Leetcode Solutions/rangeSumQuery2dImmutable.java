Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left 
corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), 
which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12



BRUTE FORCE 

//TC: O(mn) to compute each range sum
//SC: O(1)

class NumMatrix {

private int[][] data;

public NumMatrix(int[][] matrix) {
    data = matrix;
}

public int sumRegion(int row1, int col1, int row2, int col2) {
    int sum = 0;
    for (int r = row1; r <= row2; r++) {
        for (int c = col1; c <= col2; c++) {
            sum += data[r][c];
        }
    }
    return sum;
}
}



DP SOLUTION


//TC: O(m) time per query, O(mn) time pre-computation.
The pre-computation in the constructor takes O(mn)time. The sumRegion query takes O(m) time.

//SC : O(mn). The algorithm uses O(mn) space to store the cumulative sum of all rows.

class NumMatrix {

private int[][] dp;

public NumMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    dp = new int[matrix.length][matrix[0].length + 1];
    for (int r = 0; r < matrix.length; r++) {
        for (int c = 0; c < matrix[0].length; c++) {
            dp[r][c + 1] = dp[r][c] + matrix[r][c];
        }
    }
}

public int sumRegion(int row1, int col1, int row2, int col2) {
    int sum = 0;
    for (int row = row1; row <= row2; row++) {
        sum += dp[row][col2 + 1] - dp[row][col1];
    }
    return sum;
}
}

//BEST SOLUTION - DP with math!1

Time complexity : O(1) time per query, O(mn)O time pre-computation. The pre-computation in the constructor takes O(mn)O(mn) time. Each sumRegion query takes O(1)O(1) time.

Space complexity : O(mn). The algorithm uses O(mn) space to store the cumulative region sum.


class NumMatrix {

private int[][] dp; //representing the sum from the origin to each cell!!!

public NumMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    dp = new int[matrix.length + 1][matrix[0].length + 1];  //need to allocate 1 extra space for bounds,
                                                            //ie 0th row, and 0th col will be filled with 0s
//dp[i][j] ====== area of square at dp[i+1][j+1]
    for (int r = 0; r < matrix.length; r++) {
        for (int c = 0; c < matrix[0].length; c++) {
            dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];   //subtract out portion counted twice
        }
    }
}

}

public int sumRegion(int row1, int col1, int row2, int col2) {
    return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1]; //add back portion counted twice 
}