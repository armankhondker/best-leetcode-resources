
//Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S 
//and S < K. If no i, j exist satisfying this equation, return -1.

// Input: A = [34,23,1,24,75,33,54,8], K = 60
// Output: 58
// Explanation: 
// We can use 34 and 24 to sum 58 which is less than 60.


//TC: O(nlogn)
//SC: O(1)

//1 sort the array; 

class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A); // Time cost O(nlogn).
        int max = -1;, i = 0, j = A.length - 1; 
        while (i < j) {
            int sum = A[i] + A[j];
            if (sum < K) { // find one candidate.
                max = Math.max(max, sum);
                ++i; // increase the smaller element.
            }else { // >= sum.
                --j; // decrease the bigger element.
            }
        }
        return max;
        
    }
}