// The i-th person has weight people[i], and each boat can carry a maximum weight of limit.

// Each boat carries at most 2 people at the same time, provided the sum of the weight of those 
// people is at most limit.

// Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be 
// carried by a boat.)

// Intuition

// If the heaviest person can share a boat with the lightest person, then do so. Otherwise, 
// the heaviest person can't pair with anyone, so they get their own boat.

// The reason this works is because if the lightest person can pair with anyone, they might as well pair with the heaviest person.

//Use two pointers to iterate through 
//Then, as described above, if the heaviest person can share a boat with the lightest person
// (if people[j] + people[i] <= limit) then do so; otherwise, the heaviest person sits in their own boat.


//TC: O(nlogn) to sort the array
//SC: O(1)

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i=0;
        int j=people.length-1;
        
        int totalBoats =0;
        
        while(i<=j)
        {
            if(people[i] + people[j] <= limit)
            {
                totalBoats++;
                i++;
                j--;
            }
            else
            {
                j--;
                totalBoats++;
            }
        }
        return totalBoats; 
    }
}