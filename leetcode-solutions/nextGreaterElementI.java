// You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of 
// nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

// The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
// If it does not exist, output -1 for this number.

//Intution, use a hashmap to store all the values and their next greater element 
//Use a stack to store all elements until we reach an item that is bigger than item in stack

// Time complexity : O(m+n). The entire numsnums array(of size nn) is scanned only once. 
// The stack's n elements are popped only once. The findNumsfindNums array is also scanned only once.

// Space complexity : O(m+n). stackstack and mapmap of size nn is used. resres array of size m
// is used, where nn refers to the length of the numsnums array and mm refers to the length of the
// findNumsfindNums array.




class Solution {
    public int[] nextGreaterElement(int[] nums, int[] findNums) {
        Stack < Integer > stack = new Stack < > ();
        HashMap < Integer, Integer > map = new HashMap < > (); //hashmap to store the next greater elemenet for every 
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] > stack.peek()) //we have found next greater element, want this to be while loop,
                                                                //just incase we have a long sequence of decreasing numbers 
            {
                map.put(stack.pop(), nums[i]);
            }
            stack.push(nums[i]);  
        }
        while (!stack.empty())
            map.put(stack.pop(), -1);   //all of these dont have a next greater element 
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }
}