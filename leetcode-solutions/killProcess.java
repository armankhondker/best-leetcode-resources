// Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

// Each process only has one parent process, but may have one or more children processes. This is 
// just like a tree structure. Only one process has PPID that is 0, which means this process has no
//  parent process. All the PIDs will be distinct positive integers.

// We use two list of integers to represent a list of processes, where the first list contains PID 
// for each process and the second list contains the corresponding PPID.

// Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs 
// of processes that will be killed in the end. You should assume that when a process is killed, all 
// its children processes will be killed. No order is required for the final answer.

// Example 1:
// Input: 
// pid =  [1, 3, 10, 5]
// ppid = [3, 0, 5, 3]
// kill = 5
// Output: [5,10]
// Explanation: 
//            3
//          /   \
//         1     5
//              /
//             10
// Kill 5 will also kill 10.



HASHMAP + BFS - use a hashmap to better represent parent-child relationships of the tree
Then perform BFS starting from kill and adding all children to res array 

TC: O(N) we need to iterative on the ppid array of length N once
SC: O(N) for the hashmap which will contain all nodes and their children


    public List < Integer > killProcess(List < Integer > pid, List < Integer > ppid, int kill) {
        HashMap < Integer, List < Integer >> map = new HashMap < > ();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List < Integer > l = map.getOrDefault(ppid.get(i), new ArrayList < Integer > ());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }
        Queue < Integer > queue = new LinkedList < > ();
        List < Integer > l = new ArrayList < > ();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int r = queue.remove();
            l.add(r);
            if (map.containsKey(r))
                for (int id: map.get(r))
                    queue.add(id);
        }
        return l;
    }
