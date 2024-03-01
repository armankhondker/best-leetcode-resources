// Implement int sqrt(int x).

// Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

// Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

// Example 1:

// Input: 4
// Output: 2
// Example 2:

// Input: 8
// Output: 2
// Explanation: The square root of 8 is 2.82842..., and since 
//              the decimal part is truncated, 2 is returned.

BINARY SEARCH 

For x≥2 the square root is always smaller than x/2 and larger than 0 : ----> 0 < a < x/2 
Since a is an integer, the problem goes down to the iteration over the sorted set of 
integer numbers. Here the binary search enters the scene.

Algorithm 

1. If x < 2, return x.

2. Set the left boundary to 2, and the right boundary to x / 2.

3. While left <= right:

		Take num = (left + right) / 2 as a guess. Compute num * num and compare it with x:

		If num * num > x, move the right boundary right = pivot -1

		Else, if num * num < x, move the left boundary left = pivot + 1

		Otherwise num * num == x, the integer square root is here, let's return it                                                 

4. Return right

TC: O(logn)
SC: O(1)

class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    long num;
    int pivot, left = 2, right = x / 2;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      num = (long)pivot * pivot;
      if (num > x) right = pivot - 1;
      else if (num < x) left = pivot + 1;
      else return pivot;
    }

    return right;
  }
}