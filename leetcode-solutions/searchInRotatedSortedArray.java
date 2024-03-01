Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

INTUTION, need to find the pivot point, once we find, we can use that, to determine, if we 
want to search the sorted LEFT HALF or the sorted RIGHT HALF, then do reuglar binary seaarch

1. Find rotation index //(in our implementation low will be set to this index at end of first binary search)
2. Set bounds of left and right depending on target, so we search correct half
3. Do regular binary search with new bounds 

//TC: O(logn) to perform binary search
//SC: O(1) constant space
class Solution {
public int search(int[] nums, int target) {
       if (nums == null || nums.length == 0) return -1;
        
       int left = 0, right = nums.length-1;
        int start = 0;
        //1. find index of the smallest element
        while(left < right) {
            int mid = left + (right-left)/2;
            if (nums[mid] > nums[right]) {
                left = mid +1;
            } else right = mid;
        }  //at end of this search, our left pointer, will be the rotation element
        
     //2. figure out in which side our target lis

        start = left;  //set it equal to rotation index
        left = 0;
        right = nums.length-1;
        if (target >= nums[start] && target <= nums[right])  //set our bounds, depending on if target is in 
        													//right or left sorted halves of our array 
            left = start;
        else right = start;
        
     //3. Run normal binary search in sorted half.
        while(left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) right = mid-1;
            else left = mid + 1;
        }
        
        return -1;
    }
}

NICER SOLUTION, WITH HELPER FUNCTION EXTRACTED OUT 
TC: O(logn) for binary searches 
SC: O(1)
 public int search(int[] nums, int target) {
        int rotation = rotationIndex(nums);
        int start =0;
        int end = nums.length-1;
        if(target >= nums[rotation] && target <=nums[end]){  //select which half of the array we want to search 
            start=rotation;
        } else {
            end = rotation;
        }
        //3. Regular binary search with new bounds 
        while(start<=end){
            int mid = start + (end-start)/2; 
            if(nums[mid] == target){
                return mid;
            } else if (nums[mid]<target){
                start=mid+1;
            } else{
                end=mid-1;
            }
        }
        return -1; 
    }


public int findMinIdx(int[] nums) {
    int start = 0, end = nums.length - 1;
    while (start < end) {
        int mid = start + (end -  start) / 2;
        if (nums[mid] > nums[end]) start = mid + 1;
        else end = mid;
    }
	return start;
}