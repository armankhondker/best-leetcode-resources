You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10
 

Note: Your solution should run in O(log n) time and O(1) space.


The key observation to make is that the starting array must always have an odd number of elements 
(be odd-lengthed), because it has one element appearing once, and all the other elements appearing twice.

he subarray containing the single element must be odd-lengthed. The subarray not containing 
it must be even-lengthed. So by taking a pair out of the middle and then calculating which 
side is now odd-lengthed, we have the information needed for binary search.


tricky part is figuring out how to increment low and hi, there are four cases based on the values

TC:O(logn)
SC:O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0; //calucate length of ....
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo]; //low will end on the singled out value
    }
}