// Given an array nums of n integers and an integer target, find three integers in nums such that 
// the sum is closest to target. Return the sum of the three integers. You may assume that each input 
// would have exactly one solution.


//TC: O(n^2)
//SC: O(1)

// Similar to 3 Sum problem, use 3 pointers to point current element, next element and the last element. 
// If the sum is less than target, it means we have to add a larger element so next element move to the next.
// If the sum is greater, it means we have to add a smaller element so last element move to the second last element.
// Keep doing this until the end. Each time compare the difference between sum and target, if it is less than
//  minimum difference so far, then replace result with it, otherwise keep iterating.

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = num[0] + num[1] + num[num.length - 1]; //initialize result to some value, dont do this
        // after sorting cuz will effectively get minimum elements 
        Arrays.sort(num); //O(nlogn) time to sort 
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}