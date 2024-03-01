// You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: 
// '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: 
// for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

// The lock initially starts at '0000', a string representing the state of the 4 wheels.

// You are given a list of deadends dead ends, meaning if the lock displays any of these codes, 
// the wheels of the lock will stop turning and you will be unable to open it.

// Given a target representing the value of the wheels that will unlock the lock, return the 
// minimum total number of turns required to open the lock, or -1 if it is impossible.

// Example 1:
// Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
// Output: 6
// Explanation:
// A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
// Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
// because the wheels of the lock become stuck after the display becomes the dead end "0102".

// Example 2:
// Input: deadends = ["8888"], target = "0009"
// Output: 1
// Explanation:
// We can turn the last wheel in reverse to move from "0000" -> "0009".


SOLUTION - USE BFS since we are trying to find the shortest path
1. keep a hashset to check for dead ends in constant time
2. use a visited set to peform bfs and find shortest path
3. within the actual dfs, add all possible next moves to the queue



class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> dead_ends = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet<>();
        visited.add("0000");
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        
        int level = 0; //keep track of how many moves we make
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                String curr_lockposition = queue.poll();
                if(dead_ends.contains(curr_lockposition)){
                    size--;
                    continue;  //skip this iteration
                }
                
                if(curr_lockposition.equals(target)){ //for string comparison 
                    return level; 
                }
                
                //ADD ALL THE NEXT POSSIBLE MOVES
                
                StringBuilder sb = new StringBuilder(curr_lockposition); 
                for(int i=0; i<4; i++){ //add/subtract 1 from every of the 4 digits, add to queue
                    char curr_position = sb.charAt(i); 
                    String s1 = sb.substring(0,i) + (curr_position == '9' ? 0 : curr_position - '0' + 1) + sb.substring(i+1); //add all chars up to i, change current position by 1, add rest of the substring
                    String s2 = sb.substring(0,i) + (curr_position == '0' ? 9 : curr_position - '0' - 1) + sb.substring(i+1);  //add all chars up to i, change current position by 1, add rest of the substring
                    
                    if(!visited.contains(s1) && !dead_ends.contains(s1)){
                        queue.add(s1);
                        visited.add(s1);
                    }
                    if(!visited.contains(s2) && !dead_ends.contains(s2)){
                        queue.add(s2);
                        visited.add(s2);
                    }
                    
                }
                
                size--;
            }
            level++;
        }
        
        return -1; //impossible to reach the target
    }
}