Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?



UNOPTIMAL SPACE Solution

Iterate through the matrix on first iteration and populate a boolean arrays
containing true or false if the row/column has a zero in the row

Then iterate on a second time, and if either the row/col of the current element
has a true value in the boolean array, then set that element to 0

TC: O(MxN) to iterate through the entire matrix of MxN dimmesions
SC: O(M+N) to store the arrays of m and n length to store row status


class Solution {
    public void setZeroes(int[][] matrix) {
        
        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];
        
          
        for(int i=0; i<matrix.length; i++){
            for(int j=0;j<matrix[0].length; j++){
                if(matrix[i][j] == 0){ //set to zero in this scenario 
                    rows[i] = true; //this column contains a zero, so mark entire column as true
                    cols[j] = true; //this column contains a zero, so mark entire column as true 
                }
            }
        }
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0;j<matrix[0].length; j++){
                if(rows[i] == true || cols[j] == true){ //set to zero in this scenario 
                     matrix[i][j] =0;
                }
            }
        }
        
        return; 
    }
}