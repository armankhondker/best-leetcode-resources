//Qrite an efficient algorithm that searches for a value in an m x n matrix.
//This matrix has the following properties:

// 1. Integers in each row are sorted in ascending from left to right.
// 2. Integers in each column are sorted in ascending from top to bottom.

//TC: O(M+N) because the farthest you can search left/right is number of columns and farthest you can 
//search top/bottom is number of rows 
//SC: O(1) no memory needed
//Intuiton can potentially reduce this to have lgn in the runtime, but probably really complicated 

//example_matrix = [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]

//Initial thought, IF I GO FROM right to left in matrix, I increased value , left to right I decrease in value
//IF I GO FROM top to bottom, I increase value, bottom to top I decrease in value 

//we want to progressively lower our search space.
//IF WE START AT 
//1. top left and bottom right corners, it doesnt allow us to do this because if we go either direction we will only increase/decrease in value
//2 bottom left and top right corners are GOOD! Allow us to either increase/decerase in value, hence making our decision
//space smaller 


//start search at TOP RIGHT CORNER (could also be bottom right corner)
//if the target is greater than the value in current position, then the target can not be in entire row of current position
// because the row is sorted, if the target is less than the value
//in current position, then the target can not in the entire column because the column is sorted too.
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;   //start search at TOP RIGHT CORNER
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
        
    }
}