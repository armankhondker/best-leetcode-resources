// Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

// Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  
// Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

// Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
// Output: [2,2,2,1,4,3,3,9,6,7,19]


// Count number of elements of arr2 in arr1 using map. Those that are not in arr2 - add it 
// to the end of resulting array, this part is not sorted yet.

// Iterate over arr2 - this gives correct order, use custom pointer for that. Put every element of 
// arr2 number of times that is stored in the map. After that our custom pointer points right to the first
//  lement in the series that was unique for arr1. For that part do the sort.

// Complexity, n - length of arr1, m - length of arr2
// time is O(max(n, m)) for loops, O(max(n, m)) for lookup in the map (n times O(1)) 
//and O(nlgn) for sorting that unique part of arr1, so oeverall - O(nlgn).
// Memory - O(m) for the Map, result array I don't count.

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //create map for counting numbers in arr1. Initialize everything with zeroes
        Map<Integer, Integer> m = new HashMap();
        for (int num : arr2) {
            m.put(num, 0);
        }
        int last = arr1.length - 1;
        int[] res = new int[arr1.length];
        //iterate over arr1  and count numbers of time this element is in arr1
        for (int num : arr1) {
            //if number is from arr2 - increment count
            if (m.containsKey(num))
                m.put(num, m.get(num) + 1);
            //otherwise add element to the end of res and decrement the pointer
            else {
                res[last--] = num;
            }
        }
        //iterate over arr2, fill elements in res based on it's count 
        int p = 0;
        for (int num : arr2) {
            int c = m.get(num);
            for (int i = 0; i < c; i++) {
                res[p++] = num;
            }
        }
        //now sort the leftovers - p points to the first element in series of those from arr2 that are not in arr1 
        Arrays.sort(res, p, res.length);
        return res;
    }