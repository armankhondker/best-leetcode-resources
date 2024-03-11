// Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

// Note:

// The length of both num1 and num2 is < 5100.
// Both num1 and num2 contains only digits 0-9.
// Both num1 and num2 does not contain any leading zero.
// You must not use any built-in BigInteger library or convert the inputs to integer directly.

//TC: O(n) where n is max length of two arrays, because we loop through them bothß
//SC: O(n) where n is max length between num1 and num2 to store char array
class Solution {
    public String addStrings(String num1, String num2) {
        int i=num1.length()-1;
        int j = num2.length() -1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while(i>=0 ||  j>=0 || carry > 0){
            int sum = carry;
            if(i>=0){
                sum += num1.charAt(i) - '0'; 
                i--;
            }
            if(j>=0){
                sum+=num2.charAt(j) - '0';
                j--;
            }
            carry = sum/10;
            res.append(sum%10);
        }
        return res.reverse().toString();
    }
}