Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1


BRUTE FORCE,

1. Add all numbers in array to a set, to get unique elements

2. we now know the missing number is in range (1, set.size()+1)

3. loop from 1 to set.size()+1 and check if that i is in the set,
if that number isnt in set, return i


//TC: O(n) to scan through the array
//SC: O(n) for hashset of elements in array

    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        
        for(int x : nums){
            set.add(x); 
        }
        
        for(int i=1; i<nums.length+1; i++){
            if(!set.contains(i)) return i;
        }
        
        return nums.length+1;    
    }


OPTIMIZED, BEST SOLUTION

//TC: O(N)
//SC: O(1)

INTUTION, the missing element must come in the range from 1 to n+1 where n is length of array

1. IGNORE ELEMENT WE DONT CARE ABOUT (negatives and greater than n+1)
2. WALK through array and change all values that we have index based to a negative
3. check our final array until we find first psotive number 



public class Solution {
public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    if(nums == null || n == 0) return 1; //first missing number is 1
    boolean containsOne = false; //need this flag to verify that 1 exists in the array before step 1
    
    //step 1 - set all values we dont care about to 1 (can be almost any value)
    for(int i=0; i<n; i++){
        if(nums[i]==1){
            containsOne = true;
        } else if(nums[i] <= 0 || nums[i] > n) { //we dont care about these elements
            nums[i]=1; //set them all to one
        }
    }
    
    if(containsOne == false) return 1; //edge,case our array has no 1
    
    //step 2 - use array as a map, that is 0 index based, and make all every
    //value we have a negative
    for(int i=0; i<n; i++){
        int index = Math.abs(nums[i]) -1; //ABS VALUE, because we are doing index based and dont want later negatives to mess this up
                                          //SUBTRACT ONE, because we are 0 based index
        
        if(nums[index]>0){ //ONLY FLIP if its positive, and dont want to flip again so need this check
            nums[index]*=-1; //MAKE NEGATIVE
        }
    }
    
    //step 3 - CHECK our array now, and once we find first positive, that is first missing number
    
    for(int i=0; i<n; i++){
        if(nums[i]>0){
            return i+1; //need i+1 because (1 is in 0 position, 2 is in 1 position, ... etc)
        }
    }
    
    //IF WE REACH HERE, all the elements are in the array, so first mising number is n+1
    return n + 1;
}
}