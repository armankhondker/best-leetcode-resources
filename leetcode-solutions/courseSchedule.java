// There are a total of n courses you have to take, labeled from 0 to n-1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?


// Intuition, can represent the nodes as graph, 
// Naive solution, If there is any cycle we can't finish the courses 
// Can use topological ordering to see if 

//To make topigical ordering
//1. list all elements with incoming edges
//2. list an element to where all incoming edges to that element have already been listeed 

//USE BFS to sort the graph 

//TC: O(V + E) to run BFS 
//SC: O(V) because max length of queue will be determind by number of nodes

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
         if (numCourses == 0 || prerequisites.length == 0) return true;

    // Convert graph presentation from edges to indegree of adjacent list.
    int indegree[] = new int[numCourses];
    for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
        indegree[prerequisites[i][0]]++;    

    Queue<Integer> queue = new LinkedList<Integer>();
    for (int i = 0; i < numCourses; i++) 
        if (indegree[i] == 0) //add all incomindg edges of length 
            queue.add(i);

    // How many courses don't need prerequisites.
    int canFinishCount = queue.size();  
    while (!queue.isEmpty()) {
        int prerequisite = queue.remove(); // Already finished this prerequisite course.
        for (int i = 0; i < prerequisites.length; i++)  {
            if (prerequisites[i][1] == prerequisite) { 
                indegree[prerequisites[i][0]]--;  //remove all the edges 
                if (indegree[prerequisites[i][0]] == 0) {
                    canFinishCount++;
                    queue.add(prerequisites[i][0]);
                }
            }
        }
    }

    return (canFinishCount == numCourses);
    }
}