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
// Related Topics 字符串 动态规划 👍 4644 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string longestPalindrome(string &s) {
        int length = (int) s.size() * 2 + 1, idx = 0;
        char container[length];
        container[idx++] = 'a';
        while (idx < length) {
            container[idx++] = s[idx >> 1];
            container[idx++] = 'a';
        }
        int maxRadius = 0, maxCenter, radius[length];
        for (int i = 0, right = -1, center; i < length; i++) {	// 记录最右边界和相应中心
            if (i < right && right - i + 1 > radius[2 * center - i]) {	// 可以直接确定半径的情况
                radius[i] = radius[2 * center - i];
                continue;
            }
            radius[i] = i >= right ? 1 : (right - i + 1);
            while (i - radius[i] >= 0 && i + radius[i] < length
                   && container[i - radius[i]] == container[i + radius[i]]) {	// 中心扩散
                radius[i]++;
            }
            if (i + radius[i] - 1 > right) {
                center = i;
                right = i + radius[i] - 1;
            }
        }
        for (int i = 0; i < length; i++) {
            if (radius[i] - 1 > maxRadius) {
                maxRadius = radius[i] - 1;
                maxCenter = i;
            }
        }
        return s.substr((maxCenter - maxRadius) >> 1, maxRadius);
    }
};

//leetcode submit region end(Prohibit modification and deletion)
