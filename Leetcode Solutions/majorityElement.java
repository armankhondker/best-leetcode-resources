Given an array of size n, find the majority element. The majority element is the 
element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.


//MULTIPLE WAYS TO SOLVE

//1. Sort the array and then return the element in the middle! clever solution that uses no space, however,
//we can improve on time complexity 


//O(logn)
//O(1)
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

//2. HashMap to store counts!


class Solution {
   public int majorityElement(int[] nums) {
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>(); //store count of every element
    int ret=0;
    for (int num: nums) {
        if (!myMap.containsKey(num))   //can also use getOrDefault to avoid this if statemenet 
            myMap.put(num, 1);
        else
            myMap.put(num, myMap.get(num)+1);
        if (myMap.get(num)>nums.length/2) {
            ret = num;
            break;
        }
    }
    return ret;
}
}