// A peak element is an element that is greater than its neighbors.

// Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

// The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

// You may imagine that nums[-1] = nums[n] = -∞.

// Example 1:

// Input: nums = [1,2,3,1]
// Output: 2
// Explanation: 3 is a peak element and your function should return the index number 2.
// Example 2:

// Input: nums = [1,2,1,3,5,6,4]
// Output: 1 or 5 
// Explanation: Your function can return either index number 1 where the peak element is 2, 
//              or index number 5 where the peak element is 6.


// NAIVE APPROACH, linear scan of array and check previous and next values for peak
//TC: O(N)
//SC: O(1)



BEST SOLUTION, use a binary search on array

// The reason binary search will always return if there is a peak is because:
// 1. when we check a direction, a peak element will for sure exist because it will either
// be the last element in the direction, or there will be a dip and be that element 

HOW IT WORKS:
1. We calculate mid

2. if MID lies in the DESCENDING portion of the array (found by comparing neighbor), 
then a peak must be LEFT of it

3. if mid lies in ASCENDING portion of the array (found by comparing neighbor)
, then peak must be RIGHT of it 

4. at end of binary search, left and right pointers will meet, and we will be at a peak element 

//TC: O(logn)
//SC: O(1)
public int findPeakElement(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] < nums[mid + 1]) { //check the right half of the subarray for a peak
            left = mid + 1;
        } else {
            right = mid;  //check the left half of subarray for a peak
        }
    }
    return left;
}


RECURSIVE VERSION, SAME LOGIC AND TIME COMPLEXITY

public class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}