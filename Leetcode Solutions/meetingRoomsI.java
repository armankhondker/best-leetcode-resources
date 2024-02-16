Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.


Sort the intervals and then loop through seeing if any conflicts


class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals,(a,b) -> a[0]-b[0]);  //sort intervals by start time 
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<intervals[i-1][1]){
                return false;
            }
        }
        return true
    }
}