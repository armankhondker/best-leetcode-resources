// Given a 2D board and a word, find if the word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cell, where
// "adjacent" cells are those horizontally or vertically neighboring. The same letter 
// cell may not be used more than once.


//Inutiton, search the board until we reach first letter in string then perform a DFS

DFS WITH BACKTRACKING

//TC: O(MxN * 4^L) to iterate through all the cells in the grid, M number of columns, N is number of rows 
//* 4^L where l is length of word comes from recursive calls in worst case s
//SC: O(MxN) for recursion stack, in worst case will go through the entire grid, if word is entire grid 

class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[0].length; j++)
            {
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, 0, word))  
                    //if first letter is here and if dfs finds rest of characters 
                {
                    return true ;
                }
            }
        }
        return false; //cant find word 
    }
    public boolean dfs(char[][] board,int i, int j, int count, String word) //count is var for character that we are currently checking 
    {
        if(count==word.length()){ //we have found the entire word 
            return true; 
        }
        
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] != word.charAt(count))
            //check bounds and if word that we are on is equal to current char we are on 
        {
            return false; 
        }
        
        char temp = board[i][j]; // needed to remember this char
        board[i][j] = ' '; //we need to not use same cell more than once 

        boolean found =dfs(board, i+1, j, count+1, word) //check all possilbites 
            || dfs(board, i-1, j, count+1, word)
                || dfs(board, i, j+1, count+1, word)
                || dfs(board, i, j-1, count+1, word);
        board[i][j] = temp; //need to restore that 
        return found;  //this will return if we have found the final answer 
    }
}