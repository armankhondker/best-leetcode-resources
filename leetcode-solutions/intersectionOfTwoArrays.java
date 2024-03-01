// Given two arrays, write a function to compute their intersection.

// Example 1:

// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]
// Example 2:

// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]
// Note:

// Each element in the result must be unique.
// The result can be in any order.
 

//SOLUTION use two hashsets!!

//TC: O(n + m)
//SC:  O(n + m) space for the arrays

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>(); //store all unique elements in nums1
        for(int num: nums1)
        {
            set.add(num);
        }
        HashSet<Integer> intersection = new HashSet<Integer>();
        for(int num: nums2)
        {
            if(set.contains(num))
            {
                intersection.add(num);
            }
        }
        
        int[] res = new int[intersection.size()];
        int index = 0;
        for(int i: intersection)
        {
            res[index++] = i; 
        }
        return res; 
    }
}


//FOLLOW UP:
//Assume that the arrays are sorted, how could you do in O(N) time and O(1) space
//ANSWER: Use two pointer approach, and if the elements are equal add to res, else increment either i or j 

public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // assume sorted
        Arrays.sort(nums2); // assume sorted
        
        int[] intersections = new int[nums1.length];
        
        int i1 = 0, i2 = 0, j=0;
        while (i1<nums1.length && i2<nums2.length) {
            int val1 = nums1[i1];
            int val2 = nums2[i2];
            
            if (val1 == val2) {
                intersections[j++]=val1;
                while (i1<nums1.length && val1 == nums1[i1]) i1++;
                while (i2<nums2.length && val2 == nums2[i2]) i2++;
            }
            if (val2 > val1) 
                while(i1<nums1.length && val1 == nums1[i1]) i1++;
            else 
                while(i2<nums2.length && val2 == nums2[i2]) i2++;

        }
        return Arrays.copyOf(intersections,j);
}