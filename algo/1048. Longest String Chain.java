/*
Given a list of words, each word consists of English lowercase letters.
Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
Return the longest possible length of a word chain with words chosen from the given list of words.

Example 1:
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chain is "a","ba","bda","bdca".

Example 2:
Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5

Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
*/


/*
    Logic: 
        Sort the words based on length, because length i can be conencted only with length i - 1
        Instead of adding a character, try deleting a character to form a chain in reverse.
        Use DP tabulation technique to store prev calculated results
        
    Time: n*logn + nss
    Space: n
*/
class Solution 
{
    public int longestStrChain(String[] words) 
    {
        Arrays.sort(words, new Comparator<String>(){                                        // n*logn
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        
        int output = 0, currOutput;
        Map<String, Integer> dpMap = new HashMap<String, Integer>();    // for each word, store the output
        
        for(String word : words)                                                            // n
        {
            currOutput = 1;
            for(int i = 0; i < word.length(); i++)                                          // s
            {
                String prev = word.substring(0, i) + word.substring(i + 1);                 // s
                if(dpMap.containsKey(prev)) 
                {
                    currOutput = Math.max(currOutput, dpMap.get(prev) + 1);
                }
            }
            dpMap.put(word, currOutput);
            output = Math.max(output, currOutput);
        }
        return output;
    }
}
