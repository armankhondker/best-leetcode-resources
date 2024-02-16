
// You are given a helper function bool knows(a, b) which tells you whether A knows B.
//  Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. 
//  Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */



//Intutiton, two pass solution!!
//1. First pass, assign candidate to first person, go through to get a potential candidate 
//2. second pass, check if conditions hold true for candidate (everyone knows candidate, candidate knows no one)

//TC: O(n) to loop through all potential candidates
//SC: O(1))

public class Solution extends Relation {
    public int findCelebrity(int n) {
        
    int candidate =0;
    for(int i=1; i<n; i++)
    {
        if(knows(candidate,i)) //if our candidate knows anyone, they can't be our candidate, so update our candidate 
        {
            candidate=i;
        }
    }
    for(int i=0; i<n; i++)
    {
        if(i==candidate) // dont want to consider himself, candidate wont know himself in array 
        {
            continue;
        }
        if(knows(candidate,i) || !knows(i, candidate))  //candidate can't be a celebrity if they know someone,
                                                        //or if anyone doesn't know candidate 
        {
            return -1;
        }
    }
        
     return candidate; //we have found celebrity    
    }
}

MORE REASONING
// The first loop is to find the candidate as the author explains. In detail, suppose the
//  candidate after the first for loop is person k, it means 0 to k-1 cannot be the celebrity, because they
//   know a previous or current candidate. Also, since k knows no one between k+1 and n-1, k+1 to n-1 can
//    not be the celebrity either. Therefore, k is the only possible celebrity, if there exists one.

// The remaining job is to check if k indeed does not know any other persons and all other persons know k

ANOTHER DETAIL

// the moment you realize a call to knows(i,j) eliminates either i or j the problem is solved. knows(i,j) == true 
// then i can't be a celeb. since a celeb knows nobody and knows(i,j) == false then j can't be a celeb since 
//everyone must know the celeb.