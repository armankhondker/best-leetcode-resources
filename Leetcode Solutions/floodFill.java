An image is represented by a 2-D array of integers, each integer representing the pixel value of the image 
(from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, 
and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally 
to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally 
to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the
 aforementioned pixels with the newColor.

At the end, return the modified image.


//Intution, use DFS
TC: O(M*N) because in worst case we will process every pixel in grid
SC:O(M*N) because our stack space can grow to be the whole board

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) return image; //no work to be done
        fill(image, sr, sc, image[sr][sc], newColor);
        return image; 
    }
    
    public void fill(int[][] image, int i, int j, int currColor, int newColor)
    {
        if(i<0 || i>=image.length || j<0 || j>=image[0].length) return; //check bounds
        if(image[i][j] != currColor) return; //dont need to change this cell 
        
        image[i][j] = newColor; //update color 
        fill(image, i+1, j, currColor, newColor);
          fill(image, i-1, j, currColor, newColor);
          fill(image, i, j+1, currColor, newColor);
          fill(image, i, j-1, currColor, newColor);
    }
}