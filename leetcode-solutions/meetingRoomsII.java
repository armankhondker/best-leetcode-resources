Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] 
(si < ei), find the minimum number of conference rooms required.

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Input: [[7,10],[2,4]]
Output: 1


//Intution, sort the intervals by start time
//Use a priority queue to populate all the meetings that work together
//at end return the size of the heap because that will be number of rooms needed 

//NOTE, the priority queue, will hold all the meetings with earliest end times that dont conflict

//O(nlogn) to sort the intervals, also worst case will have n insertions to heap when all meetings conflict
//O(n) to store the values in the min heap

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]); //sort intervals by start time 
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); //we are just inserting END TIMES, min heap by default
        for (int[] interval : intervals) {
            if (pq.size() == 0 || pq.peek() > interval[0]) { //check if end time is greater than start
                pq.add(interval[1]);  //if so we need another room, so add another item to heap 
            } else { //in this case the current interval strts right afer the interval on heap! so dont need extra room,
                     //pop current element and update the end time 
                pq.poll();
                pq.add(interval[1]);
            }
        }
        
        return pq.size();
    }
}



MORE THOROUGH SOLUTION, same time complexity and lgoic ,
NOTE INPUT IS DIFFERENT

public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0)
        return 0;
        
    // Sort the intervals by start time
    Arrays.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.start - b.start; }
    });
    
    // Use a min heap to track the minimum end time of merged intervals
    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.end - b.end; }
    });
    
    // start with the first meeting, put it to a meeting room      STARTING HERE FOR MORE DETAILED LOGIC!!!!!
    heap.offer(intervals[0]);
    
    for (int i = 1; i < intervals.length; i++) {
        // get the meeting room that finishes earliest
        Interval interval = heap.poll();
        
        if (intervals[i].start >= interval.end) {
            // if the current meeting starts right after 
            // there's no need for a new room, merge the interval
            interval.end = intervals[i].end;
        } else {
            // otherwise, this meeting needs a new room
            heap.offer(intervals[i]);
        }
        
        // don't forget to put the meeting room back
        heap.offer(interval);
    }
    
    return heap.size();
}