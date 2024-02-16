// Given a sorted array A of unique numbers, find the K-th missing number starting from 
// the leftmost number of the array.



BRUTE FORCE
TC: O(N)
SC: O(1)

class Solution {
  // Return how many numbers are missing until nums[idx]
  int missing(int idx, int[] nums) {
    return nums[idx] - nums[0] - idx;
  }

  public int missingElement(int[] nums, int k) {
    int n = nums.length;
    // If kth missing number is larger than 
    // the last element of the array
    if (k > missing(n - 1, nums))
      return nums[n - 1] + k - missing(n - 1, nums);

    int idx = 1;
    // find idx such that 
    // missing(idx - 1) < k <= missing(idx)
    while (missing(idx, nums) < k) idx++;

    // kth missing number is greater than nums[idx - 1]
    // and less than nums[idx]
    return nums[idx - 1] + k - missing(idx - 1, nums);
  }
}


OPTIMAL - BINARY SEARCH SOLUTION

//TC: (O(logn)) to perform binary search since sorted array 
//SC: O(1)


EXPLAINATION
Let missingNum be amount of missing number in the array. Two cases that need to be handled:

missingNum < k, then return nums[n - 1] + k - missingNum
missingNum >= k, then use binary search(during the search k will be updated) to find the index in the array, 
where the kth missing number will be located in (nums[index], nums[index + 1]), return nums[index] + k

# of missing elemnts at index i ========= nums[i] - nums[0] - i!!!

class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int h = n - 1;
        int missingNum = nums[n - 1] - nums[0] - (n-1); //missing numbers at last positin
        
        if (k > missingNum) { //the missing number is past our array vaks
            return nums[n - 1] + k - missingNum;
        }
        
        while (l < h - 1) {
            int m = l + (h - l) / 2;
            int missing = nums[m] - nums[l] - (m - l);
            
            if (missing >= k) {
			    // when the number is larger than k, then the index won't be located in (m, h]
                h = m;
            } else {
			    // when the number is smaller than k, then the index won't be located in [l, m), update k -= missing
                k -= missing;
                l = m;
            }
        }
        
        return nums[l] + k;
    }
}