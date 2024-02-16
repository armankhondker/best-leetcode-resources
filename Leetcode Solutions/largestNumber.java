Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"


INTUTION, NEED TO USE A COMPARATOR, to decide which should come first,
in concatenation

EX. 
String s1 = "9";
String s2 = "31";

String case1 =  s1 + s2; // 931
String case2 = s2 + s1; // 319


//TC: O(knlogn) 
//SC: O(n) for the array of strings, for each num in input 

The time complexity is O(knlogn)? The typical sort takes O(nlogn) when the comparison between two 
elements takes O(1). Then in this case each comparison takes O(k), I guess the overall time complexity 
should be O(knlogn)

public class Solution {
     public String largestNumber(int[] num) {
		if(num == null || num.length == 0)
		    return "";
		
		// Convert int array to String array, so we can sort later on
		String[] s_num = new String[num.length];
		for(int i = 0; i < num.length; i++)
		    s_num[i] = String.valueOf(num[i]);
			
		// Comparator to decide which string should come first in concatenation
		Comparator<String> comp = new Comparator<String>(){
		    @Override
		    public int compare(String str1, String str2){
		        String s1 = str1 + str2;
			String s2 = str2 + str1;
			return s2.compareTo(s1); // reverse order here, so we can do append() later
		    }
	        };
		
		Arrays.sort(s_num, comp);
                // An extreme edge case by lc, say you have only a bunch of 0 in your int array
                if(s_num[0].charAt(0) == '0')
                    return "0";
            
		StringBuilder sb = new StringBuilder();
		for(String s: s_num)
	            sb.append(s);
		
		return sb.toString();
		
	}
}