Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.




BRUTE FORCE

TC: O(N^3) on the outer for each loop we walk throguh all elements O(N), on the while loop we can 
walk through the rest of the entire array O(N), and in the function call, we are doing O(N) work
to see if the array has that element O(N^3) total time complexity 
SC: O(1)

    public int longestConsecutive(int[] nums) {
        int longestStreak = 0; //keep track of res
        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;  //current streak we are on

            while (arrayContains(nums, currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }

            longestStreak = Math.max(longestStreak, currentStreak);
        }
        return longestStreak;
    }

       private boolean arrayContains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }

        return false;
    }
    

SORTING SOLUTION 
1. sort the array
2. elements next to each other are ptoential items for sequence

TC: O(NlogN) for SORTING
SC: O(1) assuming we can modify input array, if not then O(N) to make copy of input array

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) { //take care of repeats 
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);
    }
}


BEST SOLUTION - HASHSET and intellgient sequence building

Brute force solution was on the right track, can use a hashset to improve performance

This optimized algorithm contains only two changes from the brute force approach: 
1. the numbers are stored in a HashSet (or Set, in Python) to allow O(1) lookups 
2. we only attempt to build sequences from numbers that are not already part of a longer sequence. 
This is accomplished by first ensuring that the number that would immediately precede 
the current number in a sequence is not present, as that number would necessarily be 
part of a longer sequence.

TC: O(N) allow it looks quadratic, you have to realize that the inner while loop, can only run
N iterations during the entire algorithm. Therefore, it is more O(N+N) than O(N^2)
SC: O(N) for the hashset to allow constant look ups


  public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>(); //so we can have O(1) look ups
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
