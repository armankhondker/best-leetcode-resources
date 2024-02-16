// Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


// In Pascal's triangle, each number is the sum of the two numbers directly above it.

// Example:

// Input: 5
// Output:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]


USE dynamic programming to construct the tree,
each row relies on previous row adjacent elements 

TC: O(N^2) where n is numRows, because out loop goes for N times, and inner loop can go up to N times also
SC: O(N^2) because we need to store each row of the triangle in the 2d result array 

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows == 0) return triangle; 
        
        
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1); //first row is always 1;
        
        for(int rowNum=1; rowNum<numRows; rowNum++){
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1); 
            
            row.add(1); //first elemnet always 1
            
            for(int j=1; j<rowNum; j++){
                row.add(prevRow.get(j-1)+prevRow.get(j)); //similar to dp 
            }
            
            row.add(1); //last elements always 1
            
            triangle.add(row); //row is finished processing 
            
        }   
        return triangle;
        
    }
}