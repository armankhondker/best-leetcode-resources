Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.


// This seems to be a very difficult question with whole lot of restriction, but if you break down the question and tackle every sub-problem individually, it might not seem as hard as it is.

// First, we need to check whether the end result is positive or negative. Two cases will lead to negative case which is when dividend and divisor has different signs. Alright, first part is done.

// Second part is to deal with overflow. You know, there are so many corner cases which will lead to overflow. So it is better to convert them to long first, and convert it back to integer when returning the value.

// Alright, now we get to the main part. We know that division is actually the backward of multiplication, for example , 20 / 5 = 4 can be seen as 4 * 5 = 20. Here what we are going to do is to find the multiplication. We set tmp as divisor (5) and set count to 1. As long as the tmp is less than or equal to dividend (20), we left shift << which is same as multiply 2 but without using multiplication.

// 1st loop --- tmp = 10 , count = 2 
// 2nd loop --- tmp = 20, count = 4
// 3rd loop --- tmp = 40, count = 8 (exit the loop)
// Now we right shift both tmp and count by 1, which gives us result of 4 . After subtraction of 20 from dividend, which gives us dividend = 0 and that we break out the outer loop and get to the last part.

// Finally, we gotta check if the sign is positive or negative. If it is negative, then we apply negation ~result + 1 (two's complement) to get the negative result (why not just result * -1 ? Well, critics might say you use multiplication -_-!!! (lol jk). Also make sure to check if result is overflow, because you know, leetcode is pretty strict to corner cases as well.

// Another example : 10 / 3 = 3

// 1st outer loop
// --------------

// 1st inner loop --- tmp = 6 , count = 2
// 2nd inner loop --- tmp = 12, count = 4 (exit the inner loop, result = 0 + (4 >> 1) = 2)

// dividend = 10 - (12 >> 1) = 10 - 6 = 4 (4 >  divisor, so here we go second outer loop)

// 2nd outer loop
// --------------

// 1st inner loop --- tmp = 6, count = 2 (exit the inner loop, result = 2 + (2 >> 1) = 3)

// dividend = 4 - (6 >> 1) = 4 - 3 = 1( divisor > 1, exit outer loop, return result)
// Credits to @HelloWorld123456. Here is the simplified code:



//TC: O(logn ^2 ) for each of the for loops , which goes to O(logn)
//SC: O(1) space 
public int divide(int dividend, int divisor) {
    boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ? true : false;
    long absDividend = Math.abs((long) dividend);
    long absDivisor = Math.abs((long) divisor);
    long result = 0;
    while(absDividend >= absDivisor){
        long tmp = absDivisor, count = 1;
        while(tmp <= absDividend){
            tmp <<= 1;
            count <<= 1;
        }
        result += count >> 1;
        absDividend -= tmp >> 1;
    }
    return  isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
}