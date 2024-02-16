// Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
// Find all unique triplets in the array which gives the sum of zero.

//Intution is to fix the element we are on, and perform TWO SUM on remaining of array to see if we get 0-nums[i],
//WE DO TWO SUM USING TWO POINTERS, 
//if we do, then have found a pair i,j,k which add up to 0. 

//TC: O(n^2)
//SC: O(n) for memory required for sorting, DONT COUNT O(n^2) space for the output 

1. SORT THE INPUT Array
2. FOR LOOP, where you PIN one element, and implement logic to avoid duplicates
3. USE TWO POINTERS, with low and high, to try and find elements that equal, the target which is 0 - pinVALUE
4. If we find a pair append to res, then return 

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(num); //sort the array so that we can use two pointer with two sum  
    List<List<Integer>> res = new LinkedList<>(); 
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) { //to not get duplicates, we have a good element where we can try to find a pair
            int lo = i+1, hi = num.length-1,   //MOVE THESE BOUNDS INWARDS
           int sum = 0 - num[i]; //this is the value we are trying to get with two sum on rest of array
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++; //increment here to avoid duplicates
                    while (lo < hi && num[hi] == num[hi-1]) hi--; //increment here to avoid dupliactes 
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++; //we need to increment low
                else {hi--;} //increment hi
           }
        }
    }
    return res;
    }
}