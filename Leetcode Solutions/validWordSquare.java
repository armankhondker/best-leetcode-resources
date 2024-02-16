Given a sequence of words, check whether it forms a valid word square.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

Note:
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".

Therefore, it is a valid word square.


INTUTION, only 3 ways for it to be invalid

1. too long
2. too short
3. letters not equal

public boolean validWordSquare(List<String> words) {
        if (words.size() == 0 || words == null) return true;
        int n = words.size();
        for (int i = 0; i < n; i++) {
            String tmp = words.get(i);
            for (int j = 0; j < tmp.length(); j++) {
                // too long
                if (j >= n)
                    return false;
                // too short
                if (words.get(j).length() <= i)
                    return false;
                // letter not equal
                if (tmp.charAt(j) != words.get(j).charAt(i))
                    return false;
            }
        }
        return true;
    }