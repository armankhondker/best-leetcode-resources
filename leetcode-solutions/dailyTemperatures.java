// Given a list of daily temperatures T, return a list such that, for each day in the input, 
// tells you how many days you would have to wait until a warmer temperature. If there is no future day 
// for which this is possible, put 0 instead.

// For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output 
// should be [1, 1, 4, 2, 1, 1, 0, 0].

//TC: O(n) to go through temperatures 
//SC: O(n) for stack 
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stack = new Stack<>();
    int[] ret = new int[temperatures.length];
    for(int i = 0; i < temperatures.length; i++) {
        while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            int idx = stack.pop();
            ret[idx] = i - idx;
        }
        stack.push(i);
    }
    return ret;
}
}