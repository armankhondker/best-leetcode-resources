Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. 
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


SINCE IT IS SORTED, we can use TWO POINTER APPROACH!!!


TC: O(N+K) to go through the array, N will always be bigger than K so O(N)
SC: O(K) to populate to result array

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length -1;
        
        while(right-left >= k){
            int mid = left + (right-left)/2;
            if(Math.abs(arr[left]-x) > Math.abs(arr[right]-x)){
                left++;
            } else {
                right--; 
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for(int i=left; i<=right; i++){
            res.add(arr[i]);
        }
        return res; 
        
    }
}


BEST SOLUTION, use binary search, harder to implement 

TC: O(logN + K) for the binary search on array of length N and res array of size k
SC: O(k) for the result array 

class Solution {
       public List<Integer> findClosestElements(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
             int mid = (left + right) / 2;
                if (A[mid] == A[mid + k]) {  // <---- NEED THIS SPECIAL CASE IN CASE THEY ARE BOTH EQUAL, to see
               								 //if we should go left or go right 
                    if (x > A[mid])
                         left = mid + 1;
                    else
                         right = mid;
            } else if (Math.abs(A[mid]-x) > Math.abs(A[mid+k]-x))
                left = mid + 1;
            else
                right = mid;
        }

        //LEFT POINTER WILL BE ON THE SMALLEST CLOEST ELEMENT TO K, so just need to add k elements after left!
        
        List<Integer> res = new ArrayList<>();
        for(int i=left; i<left+k; i++){
        	res.add(A[i]); 
        }
        return res; 
    }
}