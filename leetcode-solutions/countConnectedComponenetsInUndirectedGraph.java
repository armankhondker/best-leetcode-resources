Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.



//intution RUN BFS/DFS on every connected compoenent, update counter once current element has been explored 

//BFS AND DFS SOLUTIONS PROVIED!!!!
//TC: O(V+E) to run BFS or BFS
//SC: O(V)

class Solution {
    
    public int countComponentsDFS(int n, int[][] edges) {        
    HashMap<Integer, List<Integer>> adjList = new HashMap<>(); 
    for(int node = 0; node < n; node++) {
        adjList.put(node, new ArrayList<>()); 
    }
    
    for(int[] edge : edges) {
        adjList.get(edge[0]).add(edge[1]); 
        adjList.get(edge[1]).add(edge[0]); 
    }
    
    int connectedComponents = 0; 
    HashSet<Integer> visited = new HashSet<>(); 
    for(Integer node : adjList.keySet()) {
        if(!visited.contains(node)) {
            connectedComponents++; 
            dfs(adjList, visited, node); 
        }
    }
    return connectedComponents; 
}

private void dfs(HashMap<Integer, List<Integer>> adjList, HashSet<Integer> visited, int node) {
    visited.add(node); 
    for(Integer neighbor : adjList.get(node)) {
        if(!visited.contains(neighbor)) {
            dfs(adjList, visited, neighbor); 
        }
    }
}



//BFS VERSION 
public int countComponentsBFS(int n, int[][] edges) {        
    HashMap<Integer, List<Integer>> adjList = new HashMap<>(); 
    for(int node = 0; node < n; node++) {
        adjList.put(node, new ArrayList<>()); 
    }
    
    for(int[] edge : edges) {
        adjList.get(edge[0]).add(edge[1]); 
        adjList.get(edge[1]).add(edge[0]); 
    }
    
    int connectedComponents = 0; 
    HashSet<Integer> visited = new HashSet<>(); 
    Queue<Integer> queue = new LinkedList<>(); 
    for(Integer node : adjList.keySet()) {
        if(!visited.contains(node)) {
            connectedComponents++; 
            queue.offer(node); 
            visited.add(node);
        }
        
        while(!queue.isEmpty()) {
            int curr = queue.poll(); 
            for(Integer neighbor : adjList.get(curr)) {
                if(!visited.contains(neighbor)) {
                    queue.offer(neighbor); 
                    visited.add(neighbor); 
                }
            }
        }
    }
    
    return connectedComponents; 
}
}