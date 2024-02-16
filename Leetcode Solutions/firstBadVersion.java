// You are a product manager and currently leading a team to develop a new product. 
// Unfortunately, the latest version of your product fails the quality check. Since each 
// version is developed based on the previous version, all the versions after a bad version 
// are also bad.

// Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
// which causes all the following ones to be bad.

// You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

// Example:

// Given n = 5, and version = 4 is the first bad version.

// call isBadVersion(3) -> false
// call isBadVersion(5) -> true
// call isBadVersion(4) -> true

// Then 4 is the first bad version. 


INSIGHT, USE BINARY SEARCH TO MAKE SEARCH SPACE SMALLER!!!

At the end of our function, left and right pointers will be on top of eachother.
and they will have the first bad version


//TC: O(logn)
//SC: O(1)

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        
        int left = 0;
        int right = n;
        
        while(left < right){
            int mid = left + (right - left)/2;
            if(!isBadVersion(mid)){
                left = mid+1; //we already know mid isnt first bad version, so we can DISCARD IT, and have left skip over it
            }else{
                right = mid;  //because mid MAY or MAY NOT be the first bad version, so we cant discard it
            }
        }
        return left; 
        
    }
}