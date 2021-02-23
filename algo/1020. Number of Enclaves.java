/*
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Example 1:
Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

Example 2:
Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
*/


/*
    Approach 1:
        Do DFS from all inner cells with value = 1
        solution: below code
        
    Approach 2:
        Do DFS from all boundary cells with value = 1
        solution: https://leetcode.com/problems/number-of-enclaves/discuss/266168/Easy-Java-DFS-6ms-solution
        
    Time: m * n
    Space: 1
*/

class Solution {
    int[][] grid;
    int[] dir = new int[] {-1, 0, 1, 0, -1};
    int m, n;
    
    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int currOutput, output = 0;
        
        for (int i = 1; i < m - 1; i++) {           // iterate only inner cells (without boundary)
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    currOutput = dfs(i, j);
                    if (currOutput != -1) {
                        output += currOutput;
                    }
                }
            }
        }
        return output;
    } 
    
    public int dfs(int x, int y) {
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            return -1;
        }
        grid[x][y] = 0;
        int nextX, nextY;
        int currOutput, output = 1;
        
        for (int i = 0; i < 4; i++) {
            nextX = x + dir[i];
            nextY = y + dir[i + 1];
            if (grid[nextX][nextY] == 1) {
                currOutput = dfs(nextX, nextY);
                if (currOutput != -1 && output != -1) {
                    output += currOutput;
                } else {
                    output = -1;
                }
            }
        }
        return output;
    }
}