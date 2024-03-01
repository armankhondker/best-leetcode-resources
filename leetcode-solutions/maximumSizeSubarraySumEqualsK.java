




BRUTE FORCE:

//TC: O(N^2)
//SC: O(1)

int maxSubArrayLen(vector<int>& nums, int k) {
	int len = nums.size(); if (len == 0) return 0;
	int maxLen = INT_MIN, steps = 0, sum = 0;
	for (int i = 0; i < len; ++i)
	{
		sum = nums[i];
		for (int j = i + 1; j < len; ++j)
		{
			sum += nums[j];
			if (sum == k) maxLen = max(maxLen, j - i + 1);
		}
	}
	return maxLen == INT_MIN ? 0 : maxLen;
}


BEST SOLUTION
Use hashmap 

//TC: O(N)
//SC: O(N) space 


class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return 0;
        // key: prefix sum, value: right index of the leftmost subarray
        // that has this prefix sum value
        Map<Integer, Integer> counter = new HashMap<>();
        int maxLen = Integer.MIN_VALUE;
        int sum = 0;    // current running sum

        // add the trivial case of 0-length starting subarray to the map
        counter.put(0, -1); // treat its right index as -1

        // iterate through the array only once is enough
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // see if there is a prevuous array whose prefix sum is (sum - k)
            int target = sum - k;
            if (counter.containsKey(target)) {
                maxLen = Math.max(maxLen, i - counter.get(target));
            }
            // put current index into map if this is the first time we see the
            // current prefix sum value (which implies current index is leftmost)
            counter.putIfAbsent(sum, i);
        }
        // don't forget to check if there is such a subarray that meet the condition
        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}