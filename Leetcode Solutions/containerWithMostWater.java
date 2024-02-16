// Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are
//  drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which 
//  together with x-axis forms a container, such that the container contains the most water


//brute force, just consider every possible pair and calcuate area and set it to max
//

//ONLY the smaller building constrains the area, not the large, so you move the smaller one 

//ONE PASS SOLUTION
//TC: O(n)
//SC: O(1)

class Solution {
    public int maxArea(int[] height) {
        
        int max =0;
        int left = 0;
        int right = height.length-1;
        while(left<right)
        {
            int min = Math.min(height[left], height[right]); //set min of two elements
            max = Math.max(max, min*(right-left));  //update max by calculating area
            if(height[left]<height[right]) //only want to move the pointer that is smaller
            {
                left++;
            }
            else{
                right--;
            }
        }
        return max; 
        
    }
}