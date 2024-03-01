Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"

//TC: O(m*n) to loop through both strings
//SC: O(n+m) to hold the products array 


1. MAKE AN PRODUCTS ARRAY, to hold product of every combaination of numbers
2. update array to hold proper carry value, and numbers 
3. append to string builder 

class Solution {
    public String multiply(String num1, String num2) {
        
       int n1 = num1.length();
       int n2 = num2.length();
        int[] products = new int[n1 + n2];
        
        //fill in products array, with every possible product in combination of two strings
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';  //get the value to an int
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;  
            }
        }
        
        //carry over each element in product array 
        
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;    
            carry = (products[i] + carry) / 10;   
            products[i] = tmp;    
        }
        
        //append to output string 
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0); //get rid of leading 0's
        return sb.length() == 0 ? "0" : sb.toString();
    }
}