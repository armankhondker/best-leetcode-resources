// Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different
//  letters represent different tasks. Tasks could be done without original order. Each task could be done 
//  in one interval. For each interval, CPU could finish one task or just be idle.

// However, there is a non-negative cooling interval n that means between two same tasks, there must be at
//  least n intervals that CPU are doing different tasks or just be idle.

// You need to return the least number of intervals the CPU will take to finish all the given tasks


KEY INSIGHTS:

1. Want to run the most frequently occuring task FIRST, so it gives us least amount of interval
2. calucalte IDLE SLOTS

//Optimal solution

//USE IDEA OF "IDLE SLOTS"
//TC: O(N) to go through array and populate the tasks frequency, N is length of tasks array 
//SC: O(26) = O(1)

class Solution {
    public int leastInterval(char[] tasks, int n) {
        
        
        int [] charmap = new int[26];
        
        for(char c: tasks)
        {
            charmap[c -'A']++;     //fill map with frequency of each task based on alphabet
        }
        
        Arrays.sort(charmap); //need to sort the task so most frequent task is at end!!!
        
        int maxCount = charmap[25] -1;   //maxCount of most frequent occurence will be in  last spot
        								 // subtract one, because in calcuation of idle slots, you dont need to apply cool down
        								//for the last occurence of the character,

        int idle_slots = maxCount * n;    //total number of spots we need to fill
        
        for(int i=24; i>=0; i--).  //go from most frequent task
        {
            idle_slots -= Math.min(charmap[i], maxCount);   //use this math.min for the last iteration
        }
        
        return idle_slots > 0 ? idle_slots+ tasks.length : tasks.length;  //if idle slots = 0, then we just return all the tasks
        																  //else we need to add on the remaining idle slots, to get our total
        
    }
}


ALSO VALID SOLUTION, USING MAX HEAP to store most frequent tasks!!!

//TC: O(Nlog(26)) == O(N) where n is length of tasks array, to populate the map with freqency of each element.
//SC: O(1) queue and temp size will not exceed 26 since that is length of alphabet 

class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: tasks){
            map.put(c, map.getOrDefault(c,0) +1); 
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a); //MAX HEAP
        maxHeap.addAll(map.values()); //will be O(Nlog(26)) to insert all these items
        
        int cycles = 0;
        while(!maxHeap.isEmpty()){
            List<Integer> temp = new ArrayList<>();   //to hold all the tasks ran in this iteration 
            for(int i=0; i<n+1; i++){ //need to go til N+1 because we need to actually wait N times
                if(!maxHeap.isEmpty()){
                    temp.add(maxHeap.remove());
                }
            }
            
            
            for(int i: temp){  
                if(--i > 0){ //TASK STILL NEEDS TO BE RAN
                    maxHeap.add(i);
                }
            }
        
            cycles+= maxHeap.isEmpty() ? temp.size() : n+1;  //either add the last values needed on this iteration, or just add 
                                                            //total cool down period for most freq task 
                                             //SO IN CASE WHERE EMPTY, just add last remaining tasks, dont wait entire cool down
        }
        return cycles; 
    }
}


MAINTAIN ORIGINAL ORDER - use map from task -> last executed time 

//TC: O(N) to go through all the tasks array
//SC: O(k) where k is distinct number of chars, for the hashmao 
 public static int leastInterval(char [] tasks, int n) {
        int total = 0;
        int timeRested = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i =0; i<tasks.length; i++) {
            // actual time, accounted for times idle
            int realTime = i + timeRested;
            char curr = tasks[i];
            if (!map.containsKey(curr)) {
                map.put(curr, realTime);
            } else {
                int prevTime = map.get(curr);
                // how much time to idle for this round
                int currRest = 0;
                if (realTime - prevTime -1 < n) {
                    currRest = n - (realTime-prevTime-1);
                    total += currRest;
                    timeRested += currRest;
                }
                // update the time stamp, accounted for time may have to rest
                map.put(curr, realTime+currRest);
            }
            total++;
        }
        return total;
    }