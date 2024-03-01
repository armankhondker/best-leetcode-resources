 // Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and 
// return them in any order.

// The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of 
// all nodes j for which the edge (i, j) exists.

// Example:
// Input: [[1,2], [3], [3], []] 
// Output: [[0,1,3],[0,2,3]] 
// Explanation: The graph looks like this:
// 0--->1
// |    |
// v    v
// 2--->3
// There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

NOTE, we are given a acyclic graph, so no cycles, and we wont get stuck in infinite loop 


USE BFS populating the paths that are valid 

TC: O(N^2*2^n) where n is the number of nodes in the graph
The 2^n comes from the fact, that in every path, the current node will either be in the path or it wont be in the path
N^2 comes from we will loop over all the neighbors of a node, and have to copy over all elements in the
current path, so in worst case N neighobrs x copying N nodes = O(N^2)

SC: O(2^n) because we have a total of 2^n paths, and in worst case we have to add every single path,
into our return array 

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>(); 
        if(graph == null || graph.length == 0) return res; 
        int goalNode = graph.length -1; //node we are trying to reach 
            
        Queue<List<Integer>> q = new LinkedList<>(); //q to contain current path we are on
        q.add(Arrays.asList(0));
        while(!q.isEmpty()){
            List<Integer> path = q.poll(); 
            int last = path.get(path.size()-1); //to check if we have reached goalNode
            if(last == goalNode){
                res.add(new ArrayList<>(path)); 
            } else {
                int [] neighbors = graph[last]; //get all neighobrs of last node we are on
                for(int neighbor : neighbors){		//O(n) neighbors 
                    List<Integer> list = new ArrayList<>(path); //takes O(N) time
                    list.add(neighbor);
                    q.add(list);
                }
            }
        }
        return res; 
        
    }
}