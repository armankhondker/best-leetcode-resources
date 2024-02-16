Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?


//O(N) to loop through all elments
//O(N) for hashmap space 

class Solution {
    public int singleNumber(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for(int x: nums)
        {
            if(seen.contains(x)) //if we already have x remove it, at end only one element will 
                                 //be left in the set 
            {
                seen.remove(x);
            }
            else
            {
                seen.add(x);
            }
        }
        
        for(int i: seen) //should only be one element in seen hashset so return it
        {
            return i;
        }
        return -1;
    }
}


//BEST SOLUTION USE XOR LOGIC
// For anyone who didn't understood why this works here is an explanation. 
// This XOR operation works because it's like XORing all the numbers by itself.
//  So if the array is {2,1,4,5,2,4,1} then it will be like we are performing this operation

// ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5.

// Hence picking the odd one out ( 5 in this case).

Time complexity : O(n). We only iterate through nums, 
so the time complexity is the number of elements in nums.

Space complexity : O(1)

class Solution {
    public int singleNumber(int[] nums) {
       
    int result = 0;
    for (int i = 0; i<nums.length; i++)
    {
		result ^=nums[i];
    }
	return result;
}
    }