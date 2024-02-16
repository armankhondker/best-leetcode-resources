// Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

// Each element is either an integer, or a list -- whose elements may also be integers or other lists.

// Different from the previous question where weight is increasing from root to leaf, now the weight is defined 
//from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the 
//largest weight.

// Example 1:

// Input: [[1,1],2,[1,1]]
// Output: 8 
// Explanation: Four 1's at depth 1, one 2 at depth 2.
// Example 2:

// Input: [1,[4,[6]]]
// Output: 17 
// Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 


INTUITION, same as nested list expect higher level, will be counted again in each lower level



//TC: O(N) where n is length of list or example, the list [ [[[[1]]]], 2 ] contains 4
// nested lists and 2 nested integers (1 and 2), so N=6 
//CAN ALSO BE THOUGHT OF N^2, just basically all the total elements

//SC: O(D) where D is max level of nesting in input, for the queue!



// Remarkable solution, I honestly can not conceive how anyone would come up with a solution like this.

// For future readers, this is a key fact I used when understanding this algorithm:
// Each integer get added one extra time for the mere existence of each one level under it.

// The concept of weight here is implemented with repeated addition;

public int depthSumInverse(List<NestedInteger> nestedList) {
   Queue<NestedInteger> q = new LinkedList(nestedList); //add all head nodes
   int prevSum = 0, total = 0;   //need a preSum, because each node at top level, effecitvely gets 
   								//added to each level under it, READ BOTTOM
   while(!q.isEmpty()){
       int size = q.size();
       for(int i = 0; i < size; ++i){
           NestedInteger ni = q.poll();
           if(ni.isInteger()){
               prevSum += ni.getInteger();
           }else{
               q.addAll(ni.getList());
           }
       }
       total += prevSum;
   }
   return total;
}

