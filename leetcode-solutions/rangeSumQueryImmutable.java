Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.


USE A PREFIX SUM!!!


TC: O(N)
SC: O(1)

class NumArray {
    int[] prefix;
    public NumArray(int[] nums) {
        prefix = new int[nums.length]; 
        prefix[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            prefix[i] = prefix[i-1] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left == 0){
            return prefix[right];
        }
        return prefix[right] - prefix[left-1]; //subtract out the values right before our range
    }


    // [-2 0 3 -5 2 -1 ].    [-2 0 1 -4 -2 -3]
 }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */