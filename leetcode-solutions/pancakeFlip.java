// Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, 
// then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips 
// (doing them one after another in succession) to sort the array A.

// Return the k-values corresponding to a sequence of pancake flips that sort A.  
// Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.



SOLUTION


// Keep an end pointer (starting from last position to 0-th index) for holding the current max after each iteration until the pointer reaches 0.

// There are basically 3 steps in each iteration:

// 1. Find the current max within [0, curEndIndex].
// 2. Do a flip (from 0 to curMaxIndex) to put it to the front.
// 3. Flip again to put it to endIndex. endIndex--.


//TC: O(N^2) outer loop takes
//SC: O(n) to store result


class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length < 2) {
            return res;
        }
        
        int curEndIndex = A.length - 1;
        while (curEndIndex > 0) {
            int curMaxIndex = getCurMaxIndex(A, curEndIndex);
            if (curMaxIndex != curEndIndex) {
                flip(A, 0, curMaxIndex);  // get the cur max to the 0-th index.
                res.add(curMaxIndex + 1);
                flip(A, 0, curEndIndex);  // get the cur max to curEndIndex so everything after curEndIndex (inclusive) is sorted
                res.add(curEndIndex + 1);
            }
            curEndIndex--;
        }
        return res;
    }
    
    private int getCurMaxIndex(int[] A, int endIndex) {
        int max = A[0];
        int maxIndex = 0;
        for (int i = 1; i <= endIndex; i++) {
            if (A[i] > max) {
                max = A[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private void flip(int[] A, int start, int end) {
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }
        return;
    }
}