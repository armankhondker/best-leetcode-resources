Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 
32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume 
that your function returns 0 when the reversed integer overflows.




//TC: O(N) to go through all the digits in x where N is length of X
//SC: O(1)
class Solution {
    public int reverse(int x) {
        int res = 0;
        int lastDigit;
        while(x!=0){
            lastDigit = x%10; //get last digit 
            x= x/10; //get rid of last element
            
            //HANDLE OVERFLOW!!! 
            //logic of 7 and -8 is sketchy dont write in interview
            //can maybe use a long variable here!!
          if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && lastDigit > 7)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && lastDigit < -8)) return 0;
            
            res = (res*10) + lastDigit; 
        }
        return res; 
    }
}