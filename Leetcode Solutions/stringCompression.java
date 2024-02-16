// Given an array of characters, compress it in-place.

// The length after compression must always be smaller than or equal to the original array.

// Every element of the array should be a character (not int) of length 1.

// After you are done modifying the input array in-place, return the new length of the array.

 
// Follow up:
// Could you solve it using only O(1) extra space?

 
// Example 1:

// Input:
// ["a","a","b","b","c","c","c"]

// Output:
// Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

// Explanation:
// "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 

 Use two pointers to go through the array until we reach elmenets which are diferent
 update i to be j 


TC: O(N) to go through the chars array 
SC: O(1) no extra data structures to go run this algorithm 

 class Solution {
    public int compress(char[] chars) {
        int index = 0; //store curr index we are on in chars, so we can overwrite
        int i=0;
        while(i < chars.length){
            int j=i; 
            while(j<chars.length && chars[j] == chars[i]){
                j++;
            }
            
            //record what we have found
            chars[index++] = chars[i];
            if(j-i > 1){ //j-i is number of times this char occured, need > 1 to add number
                String count = j-i+""; //convert to string;
                for(char c: count.toCharArray()){
                     chars[index++] = c; 
                }
            }
            
            i=j; //put the pointers back on top of each other 
        }
        
        return index; //will be length of new compressed string 
        
    }
}