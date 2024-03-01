Given a string which consists of lowercase or uppercase letters, 
find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.


//can get hashset to count occurences of every letter

//if count is even we can include into our final answer 


Time Complexity: O(N), where NN is the length of s. We need to count each letter.

Space Complexity: O(1), the space for our count, as the alphabet size of s is fixed. 
We should also consider that in a bit complexity model, technically we need O(logN) 
bits to store the count values.

//If using a bigger charactere set than ASCII, then can use a hashset 


class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128]; //ASCII character set 
        for (char c: s.toCharArray()) //populat count array
            count[c]++;

        int ans = 0;
        for (int x: count) {
            ans += x / 2 * 2;    //just to count number of even pairs
            if (ans % 2 == 0 && x % 2 == 1) //if we have a one of character and our answer is currently even in length  we can append 
                ans++;
        }
        return ans;
    }
    
}