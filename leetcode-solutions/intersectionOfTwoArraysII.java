Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]



//TC: O(n + m) to loop through the arrays 
//SC: O(n + m) to store hashmap that will get as big as nums1

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> res = new ArrayList<>(); 
        
        HashMap<Integer, Integer> hm = new HashMap<>(); 
        for(int num: nums1)
        {
            hm.put(num, hm.getOrDefault(num, 0) +1);
        }
        
        
        for(int num : nums2)
        {
            if(hm.containsKey(num) && hm.get(num) > 0)
            {
                res.add(num);
                hm.put(num, hm.get(num) -1);
            }
        }
        
       int[] r = new int[res.size()];
       for(int i = 0; i < res.size(); i++)
       {
           r[i] = res.get(i);
       }
        
        
        return r; 
    }
}




SORTING WAY
//TC: O(nlogn + mlogm)
//SC: O(1) because we use the input array to store our output!!!


public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ++i;
        } else if (nums1[i] > nums2[j]) {
            ++j;
        } else {
            nums1[k++] = nums1[i++];
            ++j;
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}