Say you have an array prices for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you 
like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time 
(i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.





INTUTION, we need to find two peaks and two valleys in order to maximize profit, if not, just one big valley,
and peak 


//TC: O(N) single pass
//SC: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        //if we map out points, point of interests are peaks and valleys 
        int maxProfit = 0;
        int peak = prices[0];
        int valley = prices[0];
        
        for(int i=0; i<prices.length-1; i++){
            while(i<prices.length -1 && prices[i] >= prices[i+1]){ //find first valley 
                i++;
            }
            valley = prices[i];
            while(i<prices.length -1 && prices[i] <= prices[i+1]){ //find first peak 
                i++;
            }
            peak = prices[i];
            maxProfit+= peak-valley; 
        }
        return maxProfit;
        
    }
}