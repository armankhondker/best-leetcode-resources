Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

//O(n) to go through length of string
//O(n) because stack could have length n in worst case 

 class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        // Iterate through string until empty
        for(int i = 0; i<s.length(); i++) {
            // Push any open parentheses onto stack
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            // Check stack for corresponding closing parentheses, false if not valid
            else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                stack.pop();
            else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                stack.pop();
            else
                return false;
        }
        // return true if no open parentheses left in stack
        return stack.empty();
    }
}





//MOST BASIC WAY TO IMPLEMENT

class Solution {
    public boolean isValid(String s) {
        if(s == null) return true; 
        
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}','{');
        map.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for(char c: s.toCharArray()){
            
            if(c == '(' || c == '[' || c=='{'){
                stack.push(c);
            } else {
                if(!stack.isEmpty() && stack.peek() != map.get(c)){
                    return false;
                } else{
                    if(!stack.isEmpty())
                    {
                        stack.pop();
                    } else{
                        return false;
                    }
                }
            }
        }
        
        if(stack.size()!=0) return false; 
        
        return true; 
        
    }
}