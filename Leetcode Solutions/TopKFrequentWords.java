//Given a non-empty list of words, return the k most frequent elements.


//1 . keep count of occurences of each word in hashmap
//2. use a heap that is of size k to insert all of the most frequent elements 
//3. Then add to our ans as we pop off the heap, and reverse our answer to get most frequenet words

//TC: O(NlogK) because we insert all N items into a heap which takes O(logK) time, where n is size of words list
//SC: O(N) to store count hashmap 

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

    	Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
        
    }
}