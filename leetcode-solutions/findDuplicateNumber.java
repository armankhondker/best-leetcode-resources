// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
// prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
// find the duplicate one.


//FIRST SOLUTION: Use hashset
//TC: O(n) to iterate of all elements
//SC: O(n) to store the hashmap

class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int x: nums)
        {
            if(!set.contains(x))
            {
                set.add(x);
            }
            else
            {
                return x; 
            }
            
        }
        return -1; 
        
    }
}

//SECOND SOLUTION, SORTED AND THEN LOOP THROUGH!!

//BETTER SOLUTION USING SLOW AND FAST POINTERS!!


// Use two pointers the fast and the slow. The fast one goes forward two steps each time, while the slow one 
// goes only step each time. They must meet the same item when slow==fast. In fact, they meet in a circle, 
// the duplicate number must be the entry point of the circle when visiting the array from nums[0]. Next we just 
// need to find the entry point. We use a point(we can use the fast one before) to visit form begining with one 
// step each time, do the same job to slow. When fast==slow, they meet at the entry point of the circle.

class Solution {
    public int findDuplicate(int[] nums) {
    
        if (nums.size() > 1)
	{
		int slow = nums[0];
		int fast = nums[nums[0]];
		while (slow != fast)
		{
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		fast = 0;
		while (fast != slow)
		{
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}
	return -1;
    }
}



