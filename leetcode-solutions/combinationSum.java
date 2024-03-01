Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]



INTUTION, USE BACKTRACKING, to exhaust all possible combatinations of numbers,
NOTE, we can use the same element multiple times!!

N - length of candidates array, max number of leaves for a nodes
T - target value 
M - smallest number in candidates array

TC: O(N^(T/M+1)) this forumla for max number of nodes in an n-ary tree (i.e. n^maxdepth+1)


SC: O(T/M) where T is target and M is minimum number in candidates array (THIS IS THE MAX DEPTH OF THE TREE),
hence max depth of the call stack, 
****THIS IS FOR THE CASE where we keep adding the smallest element to the combination
Can just be O(T) if our candiadtes array has smallest value close to 1

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
       // Arrays.sort(candidates);  //DONT NEED IT TO BE SORTED, for this one, only to avoid duplicates
        backtrack(res, candidates, target, new ArrayList<>(), 0); //starting index is 0, sum is our target
        return res;
    }
    
    public void backtrack(List<List<Integer>> res, int[] candidates, int sum, List<Integer> current, int startIndex){
        if(sum < 0){
            return;
        }else if (sum == 0){
            res.add(new ArrayList<>(current)); 
        }
        else{
            for(int i=startIndex; i<candidates.length; i++){
                current.add(candidates[i]); 
                backtrack(res, candidates, sum-candidates[i], current, i); //NOT i+1 because we can 
                														   //REUSE the current element 
                current.remove(current.size()-1); //BACKTRACK, remove from our answer and go back to other combos 
            }
        }
        
    }
}