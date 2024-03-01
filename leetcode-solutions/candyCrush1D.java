Intuition: 

Only consecutive characters are to be considered. While parsing through the string, 
the last 3 characters at any given point are of interest.

A stack might be of interest here, as we need the last 3 characters at any point in time. 
However, the character encountered is not the only attribute of interest. We also need to keep track 
of the number of consecutive occurrences of the character.

What to enter into stack?
(character, number of consecutive occurrences)

Algorithm 

1. Check the top of stack. If stack is empty or current character is not the same as top of stack, 
add (current character, 1) to the stack.
2. If current character is equal to top of stack, pop from stack. Number of consecutive occurrences 
is incremented by 1. If number of occurrences is less than 3, push char with updated count back. 
Move to next character in string
3. Continue till all characters have been iterated over. Answer is reverse order of characters in stack

//TC: O(N)
//SC: O(N)


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

FOLLOW UP - use backtracking