// Given a string, sort it in decreasing order based on the frequency of characters.

// Example 1:

// Input:
// "tree"
// Output:
// "eert"

// Explanation:
// 'e' appears twice while 'r' and 't' both appear once.
// So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
// Example 2:

// Input:
// "cccaaa"

// Output:
// "cccaaa"

// Explanation:
// Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
// Note that "cacaca" is incorrect, as the same characters must be together.
// Example 3:



MAX HEAP SOLUTION 

TC: O(N + MlogM) where N is length of input string, and M is number of distinct chars 
that are keys in the map, will take O(mlogm) to build the heap. SINCE OUR CHARSET IS LIMITED,
and we only deal with limited characters, can be considerered O(N) total since 52log52 == O(1)

SC: O(M) to have a heap with all the distinct chars and a map of all the distcint keys, and the values
SINCE OUR CHARSET IS LIMITED, we can say O(1) space for map, BUT NEED O(N) space for the output array

class Solution {
    public String frequencySort(String s) {
        if(s == null || s.length() == 0) return ""; 
        
       HashMap<Character, Integer> map = new HashMap<>();
        for(char c: s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b)->map.get(b) -map.get(a));
        StringBuilder sb = new StringBuilder();
        
        for(char c: map.keySet()){
            maxHeap.offer(c);
        }
        while(!maxHeap.isEmpty()){
            char c = maxHeap.poll();
            int count = map.get(c);
            for(int i=0; i<count; i++){
                sb.append(c); 
            }
        }
        
        return sb.toString(); 
    }
}



SLIGHTLY OPTIMIZED HARD TO IMPLEMENT

BUCKET sort - because the input string is length N, the maximum a char freq can be is N
Therfore, we make an array of buckets of length maxFreq, where we put each char into these buckets

Then we iterate from the mostFreq bucket, and populate the string just like bfore 


TC: O(N) where N is length of input, because we need to count freq and put into map
bucket sorting will take O(N) 
SC: O(N) because the bucket array takes up O(n) space and there are k items across all buckets 

public String frequencySort(String s) {
        
    if (s == null || s.isEmpty()) return s;
    
    // Count up the occurances.
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : s.toCharArray()) {
        counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
    
    int maximumFrequency = Collections.max(counts.values());
    
    // Make the list of buckets and apply bucket sort.
    List<List<Character>> buckets = new ArrayList<>();
    for (int i = 0; i <= maximumFrequency; i++) {
        buckets.add(new ArrayList<Character>());
    }
    for (Character key : counts.keySet()) {
        int freq = counts.get(key);
        buckets.get(freq).add(key);
    }

    // Build up the string. 
    StringBuilder sb = new StringBuilder();
    for (int i = buckets.size() - 1; i >= 1; i--) {
        for (Character c : buckets.get(i)) {
            for (int j = 0; j < i; j++) { //this is amount of times char will occur
                sb.append(c);
            }
        }
    }
    return sb.toString();
}
