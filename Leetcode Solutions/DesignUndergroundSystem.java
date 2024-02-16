// Implement the class UndergroundSystem that supports three methods:

// 1. checkIn(int id, string stationName, int t)

// A customer with id card equal to id, gets in the station stationName at time t.
// A customer can only be checked into one place at a time.
// 2. checkOut(int id, string stationName, int t)

// A customer with id card equal to id, gets out from the station stationName at time t.
// 3. getAverageTime(string startStation, string endStation) 

// Returns the average time to travel between the startStation and the endStation.
// The average time is computed from all the previous traveling from startStation to endStation that 
// happened directly.
// Call to getAverageTime is always valid.
// You can assume all calls to checkIn and checkOut methods are consistent. That is, if a customer gets
//  in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen in 
//  chronological order.

 Real-world issues:
 1. It would not be realistic to store this data in voltaile computer memory(computers fail), would need to
 store to data in a permanant place (database)
 2. Scalability, in a large city like tokyo with millions of trips per day, there will be thousands of 
 check ins every second, to make this work we would need multiple computers, this introduces concurrency issues
 
Note: 
 In Java 8, a Pair<V, K> is added in javafx.util package. The class represent key-value pairs 
 and supports very basic operations like getKey(), getValue(), hashCode(), equals(java.lang.Object o).

//TC: O(1) constant time for all the operations 
//SC: O(P + S^2) 
// 1. the check in hasmap an entry for each time that a passenger checks in and will grow 
//with the number of total passegners O(P) maximum space, where P is number of unique passengers 
//2. The map with checkouts has one entry for each pair of stations with that has had at least one
//passenger start/end journey at those stations. In worst case, we can expect every possible pair of 
 //stations to have an entry in hasmap, therefore S^2 combinations, where s is number of stations 
 
class UndergroundSystem {
    HashMap<String, Pair<Integer, Integer>> checkoutMap = new HashMap<>(); // Route - {TotalTime, Count}
    HashMap<Integer, Pair<String, Integer>> checkInMap = new HashMap<>();  // Uid - {StationName, Time}
    
	public UndergroundSystem() {}
    
	public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }
    
	public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkIn = checkInMap.get(id);
        String route = checkIn.getKey() + "_" + stationName;
        int totalTime = t - checkIn.getValue(); //time this trip took
        Pair<Integer, Integer> checkout = checkoutMap.getOrDefault(route, new Pair<>(0, 0)); 
        						//get the checkout to update, or add a new pair if key doesnt exist
        checkoutMap.put(route, new Pair<>(checkout.getKey() + totalTime, checkout.getValue() + 1));
        			//update the route, with new total time, and total count this route has been taken
    }
    
	public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "_" + endStation;
        Pair<Integer, Integer> checkout = checkoutMap.get(route);
        return (double) checkout.getKey() / checkout.getValue();
    }
}

DESIGN DECISION 
Storing total time vs average time for each possible route!!!

// Saving the total time vs the average time

// One design decision we need to make is whether to store the total-time or the average-time for 
// each possible route. The benefit of storing the average-time is that the system will be able to store a 
// lot more data before being affected by overflow (remember, the total-count is always eventually going to 
// 	be affected). The downside of storing average-time though is that we need to update it with division 
// every time a new journey is made on that route. This leads to compounded floating-point error.

// Given that overflow isn't an issue with the problem constraints we're given here, it's safest to store 
// the total-time so that we can avoid the floating-point error. In a real world system, you would use a 
// Decimal library that supports arbitrary precision and doesn't suffer from floating-point error.