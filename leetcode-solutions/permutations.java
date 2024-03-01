Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]


//TC: O(n*n!) because we have to first choose one of the n numbers, then choose one of n-1 numbers,
//then one of n-2 numbers, ...etc.  ALSO, checking if current perumtation already has this element,
//with .contains on an arraylist will take O(N) time

//SC: O(n) because we the recursive stack wil go a maxdepth of n, 
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), res);
        return res;
    }
    
    public void backtrack(int[] nums, List<Integer> current, List<List<Integer>> res ){
        if(current.size() == nums.length){
            res.add(new ArrayList<>(current)); 
        }
        else {
        for(int i = 0; i<nums.length; i++){
            if(current.contains(nums[i])) continue; // element already exists, skip, CONSTRAINT ,,CAN USE HASHSET HERE
            current.add(nums[i]);
            backtrack(nums, current, res);
            current.remove(current.size()-1);
        }
        }
    }
}


SAVE TIME, BY USING A HASHSET, TO SEE IF CONTAINED 

//TC: O(n!)
//SC: O(n)
s
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        backtrack(ret, new ArrayList<>(), new HashSet<>(), nums);

        return ret;

    }
    
    private void backtrack(List<List<Integer>> ret, List<Integer> tmpList, Set<Integer> tmpSet, int[] nums) {
        if (tmpSet.size() == nums.length) {
            ret.add(new ArrayList<>(new ArrayList<>(tmpList)));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (tmpSet.contains(nums[i])) continue;
            
            tmpSet.add(nums[i]);
            tmpList.add(nums[i]);
            
            backtrack(ret, tmpList, tmpSet, nums);
            
            tmpSet.remove(tmpList.get(tmpList.size()-1));
            tmpList.remove(tmpList.size()-1);
        }
    }