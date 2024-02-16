// Say you have an array for which the ith element is the price of a given stock on day i.

// If you were only permitted to complete at most one transaction 
// (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

// Note that you cannot sell a stock before you buy one.

 
//TC: O(N) to loop through length of prices array 
//SC: O(1)

class Solution {
       public int maxProfit(int[] prices) {
        int minSoFar = Integer.MAX_VALUE;   //keep track of minimum value so far we have seen 
        int maxProfit = 0;  
        for(int i=0; i<prices.length; i++)
        {
            if(prices[i] < minSoFar) minSoFar = prices[i]; //update the minSoFar
            else if(prices[i] - minSoFar > maxProfit)
            {
                maxProfit = prices[i] - minSoFar; 
            }
        }
        return maxProfit;
    }

}