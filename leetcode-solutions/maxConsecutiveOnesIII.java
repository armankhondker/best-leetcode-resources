// Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

// Return the length of the longest (contiguous) subarray that contains only 1s. 

 

// Example 1:

// Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
// Output: 6
// Explanation: 
// [1,1,1,0,0,1,1,1,1,1,1]
// Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
// Example 2:

// Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
// Output: 10
// Explanation: 
// [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
// Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


INTUTION, USE SLIDING WINDOW with two pointers 

since we are trying to find the max!

Note, since we are keeping track of max, no need to ever shrink the window!!!

//TC: O(N)
//SC: O(1)

class Solution {
  public int longestOnes(int[] A, int K) {
    int left = 0, right;
    for (right = 0; right < A.length; right++) {
      // If we included a zero in the window we reduce the value of K.
      // Since K is the maximum zeros allowed in a window.
      if (A[right] == 0) K--;
      // A negative K denotes we have consumed all allowed flips and window has
      // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
      if (K < 0) {
        // If the left element to be thrown out is zero we increase K.
        if (A[left] == 0) K++;
        left++;
      }
    }
    return right - left; //at the end, this will be our max window size
  }
}