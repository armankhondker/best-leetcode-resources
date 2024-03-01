// There are 2N people a company is planning to interview. The cost of flying the i-th person to city A i
// s costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

// Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

 
// Example 1:

// Input: [[10,20],[30,200],[400,50],[30,20]]
// Output: 110
// Explanation: 
// The first person goes to city A for a cost of 10.
// The second person goes to city A for a cost of 30.
// The third person goes to city B for a cost of 50.
// The fourth person goes to city B for a cost of 20.

// The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.


Intution, use a Greedy algorithm to pick the locally optimal move at each step

GREEDY - Pick the locally optimal move at each step, and that will lead to the globally optimal solution.

1. sort the input by difference between city A and city B!
2. populate the minCost, by looping through the costs array, allocating price of city A to first half
and price of city B to second half 


Another way of thinking about it: 
 you just sort them in the order from most costly to send to A, to most costly to send to B and 
 then send the first half to city B and the second half to city A.


Another way to look at it is that each person costs a certain amount to fly regardless of the city they go to, 
then they cost an additional premium to fly to one of the cities over the other. If their cost pair is [1000,1001] 
basically that person costs 1000 no matter what and we are only looking at saving or spending that extra dollar.
We could reduce the solution by subtracting the minimum cost from both sides of each pair and then looking at
optimizing the differential costs. 

//TC: O(nlogn) to sort the input by cost to send to city a, could be O(n) if input is already sorted
//SC: O(1) no extra space needed

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        if(costs == null || costs.length == 0) return 0; 
        int n = costs.length/2; 
        int minCost = 0; 
        
        Arrays.sort(costs, (a,b)-> a[0] - a[1] - (b[0] - b[1])); // Sort by a gain which company has 
                                                            // by sending a person to city A and not to city B
        for(int i=0; i<n; i++){
            minCost += costs[i][0] + costs[i+n][1]; //gradually add city A cost for first half, 
            										//and City B cost for second half
        }
        
        return minCost; 
    }
}

