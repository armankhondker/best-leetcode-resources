Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits,
 and repeat the process until the number equals 1 (where it will stay), or it loops endlessly 
 in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

// Time complexity : O(logn)
// Space complexity : O(logn)

 class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> sumsSeen = new HashSet<Integer>();
        
        while(n!=1) //loop while we dont have a happy number 
        {
                int current = n;
                int sum =0;
                while(current!=0)
                {
                    sum+= (current%10)*(current%10); //add the last digit squares 
                    current=current/10; //get rid of last digit 
                }
            
            if(sumsSeen.contains(sum)) //we have a cycle 
            {
                return false; 
            }
            sumsSeen.add(sum); //add this sum to hashset of seen sums 
            n=sum;  //iterate on next sum 
        }
        
        return true; //we have reached happy number status 
    }
}

//BEST SOLUTION HARD TO IMPOLEMNT
//USE FAST AND SLOW POINTER TO DETCECT CYCLES!
 
//O(logn) Time complexity 
//O(1) Space complexity 

class Solution {

     public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}