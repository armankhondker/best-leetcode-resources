// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

// Note:

// The number of elements initialized in nums1 and nums2 are m and n respectively.
// You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
// Example:

// Input:
// nums1 = [1,2,3,0,0,0], m = 3
// nums2 = [2,5,6],       n = 3

// Output: [1,2,2,3,5,6]


OPTIMAL TIME, BUT EXTRA space

1. Create copy of nums1 array
2. use two pointer approach, where you compare elements, and append smalelr to nums1


//TC: O(n + m) , only one loop where you will loop depending on the longer between n/m
//SC: O(n) where m is length of nums 1, because we need copy array!!

class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Make a copy of nums1.
    int [] nums1_copy = new int[m];
    System.arraycopy(nums1, 0, nums1_copy, 0, m);

    // Two get pointers for nums1_copy and nums2.
    int p1 = 0;
    int p2 = 0;

    // Set pointer for nums1
    int p = 0;

    // Compare elements from nums1_copy and nums2
    // and add the smallest one into nums1.
    while ((p1 < m) && (p2 < n))
      nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

    // if there are still elements to add
    if (p1 < m)
      System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
    if (p2 < n)
      System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
  }
}



BEST SOLUTION, eliminate extra space, by starting from the end of arrays!!!

//TC: O(m +n)
//SC: O(1)

What if we start to overwrite nums1 from the end, where there is no information yet? 
Then no additional space is needed. (SINCE ENDING OF ARRAY IS A BUNCH OF ZEROS)

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;  //last index of nums1that has valid number
        int i2 = n - 1; // last index of nums2
        int lastIndex = m + n - 1; //last index of nums array
        while(i1 >= 0 && i2 >= 0){
            if(nums1[i1] > nums2[i2]){ //compare two numbers
                //if nums1[i1] is bigger, then place it in the last index in nums1
                nums1[lastIndex] = nums1[i1];
                i1 --;
            } else {
                nums1[lastIndex] = nums2[i2];
                i2 --;
            }
            lastIndex --;
        }
        // if i1 is greater than 0 but i2 is not, we don't need to do anything becuase it's a sorted array.
       // However, if i2 is greater than 0, this means the rest of spot is only n2.
        while( i2 >= 0){
            nums1[lastIndex] = nums2[i2];
            lastIndex --;
            i2 --;
        }
    }
}


