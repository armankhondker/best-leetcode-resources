// There is a new alien language which uses the latin alphabet. However, 
// the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, 
// where words are sorted lexicographically by the rules of this new language. Derive the order of letters in t
// his language.

// Example 1:

// Input:
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]

// Output: "wertf"
// Example 2:

// Input:
// [
//   "z",
//   "x"
// ]

// Output: "zx"
// Example 3:

// Input:
// [
//   "z",
//   "x",
//   "z"
// ] 

// Output: "" 

// Explanation: The order is invalid, so return "".


All approaches break the problem into three steps.

1. Extracting dependency rules from the input. For example "A must be before C", "X must be before D", or
 "E must be before B".
2. Putting the dependency rules into a graph with letters as nodes and dependencies as edges 
(an adjacency list is best).
3. Topologically sorting the graph nodes. 
NOTE there will be multiple ways for this ordering to be
(this is because all nodes with no incoming edges can be ordered in any way, FOR EACH LEVEl)


When two words are adjacent, we need to look for the first difference between them. 
That difference tells us the relative order between two letters!!!

EDGE CASE, not input is invalid if with two adjacent words, so return " " 
EDGE CASE, if there is a cycle aka we reach a point where there arent any more nodes with no indegree, return " "

BFS SOLUTION to implement topological ordering



//TC: O(C) - takes O(C) for the first and second steps in creating graph, in worst case we will have 
//to travel across, all chars in the words cominbed, if the difference in chars is always at last letter
//The bfs part will be O(V+E) but take more time than building the graph 

//SC: O(1) ecause our adj list and counts rray is constrained to our alphabet of size 26

class Solution {
    public String alienOrder(String[] words) {
        
    // Step 0: Create data structures and find all unique letters.
    Map<Character, List<Character>> adjList = new HashMap<>();
    Map<Character, Integer> counts = new HashMap<>();
    for (String word : words) {
        for (char c : word.toCharArray()) {
            counts.put(c, 0);
            adjList.put(c, new ArrayList<>());
        }
    }
    
    // Step 1: Find all edges.
    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];
        // Check that word2 is not a prefix of word1.
        if (word1.length() > word2.length() && word1.startsWith(word2)) {
            return "";
        }
        // Find the first non match and insert the corresponding relation.
        for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
            if (word1.charAt(j) != word2.charAt(j)) {
                adjList.get(word1.charAt(j)).add(word2.charAt(j));
                counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                break;
            }
        }
    }
    
    // Step 2: Breadth-first search.
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for (Character c : counts.keySet()) {  
        if (counts.get(c).equals(0)) {  //add all the nodes with NO incoming edges!!
            queue.add(c);
        }
    }
    while (!queue.isEmpty()) {
        Character c = queue.remove();
        sb.append(c);
        for (Character next : adjList.get(c)) {
            counts.put(next, counts.get(next) - 1);
            if (counts.get(next).equals(0)) { //once a node count reches 0, it has no incoming edges, so add
                queue.add(next);
            }
        }
    }
    
    if (sb.length() < counts.size()) {
        return "";
    }
    return sb.toString();
  }
}




