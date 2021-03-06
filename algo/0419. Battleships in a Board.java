/*
Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

Example:
X..X
...X
...X
In the above board there are 2 battleships.

Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
*/

/*
class Solution 
{
    public int countBattleships(char[][] board) // Time: O(n) and Space: O(n), where n = row*col
    {
        int numberOfBattleships = 0;
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        
        for(int i = 0; i < row; i++) 
        {
            for(int j = 0; j < col; j++) 
            {
                if(!visited[i][j] && board[i][j] == 'X') 
                {
                    numberOfBattleships++;
                    for(int k = j + 1; k < col && board[i][k] == 'X'; k++) {    // right
                        visited[i][k] = true;
                    }
                    for(int k = i + 1; k < row && board[k][j] == 'X'; k++) {    // down
                        visited[k][j] = true;
                    }
                }
            }
        }
        return numberOfBattleships;
    }
}
*/

class Solution 
{
    public int countBattleships(char[][] board) // Time: O(n) and Space: O(1), where n = row*col
    {
        int numberOfBattleships = 0;
        int row = board.length;
        int col = board[0].length;
        
        for(int i = 0; i < row; i++) 
        {
            for(int j = 0; j < col; j++) 
            {
                if(board[i][j] == 'X' && 
                   (j-1 < 0 || board[i][j-1] != 'X') &&  // left
                   (i-1 < 0 || board[i-1][j] != 'X'))   // up
                {
                    numberOfBattleships++;  // add count for the first X alone
                }
            }
        }
        return numberOfBattleships;
    }
}
