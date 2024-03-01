//Given a non-empty array of integers, return the k most frequent elements.


//trival solution is to sort the nums after counting,

//1  keep count of occurences of each element in hashmap
//2. use a heap that is of size k to insert all elements, at end will hold top K most frequenet
//3. Then add to our ans as we pop off the heap, and reverse our answer to get most frequenet words

//n is length of nums array, k # of topfrequent elements we want 
//TC: O(Nlog(k)) because we insert all N elements into heap, which will take O(logk) time for each insertion
//SC: O(N) to store our count map which has each elements frequency 
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
    
        // build hash map : character and how often it appears
    HashMap<Integer, Integer> count = new HashMap();
    for (int n: nums) {
      count.put(n, count.getOrDefault(n, 0) + 1);
    }

    // init heap 'the less frequent element first'. MIN HEAP
    PriorityQueue<Integer> heap =
            new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

    // keep k top frequent elements in the heap
    for (int n: count.keySet()) {
      heap.add(n);
      if (heap.size() > k)
        heap.poll();
    }

    // build output list
    List<Integer> top_k = new LinkedList();
    while (!heap.isEmpty())
      top_k.add(heap.poll());
    Collections.reverse(top_k);
    return top_k;
        
    }
}