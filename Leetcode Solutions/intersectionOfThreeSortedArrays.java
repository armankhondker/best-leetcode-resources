// Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array
//  of only the integers that appeared in all three arrays.


// Example 1:

// Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
// Output: [1,5]
// Explanation: Only 1 and 5 appeared in the three arrays.

THREE POINTERSS!!

//TC: O(N) where n is max of lenght between 3 arrays 
//SC: O(1)

class Solution {
      public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int k = 0;
        
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr3[k]) {
                j++;
            } else k++;
        }

        return result;

    }
}