Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"



BRUTE FORCE, key idea is to find the longest palindrome starting from first char,
then reverse the remaining elements and append to beggining of input
this will be shortest palindrome

TC: O(N^2)
SC: O(N) extra space for the reverse string

public String shortestPalindrome(String s) {
    int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
    while(i < j) {
         if (chs[i] == chs[j]) {
             i++; j--;
         } else { 
             i = 0; end--; j = end;
         }
    }
    return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
}




BEST SOLUTION : KMP TABLE ALGORITHM 

INTUTION, reverse the input and append to start of the string. 
THIS WILL FOR SURE BE PALINDROME, however not the shortest 
We need to find all the characters that are not necessary in the palindrome

The algorithm to find the shortest palindrome

1. String rev = rev(s);
2. append to rev to end of s 
3. find suffix(rev) which is longest prefix(s)
4. remove that suffix, append remainder of rev to BEGGINING of s

KMP is algorithm that allows you substring search in O(M+N) time rather than O(MN) time

BUILD KMP partial match table, using proper suffixes and prefixse,
then append 

The key of KMP is to build a look up table that records the match result of prefix and postfix (suffix).
Value in the table means the max len of matching substring that exists in both prefix and postfix.


TC: O(N) the kmp algorithm runs in constant space complexity 
SC: O(N) space to store the reverse string 

public String shortestPalindrome(String s) {
    String temp = s + "#" + new StringBuilder(s).reverse().toString();
    int[] table = getTable(temp);
    
    //get the maximum palin part in s starts from 0
    return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
}

public int[] getTable(String s){
    //get lookup table
    int[] table = new int[s.length()];
    
    //pointer that points to matched char in prefix part
    
    int index = 0;
    //skip index 0, we will not match a string with itself
    for(int i = 1; i < s.length(); i++){
        if(s.charAt(index) == s.charAt(i)){
            //we can extend match in prefix and postfix
            table[i] = table[i-1] + 1;
            index ++;
        }else{
            //match failed, we try to match a shorter substring
            
            //by assigning index to table[i-1], we will shorten the match string length, and jump to the 
            //prefix part that we used to match postfix ended at i - 1
            index = table[i-1];
            
            while(index > 0 && s.charAt(index) != s.charAt(i)){
                //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                index = table[index-1];
            }
            
            //when we are here may either found a match char or we reach the boundary and still no luck
            //so we need check char match
            if(s.charAt(index) == s.charAt(i)){
                //if match, then extend one char 
                index ++ ;
            }
            
            table[i] = index;
        }
        
    }
    
    return table;
}