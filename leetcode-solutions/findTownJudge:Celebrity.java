
In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.



//Intuition, create a trust array for every person
//increment trust values as you increment through trust, then check at end if anyone
//has a trust value of N-1, THIS MEANS they are the judge!!


//N is amount of people in town, T is length of trust array 
//TC: O(N + T) to iterate through the trust count array createdand check if count == N-1, and T to iterate through trust array input,
//SC: O(N) to hold the array of trust values

class Solution {
    public int findJudge(int N, int[][] trust) {
        
        int [] count = new int[N+1]; //array to hold "trust values" of every person
                                    //we will populate, and then if anyone at end has n-1 trusts, they are judge
                                    //index starts at 1 
        
        for(int[] t: trust)
        {
            count[t[0]] --; //this person will never be able to be judge again, so discount them from consideration with this
            count[t[1]] ++; //increment trust value  
        }
        
        for(int i=1; i<=N; i++)
        {
            if(count[i] == N-1) 
                return i;
            
        }
        return -1; //no one is the judge 
        
    }
}