Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.





Question, can be refrased to state, find maximum number of non-overlapping intervals, and how many should 
you remove to get this, 


//KEY INSIGHT!!!! ,use GREEDY algorithm 

sort the intervals by END time!!!!!


algorithm is
1. TAKE EARLIEST FINISH TIME Item

//TC: O(nlogn) to sort the intervals by end time
//SC: O(1) constant space 

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length ==0) return 0; 
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[1],arr2[1])); //O(nlogn), need it to be strted BY END TIME  
        int[] currInterval = intervals[0];
        int end = currInterval[1];
        int count = 1; //how many intervals will be in our scenario with no overlapping intervals, AND max number of intervals that allows                                                                                                                                  this to hold
        for(int [] interval: intervals){
            if(interval[0] >= end) {
                end = interval[1];  //UPDATE NEW END 
                count++;
            }
        }
        return intervals.length - count; //we have max numbver of nonoverlapping now,  # of intervals - # left = 
                                                                                        //min removal 

    }
}