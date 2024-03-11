Run-length encoding is a compression algorithm that allows for an integer array nums with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded. Each encoded[i] = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the value that is repeated freqi times.

For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]]. 
Another way to read this is "three 1's followed by five 2's".
The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:

Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
Compress prodNums into a run-length encoded array and return it.


You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively. 
Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.

Return the product of encoded1 and encoded2.


NAIVE IS to unencode, do multiplication, then reencode.
This time complexity will be O(4(N+M)) so we can do much better


TWO POINTERS!!! can do in one pass on M+N

TC: O(N + M) to itereate on both M + N

class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        LinkedList<List<Integer>> product = new LinkedList<>();

        int lengthEncoding1 = encoded1.length, lengthEncoding2 = encoded2.length;
        int indexEncoding1 = 0, indexEncoding2 = 0;

        while (indexEncoding1 < lengthEncoding1 && indexEncoding2 < lengthEncoding2) {
            int[] pair1 = encoded1[indexEncoding1];
            int[] pair2 = encoded2[indexEncoding2];

            int productVal = pair1[0] * pair2[0];
            int freq = Math.min(pair1[1], pair2[1]);

            pair1[1] -= freq;
            pair2[1] -= freq;

            if (pair1[1] == 0) indexEncoding1++; //exhausted the frequency, increment pointer
            if (pair2[1] == 0) indexEncoding2++;

        if (product.isEmpty() || product.getLast().get(0) != productVal) {
                product.add(Arrays.asList(productVal, freq));
            } else {
                product.getLast().set(1, product.getLast().get(1) + freq); //just increment the frequency instead of adding the same product
            }
        }
        return product;
    }
}
