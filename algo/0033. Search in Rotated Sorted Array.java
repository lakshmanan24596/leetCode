// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

// You are given a target value to search. If found in the array return its index, otherwise return -1.

// You may assume no duplicate exists in the array.

// Your algorithm's runtime complexity must be in the order of O(log n).

// Example 1:
// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4

// Example 2:
// Input: nums = [4,5,6,7,0,1,2], target = 3
// Output: -1


class Solution 
{
    public int search(int[] nums, int target) 
    {
        return binarySearch(nums, target, 0, nums.length-1);   
    }
    
    public int binarySearch(int[] nums, int target, int l, int r)
    {
        if(l > r)
            return -1;
        
        int mid = (l+r)/2;
        if(nums[mid] == target)
            return mid;
        
        if(nums[l] <= nums[mid])    // left side is sorted
        {
            if(target >= nums[l] && target < nums[mid])
                return binarySearch(nums, target, l, mid-1);
            return binarySearch(nums, target, mid+1, r);
        }
        else    // right side is sorted
        {
            if(target > nums[mid] && target <= nums[r])
                return binarySearch(nums, target, mid+1, r);
            return binarySearch(nums, target, l, mid-1);
        }
    }
}