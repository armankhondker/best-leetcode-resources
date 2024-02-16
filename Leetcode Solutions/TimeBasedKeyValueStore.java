Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").


Approach 1: HashMap + Binary Search
Intuition and Algorithm

For each key we get or set, we only care about the timestamps and values for that key. 
We can store this information in a HashMap.

Now, for each key, we can binary search the sorted list of timestamps to find the relevant value for that key


//Use hashmap with key -> value = entry, time
//Use binary search for the get operation
//
//Q: how is the list sorted? 
//The timestamps in "set" operation are strictly increasing, making it sorted,
//thus can use binary serach 

// Complexity: O(1) for each set operation, and O(logN) for each get operation, 
// where N is the number of entries in the TimeMap.

// Space Complexity: O(N)


class TimeMap {

    /** Initialize your data structure here. */
    Map<String, List<Data>> map;  //use hashmap of type data
    public TimeMap() { 
        map = new HashMap<String, List<Data>>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
        map.get(key).add(new Data(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        return binarySearch(map.get(key), timestamp);
    }
    
    protected String binarySearch(List<Data> list, int time) {
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (list.get(mid).time == time) return list.get(mid).val;
            if (list.get(mid).time < time) {
                if (list.get(mid+1).time > time) return list.get(mid).val;
                low = mid + 1;
            }
            else high = mid -1;
        }
        return list.get(low).time <= time ? list.get(low).val : "";
    }
}

class Data {
    String val;
    int time;
    Data(String val, int time) {
        this.val = val;
        this.time = time;
    }
}