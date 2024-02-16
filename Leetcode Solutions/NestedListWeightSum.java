// Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

// Each element is either an integer, or a list -- whose elements may also be integers or other lists.

// Example 1:

// Input: [[1,1],2,[1,1]]
// Output: 10 
// Explanation: Four 1's at depth 2, one 2 at depth 1.
// Example 2:

// Input: [1,[4,[6]]]
// Output: 27 
// Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.


USE EITHER BFS OR DFS, and multiply by the level, that we are on! 

//TC: O(N) where n is length of list or example, the list [ [[[[1]]]], 2 ] contains 4
// nested lists and 2 nested integers (1 and 2), so N=6 
//CAN ALSO BE THOUGHT OF N^2, just basically all the total elements

//SC: O(D) where D is max level of nesting in input, for the queue!

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
    if(nestedList == null){
        return 0;
    }
    
    int sum = 0;
    int level = 1;
    
    Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList); //add all the heads of trees
    while(queue.size() > 0){
        int size = queue.size();
        
        for(int i = 0; i < size; i++){
            NestedInteger ni = queue.poll();
            
            if(ni.isInteger()){ //we dont have any more nested list, so just add to sum 
                sum += ni.getInteger() * level;
            }else{
                queue.addAll(ni.getList());
            }
        }
        
        level++;
    }
    
    return sum;
}
}


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */