Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10]



//TC: O(N) where N is number of intervals, to go through the intervals array 
//SC: O(N) to store all the intervals

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Arrays.sort(intervals, (a,b)-> a[0] - b[0]);
        ArrayList<int[]> res = new ArrayList<>(); 
    
        for(int [] interval : intervals){
            if(newInterval == null || interval[1] < newInterval[0]){ //curr interval is WAYYY before, jus add to res
                res.add(interval);
            } else if(newInterval[1] < interval[0]) { //curr interval is WAYYY after
                res.add(newInterval);
                newInterval = null; // set null so we can just add the rest of the elements after this
                res.add(interval);
            } else { //we have some sort of intersection so update newInterval!!!
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]); 
            }
        }
        
        if(newInterval != null){  //newInterval will be at very end in this edgecase  //need to simply add it to end and return 
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][]); //simply return the intervals which have been merged 
    }
}