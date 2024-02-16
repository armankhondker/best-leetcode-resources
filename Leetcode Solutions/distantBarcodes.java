In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  
You may return any answer, and it is guaranteed an answer exists.

 
Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]



USE HEAP 

1. Linear scan to get frequency of the barcodes
2. Add all of the keys in map to a maxHeap
3. populate the res by polling the top two elements in the priority queue so consecutive elemnts
are not next to each other 

TC: O(N) where N is the length of the barcodes array
SC: O(N) for a maxheap which can get up to the size of length N

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int [] res = new int[n]; 
        Map<Integer, Integer> counts = new HashMap<>(); //hm to store (number, # of occurences)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> counts.get(b)-counts.get(a));
        
        for(int i: barcodes){
            counts.put(i, counts.getOrDefault(i,0)+1);
        }
        
        maxHeap.addAll(counts.keySet()); //add all the numbers to the heap 
        
        int index = 0; //index so we can populate res array
        while(maxHeap.size()>0){ //while there is at least 1 element 
            int a = maxHeap.poll(); 
            res[index++]= a;
            if(maxHeap.size() == 0) break;
            int b = maxHeap.poll();
            res[index++]=b;
            
            update(counts, maxHeap, a); //need to readd to the heap to keep processing if necssary
            update(counts, maxHeap, b); //need to readd to the heap to keep processing
            
        }
        return res; 
    }
    
    public void update(Map<Integer, Integer> counts, PriorityQueue<Integer> maxHeap, int x){
        
        if(counts.get(x)==1){ //last occurence of this number
            counts.remove(x);
        } else {
            counts.put(x, counts.get(x)-1); //decrement # of occurnces after we have processed 
            maxHeap.add(x); //readd to the heap 
        }
        
    }
}