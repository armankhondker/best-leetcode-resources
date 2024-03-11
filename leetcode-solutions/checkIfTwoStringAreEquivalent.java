Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

BUILD UP TWO STRINGS and compare, can use stringbuilder

TC: O(N)
SC: O(N)
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(String s: word1){
            sb.append(s);
        }
        for(String s: word2){
            sb2.append(s);
        }
        
        return sb.toString().equals(sb2.toString());
    }
}


OPTMIZED, TWO POINTER, USE two nested pointers

1) set of pointers for ith string we are on
2) set of pointers for the first char we are currently on, which we will reset to 0

TC: O(N)
SC: O(1)

public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
	int p1 = 0, p2 = 0; // inner pointer for chars
	int w1 = 0, w2 = 0; // outer pointer for the string we are on

	while (w1 < word1.length && w2 < word2.length) {
		String curr1 = word1[w1], curr2 = word2[w2];

		if (curr1.charAt(p1) != curr2.charAt(p2)) return false;

		if (p1 < curr1.length() - 1) {
			p1++;
		} else {
			p1 = 0;
			w1++;
		}

		if (p2 < curr2.length() - 1) {
			p2++;
		} else {
			p2 = 0;
			w2++;
		}
	}

	return w1 == word1.length && w2 == word2.length;