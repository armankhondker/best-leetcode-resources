//Write an efficient algorithm that searches for a value in an m x n matrix. 
//This matrix has the following properties:

// 1. Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.

// example_matrix = [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]



//HAS TO BE logarithmic time algorithm because of the sorted property. Must have log in asnwer 
//Initial thought, perform binary search on each row and search for target O(mlog(n)) where m is rows and n is colums
//IF you can convert this 2d matrix into a 1D matrix you can find the target in O(logmn) time instead

 //key insight, represent the matrix as an array with indexes, element at position idx is in...... 
    //row = idx//n (integer divison) , where n is number of columns!!!
    //col = idx%n  , where n is number of columns in matrix!!\
//  WE know where the row/col of an item by using this math. WRAP AROUND % to find col

    
    //MxN matrix , M rows, N columns 
    
    //TC: O(logn + logm) == O(log(mn)) to perform the binary search on rows and columns 
    //SC: O(1) constant space 
class Solution {
    
   
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int m = matrix.length; 
        if(m==0) return false;
        int n=matrix[0].length;
        
        int left = 0;
        int right = m*n -1; 
        int pivotIdx;
        int pivotElement; 
        while(left <= right){
            pivotIdx = (left+right)/2;
            pivotElement = matrix[pivotIdx/n][pivotIdx%n];
            if(target == pivotElement) {
                return true; 
            } 
            else if (target < pivotElement){
                right = pivotIdx -1; 
                
            } else {
                left = pivotIdx +1;
            }
        }
        return false; 
        
    }
}