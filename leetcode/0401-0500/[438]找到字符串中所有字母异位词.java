//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 727 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        int[] counterp = new int[26], counter = new int[26];
        int lens = s.length(), lenp = p.length(), count = 0;
        for (int i = 0; i < lenp; i++) {
            counterp[p.charAt(i) - 'a']++;
            counter[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counter[i] == counterp[i]) {
                count++;
            }
        }
        if (count == 26) {
            ans.add(0);
        }
        for (int i = 1; i <= lens - lenp; i++) {
            int idx1 = s.charAt(i - 1) - 'a', idx2 = s.charAt(i + lenp - 1) - 'a';
            if (counter[idx1]-- == counterp[idx1]) {
                count--;
            } else if (counter[idx1] == counterp[idx1]) {
                count++;
            }
            if (counter[idx2]++ == counterp[idx2]) {
                count--;
            } else if (counter[idx2] == counterp[idx2]) {
                count++;
            }
            if (count == 26) {
                ans.add(i);
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
