Given an array nums of n integers where n > 1,  return an array output such that output[i] 
is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).



//KEY, is to realize it is just product of left * product of right, 

//TC: O(N) to go through nums array and populate L,R,ans
//SC: O(N) for space for L and r
class Solution {
    public int[] productExceptSelf(int[] nums) {
    
        
        int length = nums.length; 
        
        int[] ans = new int[length];
        
        int [] L = new int[length];
        int [] R = new int[length];
        
        L[0] = 1;
        for(int i=1; i<length; i++)
        {
            L[i] = nums[i-1] * L[i-1];
        }
        
        R[length-1] = 1;
        for(int i=length-2; i>=0; i--)
        {
            R[i] = nums[i+1] * R[i+1];
        }
        
        for(int i=0; i<length; i++)
        {
            ans[i] = L[i] * R[i]; 
        }
        
        return ans; 
        
    }
}


OPTIMIZED, O(1) space!!!
NOTE, THIS IS ASSUMING OUTPUT ARRAY DOES NOT COUNT AS ADDITIONAL SPACE!!!

//TC: O(N) to run through all elements in array 
//SC: O(1) because output array doesn't count as extra space in this problem satement

class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int [] ans = new int[nums.length];
        
        ans[0] = 1; //FOR THE FIRST ELEMENT, there is nothing to left, so set val to 1
        
        for(int i=1; i<nums.length; i++){
            ans[i] = ans[i-1] * nums[i-1];
        }
        
        int R = 1; //FOR LASt ELEMENT, there is nothing to right, so set val to 1
        for(int i=nums.length-1; i>=0; i--){
            ans[i] = ans[i] * R;
            R = R * nums[i]; 
            
        }
        
        return ans;
    }
}

