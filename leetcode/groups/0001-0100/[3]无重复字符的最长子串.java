//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 5755 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s){
        HashSet<Character> container = new HashSet<>();
        int right = 0, length = 0, slength = s.length();
        for (int left = 0; left < slength; left++){
            if (length + left >= slength) {    // 不可能出现更长子字符串则结束
                break;
            }
            if (left != 0) {    // 移除左指针左侧字符
                container.remove(s.charAt(left - 1));
            }
            while (right < slength && (!container.contains(s.charAt(right)))){
                container.add(s.charAt(right++));
            }
            length = Math.max(length, right - left);
        }
        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
