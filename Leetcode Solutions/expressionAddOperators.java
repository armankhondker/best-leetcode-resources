// Given a string that contains only digits 0-9 and a target value, return all possibilities to 
//add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

// Example 1:

// Input: num = "123", target = 6
// Output: ["1+2+3", "1*2*3"] 
// Example 2:

// Input: num = "232", target = 8
// Output: ["2*3+2", "2+3*2"]
// Example 3:

// Input: num = "105", target = 5
// Output: ["1*0+5","10-5"]          KEY TEST CASE, the digits dont have to jsut be one difit
// Example 4:

// Input: num = "00", target = 0
// Output: ["0+0", "0-0", "0*0"]




BEST SOLUTION, DIVIDE AND CONQUER

Since we are required to return all of the valid expressions that evaluate to a given target value, 
we have to try all possible partitions of the given array thereby considering all of the possible operands
that can be formed from the digits.

//TC:
//SC: 


public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
