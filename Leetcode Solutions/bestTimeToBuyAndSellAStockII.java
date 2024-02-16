
// Say you have an array prices for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
// (i.e., buy one and sell one share of the stock multiple times).

// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock 
//     before you buy again).



INTUITON, we want to pay attention to peaks and valleys, BUY AT VALLEY, SELL AT PEAK 

//TC: O(N)
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