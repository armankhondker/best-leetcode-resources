// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.


// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// Output: [1,2,3,6,9,8,7,4,5]


//O(n) where N is the total amount of elements in the array, could also be O(NM) where n is row and m is cols 
//O(n) to store result of size N where N is total amount of elements in array, could also be O(NM)

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length ==0) return res; 
        
        int rowBegin =0;
        int rowEnd = matrix.length -1;   //row boundaries and column boundaries
        int columnBegin = 0;
        int columnEnd = matrix[0].length-1; 
        
        while(rowBegin <= rowEnd && columnBegin <= columnEnd)
        {
            
          // Traverse Right
        for(int i=columnBegin; i<=columnEnd; i++) //traverse first row but  access column indexes
           {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++; // we have traversed the entire row now;
            
            
             // Traverse Down
            for(int i=rowBegin; i<= rowEnd; i++)
            {
                res.add(matrix[i][columnEnd]);
            }
            columnEnd--;
            
            
            
            if(rowBegin <= rowEnd) //neeed to check our bounds because 
            {
                     // Traverse Left
                for(int i=columnEnd; i>=columnBegin; i--)
                {
                    res.add(matrix[rowEnd][i]);
                }
            }
            
            rowEnd--;
            
            if(columnBegin<= columnEnd) // need to check our bounds 
            {
                //Traverse Up 
                for(int i=rowEnd; i>=rowBegin; i--)
                {
                    res.add(matrix[i][columnBegin]);
                }
            }
            columnBegin++;
        }
        return res; 
            
        
    }
}