Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.


Optimal time and space, but operations may be too many, EX: array with a lot of 0s

//TC: O(n) to go throguh all elements in the array 
//SC: O(1)

class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0; 
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i]!=0)
            {
                nums[index] = nums[i];
                index++;
            }
        }
        
        for(int i=index; i<nums.length; i++)
        {
            nums[i] = 0;
        }
    }
}


MOST OPTIMAL SOLUTION 

//TC: O(N)
 class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=0){
                swap(lastNonZeroFoundAt, i, nums); 
                lastNonZeroFoundAt++;
            }
        }
    }
    
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp; 
    }
}