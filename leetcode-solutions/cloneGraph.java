// Given a reference of a node in a connected undirected graph.

// Return a deep copy (clone) of the graph.

// Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

// class Node {
//     public int val;
//     public List<Node> neighbors;
// }


//NEEDto use hashmap - because we need constant time access to associated CLONE NODE 
//map key-value will be <ORIGINAL NODE, CLONE NODE> 

//Need to use BFS to traverse graph because we care about NODE RELATIONSHIPS, node-edge which makes
//level by level searching much more efficient 

//TC: O(N) for BFS
//SC: O(N) for hashmap of nodes
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            Node curr = q.poll();
            for(Node neighbor : curr.neighbors){

                if(!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    q.add(neighbor);
                }
                map.get(curr).neighbors.add(map.get(neighbor));
                
            }
        }

        return map.get(node);
    }
}