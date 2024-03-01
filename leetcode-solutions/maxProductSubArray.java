Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

SIMILAR TO KADENEs algorithm!!
Keep track of maxSeenSoFar

In this case, will need to keep track of minProduct to account for negative sums

//TC:O(n)
//SC: O(1)

class Solution {
public static int maxProduct(int[] nums) {
    if (nums.length == 0) return 0;
    int maxEndHere = nums[0];
    int minEndHere = nums[0];
    int maxSoFar = nums[0];

    for (int i = 1; i < nums.length; i++) {
        int num = nums[i];
        if (num >= 0) {
            maxEndHere = Math.max(maxEndHere * num, num);
            minEndHere = Math.min(minEndHere * num, num);
        } else { // we have negative!! , so our max endinghere will depend on previous min 
            int temp = maxEndHere;
            maxEndHere = Math.max(minEndHere * num, num);
            minEndHere = Math.min(temp * num, num); //we want minEndHere to be as negative as possible so multiply
            										//by the previous maxEndHere, since we are at negative number 
        }
        maxSoFar = Math.max(maxEndHere, maxSoFar);
    }
    return maxSoFar;
}
}