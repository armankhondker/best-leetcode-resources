 //Given n non-negative integers representing an elevation map where the width of each bar is 1, 
//compute how much water it is able to trap after raining.

//can use dynamic programming where we build up subproblems but will take O(N) space for dp array
//instead can use two pointers for constant space 


BRUTE FORCE, iterate every possible window, keeping track of the left max and right max
Then, add min(leftmax, rightmax) - height[i] to our answer
//TC: O(n^2)
//SC: O(1)

public int trap(int[] height) {
    int totalWater = 0;
    for (int i = 0; i < height.length; i++) {
        int leftMax = height[i];
        for (int j = 0; j < i; j++) {
            leftMax = Math.max(leftMax, height[j]);
        }
        int rightMax = height[i];
        for (int j = i; j < height.length; j++) {
            rightMax = Math.max(rightMax, height[j]);
        }
        int waterLevel = Math.min(leftMax, rightMax);
        totalWater += waterLevel - height[i];
    }
    return totalWater;
}


KEY INSIGHT, the amount of water at any point is determined by right and left max

TC: O(N)
SC: O(N) to store the array of left maxes and right maxes 

public int trap(int[] height) {
    if (height.length == 0) return 0;
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];
    int currentLeftMax = height[0];
    for (int i = 0; i < height.length; i++) {
        currentLeftMax = Math.max(currentLeftMax, height[i]);
        leftMax[i] = currentLeftMax;
    }
    int currentRightMax = height[height.length - 1];
    for (int i = height.length - 1; i >= 0; i--) {
        currentRightMax = Math.max(currentRightMax, height[i]);
        rightMax[i] = currentRightMax;
    }
    int totalWater = 0;
    for (int i = 0; i < height.length; i++) {
        int waterLevel = Math.min(leftMax[i], rightMax[i]);
        totalWater += waterLevel - height[i];
    }
    return totalWater;
}

TWO POINTER APPROACH, CONSTANT SPACE 

// Algorithm (NOT DONE)
// initialize left pointer to 0 and right pointer to size -1
// while(left< right)
//     if height[left] smaller than height[right]
//         if height[left] > leftMax : update left max 
//         else add left[max ]


 public int trap(int[] height) {
        // time : O(n)
        // space : O(1)
        if (height.length==0) return 0; 
        int left = 0, right = height.length-1; 
        int leftMax=0, rightMax=0; 
        int ans = 0; 
        while (left < right) {
            if (height[left] > leftMax) leftMax = height[left]; 
            if (height[right] > rightMax) rightMax = height[right];
            if (leftMax < rightMax) {
                ans += leftMax-height[left];      //need to add the leftMax - height of current building
                left++; 
            } else {
                ans += rightMax-height[right];     //need to add this to total water
                right--; 
            }
        }
        return ans; 
    }

