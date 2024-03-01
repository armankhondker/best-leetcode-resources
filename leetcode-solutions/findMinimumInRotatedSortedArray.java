Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0



KEY INSIGHT, want to find the element where its previous element is bigger,
THAT WILL BE THE minimum,
Can easily do this in O(N) time, but is trival ,need to improve to O(logn) time

USE BINARY SEARCH O(logn)

NOTE, the answer will be in the UNSORTED portion of the array,
so our way to reduce the search space, is to check if a portion of the array is unsorted.


 class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 0) return -1; //0 element in array
        if(nums.length ==1) return nums[0];  //1 elements in array 
        
        int low = 0;
        int high = nums.length-1; 

        while(low < high){
            int mid = low + (high -low)/2;
            if(mid>0 && nums[mid]<nums[mid-1]) 
                return nums[mid]; //WE HAVE FOUND, also check for >0 for index outofbounds
            else if (nums[low] <= nums[mid] && nums[mid] > nums[high]) {
                low = mid+1;  //LEFT IS IN SORTED ORDER, so dont worry about it, exclude it from search space
            }else{
                 high = mid-1; //HIGH IS SORTED, so exlucde from search space 
            }
        }
        return nums[low];  //ITERATION WILL END, where nums[low] is element we are looking for
        
    }
}