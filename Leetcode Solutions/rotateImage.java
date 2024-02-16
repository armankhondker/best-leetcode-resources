
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]


// The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

// 1  2  3             
// 4  5  6
// 7  8  9
// after transpose, it will be swap(matrix[i][j], matrix[j][i])

// 1  4  7
// 2  5  8
// 3  6  9
// Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

// 7  4  1
// 8  5  2
// 9  6  3

//So bascically,
//1. transpose matrix
//2. go through each row with two pointers swapping just like normal two pointer approach 

//TC: O(n^2)
//SC: O(1) space 

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length; //only need to use this since NxN matrix
        for(int i=0; i<n; i++)
        {
            for(int j=i; j<n;j++)
            {
                //TRANSPOSE - basically swaps on the diagonal (2 with 4) (8 with 6) (7 with 3)
                int temp = matrix[i][j];
                matrix[i][j] = matrix [j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i=0; i<n; i++)
        {
            for (int j=0; j<n/2;j++)  //only need to go halfway because two pointers compares up to the middle 
            {
                int temp = matrix[i][j]; 
                matrix[i][j] = matrix[i][n-1-j];   //swap first and last elements 
                matrix[i][n-1-j] = temp; 
            }
        }
    }
    }
}