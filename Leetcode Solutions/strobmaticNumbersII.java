A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]


//TC:
//SC: 

class Solution {
    private final char[][] mappings = {{'0', '0'}, {'1','1'}, {'6','9'},
                                           {'9','6'}, {'8','8'}};
    public List<String> findStrobogrammatic(int n) {
        ArrayList<String> res = new ArrayList<>();
        if(n<1) return res;
        char [] current = new char[n]; //to hold all the char arrays we will generate with backtracking
        int low = 0;
        int high = n-1; //we will use two pointers to make the numbers
        helper(res, current, low, high);
        return res; 
    }
    
    public void helper(ArrayList<String> res, char [] current, int low, int high){
        if(low>high){ //we have passed our bounds, and need to popluate res
            if(current.length == 1 || current[0]!= '0'){ //dont add leading '0'!! , or don't add single '0'
                res.add(String.valueOf(current));
            }
            return;  
        }
        
        for(char [] map : mappings){
            //this is for middle char! we don't want it to be 6 or 9, just the ones that are pure strobomatics
            if(low == high && map[0]!=map[1]) continue;
            current[low] = map[0];
            current[high] = map[1]; 
            helper(res, current, low+1, high-1);
        }
         
        }
}