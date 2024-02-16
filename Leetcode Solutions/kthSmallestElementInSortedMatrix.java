Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.


REMEMBMER, heaps take O(n) time to build, O(1) to FIND min/max, O(logn) to insert into heap 

SOLUTION 1 - USE A HEAP - Transform problem to Kth smallest element in M sorted lists!!

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}


//TC: O(min(K,N)+K*logN) because we will insert k elements into the heap   just say O(KlogN)
//SC: O(N) to store elements in the minheap 
class Solution {

  public static int findKthSmallest(int[][] matrix, int k) {
    PriorityQueue<Node> minHeap = new PriorityQueue<Node>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

    // put the 1st element of each row in the min heap
    // we don't need to push more than 'k' elements in the heap
    for (int i = 0; i < matrix.length && i < k; i++)
        minHeap.add(new Node(i, 0));

    // take the smallest (top) element form the min heap, if the running count is equal to k return the number
    // if the row of the top element has more elements, add the next element to the heap
    int numberCount = 0, result = 0;
    while (!minHeap.isEmpty()) {
      Node node = minHeap.poll();
      result = matrix[node.row][node.col];
      if (++numberCount == k)
        break;
      node.col++;
      if (matrix[0].length > node.col) // make sure we dont go out of bounds 
        minHeap.add(node);
    }
    return result;
  }
}



Solution 2 using Binary Search: O(N∗log(max−min))
Since each row and column of the matrix is sorted, is it possible to use Binary Search to find the 
Kth smallest number?

The biggest problem to use Binary Search in this case is that we don’t have a straightforward sorted array, 
instead we have a matrix. As we remember, in Binary Search, we calculate the middle index of the search 
space (‘1’ to ‘N’) and see if our required number is pointed out by the middle index; if not we either 
search in the lower half or the upper half. In a sorted matrix, we can’t really find a middle. Even if we 
do consider some index as middle, it is not straightforward to find the search space containing numbers bigger
 or smaller than the number pointed out by the middle index.

An alternate could be to apply the Binary Search on the “number range” instead of the “index range”. 
As we know that the smallest number of our matrix is at the top left corner and the biggest number 
is at the bottom lower corner. These two number can represent the “range” i.e., the start and the end 
for the Binary Search. Here is how our algorithm will work:

Start the Binary Search with start = matrix[0][0] and end = matrix[n-1][n-1].
Find middle of the start and the end. This middle number is NOT necessarily an element in the matrix.
Count all the numbers smaller than or equal to middle in the matrix. As the matrix is sorted, we can 
do this in O(N). While counting, we can keep track of the “smallest number greater than the middle” 
(let’s call it n1) and at the same time the “biggest number less than or equal to the middle” 
(let’s call it n2). These two numbers will be used to adjust the "number range" for the Binary Search in the
next iteration.

If the count is equal to ‘K’, n1 will be our required number as it is the “biggest number less than or 
equal to the middle”, and is definitely present in the matrix.
If the count is less than ‘K’, we can update start = n2 to search in the higher part of the matrix 
and if the count is greater than ‘K’, we can update end = n1 to search in the lower part of the matrix 
in the next iteration.

//TC: O((n+n)log(max - min))
//SC: O(1) space
public int kthSmallest(int[][] matrix, int k) {
        // num of rows and cols in matrix
        int rows = matrix.length, cols = matrix[0].length;
        // get the lowest and highest possible num, will shrink search space according to the two nums
        // [lo, hi] is our initial search range
        int lo = matrix[0][0], hi = matrix[rows - 1][cols - 1] ;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  maxNum = lo;
            // for each row, we r going to find # of nums < mid in that row
            for (int r = 0, c = cols - 1; r < rows; r++) {
                while (c >= 0 && matrix[r][c] > mid) c--;   // this row's c has to be smaller than the c found in last row due to the sorted property of the matrix 
                if (c >= 0) {
                    count += (c + 1); // count of nums <= mid in matrix
                    maxNum = Math.max(maxNum, matrix[r][c]); // mid might be value not in matrix, we need to record the actually max num;
                }
            }
            // adjust search range
            if (count == k) return maxNum;
            else if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        // 1) Q: Why we return lo at the end:
        //    A: Here lo=hi+1, for hi, we found <k elems, for lo, we found >=k elem, lo must have duplicates in matrix, return lo
        // 2) Q: Why lo exist in the matrix
        //    A: for lo which is only 1 more than hi, we could find some extra nums in matrix so that there r >=k elems, so lo it self must exist in the matrix to meet the requirement
        return lo;
    }