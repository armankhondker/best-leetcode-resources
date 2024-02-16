

What you're looking for is a kind of map or dictionary which supports the following queries:         						

1. Add(key, x): add x to the total for that key, creating a new entry if it doesn't already exist.              
2. GetKLargest(k): return the keys/totals for the k largest entries.

Let's say Q is the number of queries, and n is the number of distinct keys. We should assume that Q is much         
 larger than n; choosing the NYSE as an example, there are a few thousand stocks traded, and a few million trades
  per day.

In the first scenario we assume that there are a large number of Add queries followed by one GetKLargest query.
 Since the cost of the Add query dominates, we can use a hashtable so that Add takes O(1) time, and then at the
  end of the day we can do GetKLargest in O(n log k) time using a priority queue of size k; note that we don't          
  need to sort the whole key-set in O(n log n) time just to find the k largest elements. The total cost of 
  answering Q queries is O(Q + n log k).

In the second scenario, we assume there could be a large number of both kinds of query. The cost of either 
query could dominate. A good option is to use an order statistic tree, which supports Add in O(log n) time, 
and GetKLargest in O(k log n) time. To look up a company by name in the tree requires a separate index, which 
can be maintained as a hashtable. The total cost is O(Qk log n) in the worst case.

If k is fixed or has a fixed limit, we can do better: keep the totals in a hashtable, but also maintain a 
priority queue of the current top k elements alongside. The cost of the Add query is now O(log k) because 
of maintaining the priority queue; to do this efficiently we need the map to also store the current index of
 each company in the priority queue, if it's there, otherwise searching the priority queue for the right                  
 company is O(k). The cost of GetKLargest is O(k) since we just output the contents of the priority queue.
  (The problem doesn't say we need to output them in order. If we do, then we can use a sorted array instead               
  	of a heap for the priority queue, and Add takes O(k) time.)

In this case, the total cost of answering Q queries is O(Qk). Note that this only works if we know in advance the maximum value of k that could be queried, before the query arrives; otherwise we don't know how big to make the priority queue.