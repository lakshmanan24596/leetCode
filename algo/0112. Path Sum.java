/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution 
{
    public boolean hasPathSum(TreeNode root, int sum) 
    {
        // refer: https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
        
        if(root == null)
            return false;
         
        sum = sum - root.val;
        if(root.left == null && root.right == null && sum == 0)
            return true;
        
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);   
    }
}