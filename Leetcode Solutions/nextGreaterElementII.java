// Given a circular array (the next element of the last element is the first element of the array), print the 
// Next Greater Number for every element. The Next Greater Number of a number x is the first greater number 
// to its traversing-order next in the array, which means you could search circularly to find its next greater number. 
// If it doesn't exist, output -1 for this number.


// Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2; 
// The number 2 can't find next greater number; 
// The second 1's next greater number needs to search circularly, which is also 2.

//SAME as next great I, except we only have one array and it is a circular array 

//Idea, we NEED to loop through the array twice!

// The approach is same as Next Greater Element I
// See explanation in my solution to the previous problem
// The only difference here is that we use stack to keep the indexes of the decreasing subsequence



// Time complexity : O(n). Only two traversals of the numsnums array are done. 
// Further, atmost 2n elements are pushed and popped from the stack.

// Space complexity : O(n). A stack of size nn is used. resres array of size n is used.

   public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n]; 
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return next;
    }