//返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。 
//
// 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 118 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string smallestSubsequence(string s) {  // 单调栈
        int length = (int) s.length(), used = 0, idx = 0;
        vector<int> counter(26);
        string sb;
        for (int i = 0; i < length; i++) {
            counter[s[i] - 'a']++;
        }
        for (int i = 0; i < length; i++) {
            char temp = s[i];
            int loc = temp - 'a';
            // 跳过已经出现的字母
            if ((used & (1 << loc)) != 0) {
                counter[loc]--;
                continue;
            }
            while (idx > 0 &&
                   sb[idx - 1] > temp &&
                   counter[sb[idx - 1] - 'a'] > 0) {
                used ^= 1 << (sb[idx - 1] - 'a');
                idx--;
            }
            if (idx == sb.length()) {
                sb += temp;
                idx++;
            }
            else {
                sb[idx++] = temp;
            }
            used ^= (1 << loc);
            counter[loc]--;
        }
        return sb;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
