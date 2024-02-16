//You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  
//You perform this action until there is one stick remaining.

//Return the minimum cost of connecting all the given sticks into one stick in this way.


//TC: O(nlogn) 
//SC: O(n) where n is length of sticks array, need space for heap

//Greed solution, want to always take the two sticks with the smallest value at any time and combine to minimize cost

    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //java minheap by default
        for (int s : sticks) {
            pq.offer(s);
        }

        int sum = 0;
        while (pq.size() > 1) { //loop until we only have one stick in minheap
            int two = pq.poll() + pq.poll();   //get two smallest on heap
            sum += two;
            pq.offer(two);   //add stick back into heap
        }
        return sum;
    }

