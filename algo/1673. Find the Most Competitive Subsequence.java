/*
Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. 
For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.

Example 1:
Input: nums = [3,5,2,6], k = 2
Output: [2,6]
Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.

Example 2:
Input: nums = [2,4,3,3,5,4,9,6], k = 4
Output: [2,3,3,4]

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
1 <= k <= nums.length
*/


/*
    1) brute force
        k loops and in each loop we need to find min within a range
        time: n * k
        
    2) segment tree to find min
        time: n + klogn
    
    3) mono increasing stack
        stack size should be exactly 4
        n-i > k-stackSize --> used to maintain min 4 elements in stacks
        time: 2n
    
    https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
    https://leetcode.com/problems/remove-k-digits/
*/

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] competitiveArr = new int[k];
        int n = nums.length;
        
        for (int i = 0; i < n; ) {
            if (!stack.isEmpty() && nums[i] < stack.peek() && n - i > k - stack.size()) {    // main logic
                stack.pop();
            } else {
                if (stack.size() < k) {
                    stack.push(nums[i]);
                }
                i++;
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            competitiveArr[i] = stack.pop();
        }
        return competitiveArr;
    }
}