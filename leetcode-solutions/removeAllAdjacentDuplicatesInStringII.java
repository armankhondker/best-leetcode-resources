// Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from 
// s and removing them causing the left and the right side of the deleted substring to concatenate together.

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


Brute force,

TC: O(n^2/k) where n is the length of the string
SC: O(1)

public String removeDuplicates(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    int length = -1;
    while (length != sb.length()) {
        length = sb.length();
        for (int i = 0, count = 1; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count = 1;
            } else if (++count == k) {
                sb.delete(i - k + 1, i + 1);
                break;
            }
        }
    }
    return sb.toString();
}


Use a stack to keep track of occurences of each character 

iterate through the string
1. if curr char is same as one before, increment count on top of the stack
2. else push 1 onto the stack
3. if top count of stack == k, erase last k characters and pop from stack 

TC: O(N) to through through the strings 
SC: O(N)

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


//BETTER WAY - Keep track of chars and counts

O(N)
O(N)

class CharAndCount
{
  private int cnt;
  private Character chr;

  public CharAndCount(Character chr, int cnt) {
    this.chr = chr;
    this.cnt = cnt;
  }

}

public class CandyCrush_InterviewBB
{
  public static String removeChars(String str, int k) {
    Stack<CharAndCount> st = new Stack<>();

    StringBuilder sb =new StringBuilder();

    for (int i =0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (!st.isEmpty())  {

        CharAndCount currVal = st.peek();
        if (currVal.getChr() == c) {
          currVal.setCnt(currVal.getCnt() + 1);
        } else {
          if (currVal.getCnt() >= k) {
            st.pop();
          }

          if (!st.isEmpty() && st.peek().getChr() == c) {
            currVal = st.peek();
            currVal.setCnt(currVal.getCnt() + 1);
          }
          else {
            st.push(new CharAndCount(c, 1));
          }
        }
      }
      else {
        st.push(new CharAndCount(c, 1));
      }
    }

    //THE ELEMENTS in the stack will represent the final string in reverse order!
    while(!st.isEmpty()) {
      CharAndCount cnc = st.pop();
      for (int i =0; i< cnc.getCnt(); i++) {
        sb.append(cnc.getChr());
      }
    }
    return sb.reverse().toString();
  }
}



