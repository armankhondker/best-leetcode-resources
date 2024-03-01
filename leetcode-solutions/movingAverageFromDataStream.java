Given a stream of integers and a window size, calculate the moving average of all integers in 
the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3


//NAIVE, use an arraylist, but we wont be able to maintain the window that we want

//TC: O(1) to get the next
//SC: O(size) where size is the max size of the queue!!!!


class MovingAverage {

    /** Initialize your data structure here. */
      Queue<Integer> q;
    double sum = 0;
    int size;

    public MovingAverage(int s) {
        q = new LinkedList();
        size = s;
    }
    
    public double next(int val) {
        if(q.size() == size){
            sum = sum - q.poll();
        }
        q.offer(val);
        sum += val;
        return sum/q.size();
    }
}