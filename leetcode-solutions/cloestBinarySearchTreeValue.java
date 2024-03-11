Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. 
If there are multiple answers, print the smallest.


Run binary search, since we are given a BST
NOTE doesn't take care of printing smallest case

TC: O(LogN)
SC: O(1)

class Solution {
    public int closestValue(TreeNode root, double target) {
        //binary search 
        if(root == null) return 0; 
        int closestVal = root.val; 
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - closestVal)){
                closestVal = root.val; //UPDATE newly found closest value
            } 


            if (root.val > target){ //binary serach on left
                root = root.left;
            } else {		//binary search on right
                root = root.right;
            }
        }
        return closestVal;
    }
}

FIX TO HANDLE PRINTING SMALLEST IF DIFFERENCE IS THE SAME

class Solution {
    public int closestValue(TreeNode root, double target) {
        int val = root.val;
        int closest = root.val; 
        while(root!=null){
            val = root.val;
            if(Math.abs(target - val) < Math.abs(target-closest)){
                closest=val;
            } else if(Math.abs(target - val) == Math.abs(target-closest) && val < closest){
                closest=val;
            }
            if(root.val > target){
                root=root.left;
            } else {
                root=root.right;
            }

        }
        return closest;
    }
}