/*
You are given an array of n strings strs, all of the same length.
We may choose any deletion indices, and we delete all the characters in those indices for each string.
For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"].
Suppose we chose a set of deletion indices answer such that after deletions, the final array has its elements in lexicographic order (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]). 
Return the minimum possible value of answer.length.

Example 1:
Input: strs = ["ca","bb","ac"]
Output: 1
Explanation: 
After deleting the first column, strs = ["a", "b", "c"].
Now strs is in lexicographic order (ie. strs[0] <= strs[1] <= strs[2]).
We require at least 1 deletion since initially strs was not in lexicographic order, so the answer is 1.

Example 2:
Input: strs = ["xc","yb","za"]
Output: 0
Explanation: 
strs is already in lexicographic order, so we do not need to delete anything.
Note that the rows of strs are not necessarily in lexicographic order:
i.e., it is NOT necessarily true that (strs[0][0] <= strs[0][1] <= ...)

Example 3:
Input: strs = ["zyx","wvu","tsr"]
Output: 3
Explanation: We have to delete every column.

Constraints:
n == strs.length
1 <= n <= 100
1 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/


/*
    For ["xga","xfb","yfa"], 
    if the second column was deleted, the final array would be ["xa","xb","ya"], with three sorted strings.
    correct output = 1, but it returns 0
    this solution breaks when there is same characters
*/
/*
// WRONG SOLUTION

class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int output = 0;
        int i;
        
        for (int j = 0; j < m; j++) {
            for (i = 0; i < n - 1; i++) {
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    output++;
                    break;
                }
            }
            if (i == n - 1) {
                return output;
            }
        }
        return output;
    }
}
*/


/*
    https://leetcode.com/problems/delete-columns-to-make-sorted-ii/discuss/203182/JavaC%2B%2BPython-Greedy-Solution-O(MN)
    https://leetcode.com/problems/delete-columns-to-make-sorted-ii/discuss/668307/C%2B%2B-Easy-to-Understand-Greedy-Approach
    
    Time: n*m, which is strs.length * strs[0].length()
    Space: n
*/
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int output = 0;
        int i;
        boolean[] sorted = new boolean[n - 1];
        int sortedCount = 0;    // count of true values in sorted array
        
        for (int j = 0; j < m; j++) {
            for (i = 0; i < n - 1; i++) {
                if (!sorted[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    output++;
                    break;
                }
            }
            if (i == n - 1) {
                for (i = 0; i < n - 1; i++) {
                    if (!sorted[i] && strs[i + 1].charAt(j) > strs[i].charAt(j)) {  // if strictly greater then it is sorted
                        sorted[i] = true;
                        sortedCount++;
                    }
                }
            }
            if (sortedCount == n - 1) {
                return output;
            }
        }
        return output;
    }
}