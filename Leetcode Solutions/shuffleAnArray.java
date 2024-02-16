Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of 
//[1,2,3] must equally likely to be returned.

solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();


BRUTE FORCE
If we put each number in a "hat" and draw them out at random, 
the order in which we draw them will define a random ordering.

1. Mechanically, this is performed by copying the contents of array into a second auxiliary array
named aux before overwriting each element of array with a randomly selected one from aux. 

2. After selecting each random element, it is REMOVED from aux to prevent duplicate draws. The 
implementation of reset is simple, as we just store the original state of nums on construction.

The correctness of the algorithm follows from the fact that an element (without loss of generality) 
is equally likely to be selected during all iterations of the for loop. 

// TC: O(N^2) because in the shuffle we will iterate on the entire array, and then all n elements
// we will have to list.remove() which is O(N) cost operation 
// SC: O(N) for the auxiliary array

class Solution {
    private int[] array;
    private int[] original;

    private Random rand = new Random();

    //helper function not main part of code
    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }
    
    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }
    
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }
}

BEST SOLUTION - cut time TC/SC of shuffle by swapping elements in array itself (FISHER/YATES ALGORITHM)
We can avoid use of auxillary array and get rid of time complexity for removing 

TC: O(N) to loop through the entire array when shuffling
SC: O(1)


1. The Fisher-Yates algorithm is remarkably similar to the brute force solution. 
On each iteration of the algorithm, we generate a random integer between the current 
index and the last index of the array. 

2. Then, we swap the elements at the current index and the chosen index - this simulates 
drawing (and removing) the element from the hat, as the next range from which we select a 
random index will not include the most recently processed one. 

// I saw some people asking why this algorithm is correct. Here is my understanding. Hope it helps.

// Proof: Suppose this algorithm works, i.e. for each position j (starting from 0), the 
// probability of any number in the range[0,j] to be at position j is 1/(1+j).

// Let's look at int i = random.nextInt(j + 1):
// (1) If i == j, nums[j] does not need to change its position, which has probability 1/(1+j).
// (2) if i !=j, nums[j] needs to be swapped with nums[i]. The probability of any number 
// x in the range [0,j-1] to be at position j = nums[j] changes its position * x is at position i
// = (1-1/(1+j)) * (1/j) = 1/(1+j)

// Each number has equal probability to be at any position.

public class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] a = nums.clone();
        for(int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1); //we need to go one further element since its exclusive
            swap(a, i, j);
        }
        return a;
    }
    
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

