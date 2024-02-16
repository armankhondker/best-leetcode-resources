Given an array consists of non-negative integers, your task is to count the number of triplets 
chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Brute force, check every single pair of triplets 
TC: O(n^3)
SC: O(1)

class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        for(int i=0; i<nums.length-2; i++){
            for(int j=i+1; j<nums.length-1; j++){
                for(int k=j+1; k<nums.length; k++){
                    if(nums[i] + nums[j] > nums[k] && nums[j] + nums[k] > nums[i] && nums[i] + nums[k] > nums[j]){
                        count++; 
                    }
                }
            }
        }
        return count; 
    }
}

//TC: O(n^2logn) to go through all i,j pairs, and O(logn) time to search for k value 
//SC: O(logn) sorting takes up O(logn) space

1. sort the elements
2. do a binary search for the upper limit

public class Solution {
    int binarySearch(int nums[], int l, int r, int x) {
        while (r >= l && r < nums.length) {
            int mid = (l + r) / 2;
            if (nums[mid] >= x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]);
                count += k - j - 1; //this is number of triplets before this k value that we need to add
            }
        }
        return count;
    }
}

SAME CONCEPT, linear scan instead of binary search
3 pointers!

TC: O(n^2) to go through i,j pairs 
SC: O(logn) for space to sort 

public class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) //this will not iterate
                											//over all elements each time, THEREFORE NOT O(n^3) 
                    k++;
                count += k - j - 1;
            }
        }
        return count;
    }
}


