Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]


BACKTRACKING PROBLEM!!!

INTUTION, subsets are just you either TAKE the element we are on or DONT TAKE the element 

Therefore, timecomplexity should be O(2^n) because every element can be absent or present 


//TC: O(n*2^n) for all the recursive calls where we will have a total of 2^n permutations, 
//and copying each list to output takes an extra O(n) time in each loop,

//SC: O(n*2^n) because there are 2^n subsets for length N, and every subset needs O(n) space to store
//the temp array

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<Integer>(), ans);
        return ans; 
    }
    public void generateSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> subsets){
        subsets.add(new ArrayList<>(current));  //FIRST THING TO DO IS ADD CURRENT SUBSET to our answer, need this to be NEW
                                                //because we are going to modify this current 
        
        //simulate TAKING and NOT TAKING, current number we are on
        for(int i=index; i<nums.length; i++){
             current.add(nums[i]); //TAKING, just add current number to our current subset
             generateSubsets(i+1, nums, current, subsets);
             current.remove(current.size()-1); //BACKTRACK, simulate NOT TAKING IT and go back
        }
    }
}

//SAME CODE CLEANER

public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    list.add(new ArrayList<>(tempList));
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}