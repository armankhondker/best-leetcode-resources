// Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999




TC:
SC:

class Solution {
    public String intToRoman(int num) {
       // if (num < 1 || num > 3999) return ""; depends on constrains of the problem
    	
	StringBuilder result = new StringBuilder(); 
	String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	
	int i = 0;
            //iterate until the number becomes zero, NO NEED to go until the last element in roman array
	while (num >0) {
		while ( num >= values[i]) {
			num -= values[i];
			result.append(roman[i]);
		}
		i++;
	}
	return result.toString();
    }
}