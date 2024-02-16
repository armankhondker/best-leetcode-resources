// Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

// We repeatedly make k duplicate removals on s until we no longer can.

// Return the final string after all such duplicate removals have been made.

// It is guaranteed that the answer is unique.

// Example 1:

// Input: s = "abcd", k = 2
// Output: "abcd"
// Explanation: There's nothing to delete.
// Example 2:

// Input: s = "deeedbbcccbdaa", k = 3
// Output: "aa"
// Explanation: 
// First delete "eee" and "ccc", get "ddbbbdaa"
// Then delete "bbb", get "dddaa"
// Finally delete "ddd", get "aa"
// Example 3:

// Input: s = "pbbcggttciiippooaais", k = 2
// Output: "ps"

SOLUTION - STACK of COUNTS

Iterate through the string:

If the current character is the same as the one before, increment the count on the top of the stack.

Otherwise, push 1 to the stack.
If the count on the top of the stack equals k, erase last k characters and pop from the stack

TC: O(N) to loop through the string
SC: O(N) for the stack 

class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>(); 
        for(int i=0; i<sb.length(); i++){
            if(i==0 || sb.charAt(i) != sb.charAt(i-1)){
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if(incremented == k){
                    sb.delete(i-k+1, i+1); 
                    i = i-k; 
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString(); 
    }
}


APPROACH 2 - Stack to store character and count - USE IF WE CANT MODIFY INPUT STRING

AT the end the characters in the stack will be in reverse order of the output!

TC: O(N) to go through the string
SC: O(N) for the stack

class Pair {
    int cnt;
    char ch;
    public Pair(int cnt, char ch) {
        this.ch = ch;
        this.cnt = cnt;
    }
}
public String removeDuplicates(String s, int k) {
    Stack<Pair> counts = new Stack<>();
    for (int i = 0; i < s.length(); ++i) {
        if (counts.empty() || s.charAt(i) != counts.peek().ch) {
            counts.push(new Pair(1, s.charAt(i)));
        } else {
            if (++counts.peek().cnt == k) {
                counts.pop();
            }
        }
    }
    StringBuilder b = new StringBuilder();  
    while (!counts.empty()) {
        Pair p = counts.pop();
        for (int i = 0; i < p.cnt; i++) {
            b.append(p.ch);
        }
    }
    return b.reverse().toString(); //need to reverse all the elements in the stack!
}