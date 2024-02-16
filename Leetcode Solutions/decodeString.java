// Given an encoded string, return its decoded string.

// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is 
// being repeated exactly k times. Note that k is guaranteed to be a positive integer.

// You may assume that the input string is always valid; No extra white spaces, square brackets are 
// well-formed, etc.

// Furthermore, you may assume that the original data does not contain any digits and that digits are 
// only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

// Examples:

// s = "3[a]2[bc]", return "aaabcbc".
// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".



USE TWO STACKS
1. to store the multiples
2. one to store the letters 
3. once we encounter a closing brace pop off the stack, and populate by the multiple times  


TC: O(N) to loop through the string, where N is length of input string 
SC: O(N) for space of the two stacks, and also for the currString that we populate

class Solution {
    public String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<String> result = new ArrayDeque<>(); 
        String currString = "";
        int index = 0;
        while(index < s.length()){
            char c = s.charAt(index);
            if(Character.isDigit(c)){
                int count = 0;
                while(Character.isDigit(s.charAt(index))){ //incase we have 3012[a]
                    count = count*10 + (s.charAt(index)) - '0'; 
                    index++;
                }
                counts.push(count);
            } else if (c == '['){
                result.push(currString);
                currString = ""; //reset current string
                index++; 
                 
            } else if (c == ']'){
                StringBuilder temp = new StringBuilder(result.pop()); //get the innermost string 
                int count = counts.pop(); //number of times to add this value 
                for(int i=0; i<count; i++){
                    temp.append(currString);
                }
                currString = temp.toString(); //set currString to the value we want to return 
                index++; 
                
            } else {
                currString += c; //append char to current string we are on 
                index++; 
            }
            
            
        }
        return currString; 
    }
}