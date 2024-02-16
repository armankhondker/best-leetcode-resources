Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].


KEY INSIGHT:
1. need to sort the intervals
2. need to check for overlapping intervals, by having ending time of INTERVAL1 > starting time of INTERVAL2 , then we 
want to have a new interval with maxEnding time between the two INTERVALs 


//O(nlogn) runtime to sort the intervals, where n is number of intervals 
//O(1) space; 

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) return intervals; 
        
        List<int[]> res = new ArrayList<>();
        
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0],arr2[0])); //O(nlogn), need it to be strted

        int[] currentInterval = intervals[0];
        res.add(currentInterval);    //can add intial value because it is int[]
                                    //and if we change it in line 40, it updates in res too 
        
        for(int [] interval : intervals)
        {
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1]; 
            
            if(currentEnd >= nextStart)
            {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
             }
            else
            {        
                currentInterval = interval; 
                res.add(currentInterval);
            }
        }
        
        return res.toArray(new int[res.size()][]);
    }
} 



SAME SOLUTION, but using a minHeap!!! 
USED for follow up question, where we can have an incoming stream of invervals

//TC: O(nlogn) to insert all the intervals into the minheap 
//SC: O(n) to have a heap of size n with all of the elements
class Solution { 
    public int[][] merge(int[][] intervals) {
                if(intervals.length <= 1) return intervals; 
        
        PriorityQueue<int []> minHeap = new PriorityQueue<>((a,b)-> a[0]-b[0]);
        for(int [] interval: intervals){
            minHeap.offer(interval);
        }
        
        List<int[]> res = new ArrayList<>();
        int[] curr = minHeap.poll(); 
        res.add(curr); 

       while(!minHeap.isEmpty()){
            int [] nextInterval = minHeap.poll();
            if(curr[1] >= nextInterval[0]){
                curr[1] = Math.max(curr[1], nextInterval[1]);
            } else{
                curr = nextInterval;
                res.add(curr);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}