//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3875 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length(), maxlength = 1;
        String maxstr = s.substring(0, 1);
        for (int left = 0; left < length; left++){
            if (left - (maxlength >> 1) >= 0 && left + (maxlength >> 1) + 1 < length &&
                    s.charAt(left - (maxlength >> 1)) == s.charAt(left + (maxlength >> 1) + 1) &&
                    left + 1 < length && s.charAt(left) == s.charAt(left + 1)){
                int right = left + 2, backleft = left - 1;
                while (backleft >= 0 && right < length && s.charAt(backleft) == s.charAt(right)){
                    backleft--;
                    right++;
                }
                if (maxlength < right - backleft - 1){
                    maxlength = right - backleft - 1;
                    maxstr = s.substring(backleft + 1, right);
                }
            }

            if (left - (maxlength + 1 >> 1) + 1 >= 0 && left + (maxlength + 1 >> 1) + 1 < length &&
                    s.charAt(left - (maxlength + 1 >> 1) + 1) == s.charAt(left + (maxlength + 1 >> 1) + 1) &&
                    left + 2 < length && s.charAt(left) == s.charAt(left + 2)){
                int right = left + 3, backleft = left - 1;
                while (backleft >= 0 && right < length && s.charAt(backleft) == s.charAt(right)){
                    backleft--;
                    right++;
                }
                if (maxlength < right - backleft - 1){
                    maxlength = right - backleft - 1;
                    maxstr = s.substring(backleft + 1, right);
                }
            }

            if (length - left - 1 < maxlength >> 1) break;
        }
        return maxstr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
