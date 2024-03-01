// Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

// Example 1:

// Input: 121
// Output: true
// Example 2:

// Input: -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
// Example 3:

// Input: 10
// Output: false
// Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

INTUTION, reverse the integer and then compare with the original

KEY POINTER, only have to revese half and check if first half is the same!! //saves time 

// compare half of the digits in x, so don't need to deal with overflow.


//O(logx) there are roughly log(x) digits in x , OR O(N) where N is number of digits in X
//O(1)
public boolean isPalindrome(int x) {
    if (x<0 || (x!=0 && x%10==0)) return false;  //negatives cant be palindrome and if it ends with 0 it cant start with 0 so no palindorme
    int rev = 0;
    while (x>rev){. //this will only go halfways
    	rev = rev*10 + x%10;
    	x = x/10;
    }
    return (x==rev || x==rev/10);  //we need to check if the number is odd length, then we will only need to check rev/10 digits 
}