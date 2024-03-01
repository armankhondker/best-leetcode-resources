
//time complexity ~O(3^n) because most digits map to 3 letters. Also, for every digit we need about 3 resursive calls
//space complexity ~O(3^n + 4^M) have to keep 3^n x 4^m solutions 
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>(); 
        if(digits == null || digits.length()==0) return res; 
        String[] mappings = {
            "0",
            "1",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
           "pqrs",
            "tuv";
            "wxyz"
        };
        letterCombinationsRecursive(res, digits, "", 0, mappings);
        return res; 
        
    }
    public void letterCombinationsRecursive(List<String> result, String digits, String current, //current is string we will populate
                                           int index, String[] mappings)
    {
        if(index==digits.length()){  //we have created a valid answer 
            result.add(current); 
            return;
        }
        //String to store all the letters that the current number is mapped to 
        String letters = mappings[digits.charAt(index) - '0']; //EX. use this to 
                                                               //Convert string '4' to number 4. 
        for(int i=0; i<letters.length(); i++)
        {
        letterCombinationsRecursive(result,digits,current+letters.charAt(i),index+1,mappings); //recursive call to populate result 
        }
    }
}