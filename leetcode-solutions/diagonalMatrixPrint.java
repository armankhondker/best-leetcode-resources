Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in 
diagonal order as shown in the below image. 

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

INTUITION,

go along to diaonals and incrememnt but i-1, j+1

to do in reverse order, store all elements in arraylist and then reverse

REGULAR DIAONGAL MATRIX
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int m= matrix.length;
        int n= matrix[0].length;
        int index =0;
        int[] res = new int[m*n];
        for(int k=0; k<=m-1; k++){
            
            int i=k;  // to print all the diagonals starting on left row
            int j=0; //starts on first col always
            while(i>=0){ //go until we reach the upper bord
                res[index++]=matrix[i][j];
                i=i-1;
                j=j+1;
            }
        } 
        for(int k=1; k<=n-1; k++){ //HAVE TO START FROM K=1 to skip over the diagonl that gets counted twice
            int i = m-1; //starts on last rol always
            int j= k; //to print all diagonals starting at bottom row 
            while(j<=n-1){  //go until we reach the right border
                res[index++]=matrix[i][j];
                i=i-1;
                j=j+1;
            }
        }
        return res; 
    }
}




SAME AS DIAOGONAL, but if the diagonl we are on is odd, we will reverse all the elemetnts,
THEN add to result 

//TC: O(N*M) to go thorugh the entire matrix, O(K) to clear the matrix 
//SC: O(min(N,M)) to store elements in arraylist
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length;
        int n = matrix[0].length;
 
        int[] res = new int[m*n];
        int resIndex = 0;
        
        ArrayList<Integer> temp = new ArrayList<>(); 

        
        for(int k=0; k<m; k++){
            temp.clear(); //clear out this temp array
            int i = k;
            int j =0;
            while(i>=0){
                temp.add(matrix[i][j]); //copy this elemeent 
                i= i-1;
                j=j+1;
            }
            if(k%2 == 1){
                Collections.reverse(temp);
            }
            for(int x: temp){
                res[resIndex++] = x;
            }
        }
        
        for(int k=1; k<n; k++){  //NOTE, need to go from k=1 to skip of, BIGGEST DIAGONAL counted twice 
             temp.clear(); //clear out this temp array
        
            int i = m-1; 
            int j = k; 
            while(j< n){
                temp.add((matrix[i][j]));
                i=i-1;
                j=j+1; 
            }
            if(k%2 == 1){
                Collections.reverse(temp);
            }
            for(int x: temp){
                res[resIndex++] = x;
            }
        }
        return res; 
    }
}


// If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
// if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
// if out of top border (row < 0) then row = 0; change walk direction.
// if out of left border (col < 0) then col = 0; change walk direction.
// Otherwise, just go along with the current direction.

BEST SOLUTION
//O(M*N)
//O(1) constant space solutioN!!!!
public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;  //first direction we go is upright
        int[][] dirs = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            
            if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) { col = n - 1; row += 2; d = 1 - d;}  //out on right botder
            if (row < 0)  { row = 0; d = 1 - d;} //out on top border, reset
            if (col < 0)  { col = 0; d = 1 - d;} // out on left border, reset col
        }
        
        return result;
    }
}