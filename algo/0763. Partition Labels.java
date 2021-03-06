/*
A string S of lowercase English letters is given. 
We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]

Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
*/

class Solution 
{
    public List<Integer> partitionLabels(String S) 
    {
        List<Integer> outputList = new ArrayList<Integer>();
        if(S == null || S.isEmpty()) {
            return outputList;
        }
        
        int[] map = new int[26];    // stores last visited index of all lower-case-char
        for(int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        
        int start = 0, last = 0;
        for(int i = 0; i < S.length(); i++) 
        {            
            last = Math.max(last, map[S.charAt(i) - 'a']);
            if(last == i)           // main logic
            {
                outputList.add(last - start + 1);
                start = last + 1;
            }
        }
        return outputList;
    }
}