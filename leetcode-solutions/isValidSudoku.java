Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


1. must check that each row has no duplicates
2. must check that each column has no duplicates
3. must verify that 3x3 subsquare has no dupliacates

USE A HASHSET, and use a STRING for the keys, that will have unique ones for reach row, col, subbox


TC:O(1) because board is 9x9 always, if nxn board then will be n^2
SC:O(1) because board is 9x9 always,

public boolean isValidSudoku(char[][] board) {
    HashSet<String> seen = new HashSet();
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;
}ß