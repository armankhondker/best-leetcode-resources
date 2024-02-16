// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to 
//each other are not the same.
// If possible, output any possible result.  If not possible, return the empty string.

1. First get freq of all the characters
2. SOLTUION, USE A MAX HEAP with MOST FREQ occuring characters
3. Greedily append top two most freq elements to res, at end check last word in heap 

//TC: O(nlogA) ==== O(N) where n is size of string, and A is size of alphabet
//SC: O(A) = O(1) since A is fixed, constant space 

class Solution {
    public String reorganizeString(String S) {
        if(S.length() == 0 || S == null) return null; 

        Map<Character, Integer> freq = new HashMap<>();
        for(char c: S.toCharArray()){
            freq.put(c, freq.getOrDefault(c,0)+1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b)->freq.get(b) - freq.get(a)); 
                                                   //we will order them by their freq count in map
        maxHeap.addAll(freq.keySet()); //add all our chars to the heap 
        
        StringBuilder sb = new StringBuilder(); 
        
        //greedy solution, greedily append top two most freq elements 
        while(maxHeap.size() > 1){
            char current = maxHeap.remove(); //get two most freq chars
            char next = maxHeap.remove(); 
            
            sb.append(current);
            sb.append(next);
            
            freq.put(current, freq.get(current)-1); //decrement count
            freq.put(next, freq.get(next)-1);
            
            if(freq.get(current) > 0){ 
                maxHeap.add(current);
            }
           if(freq.get(next) > 0){ 
                maxHeap.add(next);
            }
        }
        
        //check for last char, if it has more freq left inside of it, then we can't reorganize the string
        if(!maxHeap.isEmpty()){
            char last = maxHeap.remove();
            if(freq.get(last)>1){
                return "";
            }
            sb.append(last); //else we are good, and just append the last char 
        }

        return sb.toString(); 
    }
}