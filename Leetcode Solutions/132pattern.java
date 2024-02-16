Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k 
and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 
pattern in the list.
 
// Time complexity : O(n). We travesre over the numsnums array of size nn once to fill the minmin array. After this, 
// we traverse over numsnums to find the nums[k]nums[k]. During this process, we also push and pop the elements 
// on the stackstack. But, we can note that atmost nn elements can be pushed and popped off the stackstack in total. 
// Thus, the second traversal requires only O(n) time.

// Space complexity : O(n). The stackstack can grow upto a maximum depth of nn. Furhter, minmin array of size n is used.

class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;
        Stack < Integer > stack = new Stack < > ();
        int[] min = new int[nums.length];  //array to store the minimum elements
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) 
            min[i] = Math.min(min[i - 1], nums[i]); //populate min array

        //fix j 
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) { //we have found an i, j pair!
                while (!stack.isEmpty() && stack.peek() <= min[j]) //pop elements of stack that arent greater than min[j] or the k element
                    stack.pop();
                if (!stack.isEmpty() && stack.peek() < nums[j]) //we have found i,j,k pair 
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }
}