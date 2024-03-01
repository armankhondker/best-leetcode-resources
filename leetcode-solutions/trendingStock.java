public class TrendingStock {

	public void processStock(String stock) {
	}

	public String getTrendingStock() {
	}

}



USE PRIORITY QUEUE + MAP

public class TrendingStock {
    static long lastTradedUid = 0;
    HashMap<String,Stock>stockMap;
    PriorityQueue<Stock>stockOrderQueue;

    public TrendingStock()
    {
        this.stockMap = new HashMap<>();
        this.stockOrderQueue = new PriorityQueue<>();
    }

    //O(LOGN) where n is number of unique stocks in the priority queue
    public void processStock(String stock) {
        Stock currentStock  = stockMap.getOrDefault(stock,new Stock(stock));//Get stock if present in map
        if(stockOrderQueue.contains(currentStock))
            stockOrderQueue.remove(currentStock);//remove from queue if present.Need to remove so it can be re-inserted in correct place.

        currentStock.lastTraded = getNextUid();//increment next tradedUid.
        currentStock.frequency = currentStock.frequency+1; //Increment its frequency by 1.
        if(!stockMap.containsKey(stock))
            stockMap.put(stock,currentStock);//Add to map if absent.
        stockOrderQueue.offer(currentStock);//Offer to PriorityQueue.
    }

    public String getTrendingStock() {
        Stock currentStock = stockOrderQueue.poll();//remove most frequent from PriorityQueue
        stockMap.remove(currentStock.symbol);//Remove from map
        return  currentStock.symbol;
    }

    public long getNextUid()
    {
        lastTradedUid++;
        return lastTradedUid ;
    }
}

class Stock implements Comparable<Stock> {
    String symbol;
    Integer frequency;
    Long lastTraded;

    public Stock(String symbol)
    {
        this.symbol = symbol;
        this.lastTraded = Long.valueOf(0);
        this.frequency = 0;
    }
    @Override
    public int compareTo(Stock o) {
        //IF frequency is same sort in desc order based on lastTradedUid.
        if (this.frequency.equals(o.frequency)) {
            //Sort in descending order of last trade
            return -1 * this.lastTraded.compareTo(o.lastTraded);
        }
        return -1 * this.frequency.compareTo(o.frequency);
    }
}