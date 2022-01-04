//使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
// 
// 如果字符串的长度为 1 ，算法停止 
// 如果字符串的长度 > 1 ，执行下述步骤：
// 
// 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。 
// 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + 
//x 。 
// 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。 
// 
// 
// 
//
// 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "great", s2 = "rgeat"
//输出：true
//解释：s1 上可能发生的一种情形是：
//"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
//"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
//"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
//"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
//"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
//"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
//算法终止，结果字符串和 s2 相同，都是 "rgeat"
//这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
// 
//
// 示例 2： 
//
// 
//输入：s1 = "abcde", s2 = "caebd"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s1 = "a", s2 = "a"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// s1.length == s2.length 
// 1 <= s1.length <= 30 
// s1 和 s2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 409 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isScramble(string s1, string s2) { // 区间DP
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1 == s2) {
            return true;
        }
        int length = (int) s1.length();
        // s1起点,s2起点,子序列长度 -> 是否组成扰乱字符串
        vector<vector<vector<bool>>> dp(length, vector<vector<bool>>(length, vector<bool>(length + 1)));
        for (int i = 0; i < length; i++) {
            char temp = s1[i];
            for (int j = 0; j < length; j++) {
                dp[i][j][1] = temp == s2[j];
            }
        }
        // 根据状态转移可以看出需要较大部分起始索引匹配情况,start从右往左
        // 而len需要sublen提前遍历,故len需从左往右遍历
        // sublen只是枚举各种切割位置,遍历顺序无所谓
        for (int start1 = length - 1; start1 >= 0; start1--) {
            for (int start2 = length - 1; start2 >= 0; start2--) {
                for (int len = 2; len <= length - max(start1, start2); len++) {
                    for (int sublen = 1; sublen < len; sublen++) {
                        if (dp[start1][start2][sublen] &&
                            dp[start1 + sublen][start2 + sublen][len - sublen]  // 不交换
                            || dp[start1][start2 + sublen][len - sublen] &&
                               dp[start1 + len - sublen][start2][sublen]) {    // 交换

                            dp[start1][start2][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][length];
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    string word1, word2;
    // word1起始索引, word2起始索引, 判断子序列长度 -> -1:无法匹配, 0:未判断,1:可匹配
    vector<vector<vector<int>>> dp;
    bool isScramble(string s1, string s2) { // 记忆化DP(参考官方题解)
        word1 = s1;
        word2 = s2;
        dp = vector(s1.length(), vector(s1.length(), vector<int>(s1.length() + 1)));
        return dfs(0, 0, (int) s1.length());
    }

    bool dfs(int start1, int start2, int length) {
        // 排除可以直接判断的情况
        if (dp[start1][start2][length] != 0) {
            return dp[start1][start2][length] == 1;
        }
        if (word1.substr(start1, length) ==
            word2.substr(start2, length)) {
            dp[start1][start2][length] = 1;
            return true;
        }
        if (!check(start1, start2, length)) {
            dp[start1][start2][length] = -1;
            return false;
        }

        // 枚举截取子序列长度(截取后考虑是否交换 同一字符串的2个子序列 的位置)
        for (int subLength = 1; subLength < length; subLength++) {
            // 不交换
            if (dfs(start1, start2, subLength)
                && dfs(start1 + subLength, start2 + subLength, length - subLength)) {
                dp[start1][start2][length] = 1;
                return true;
            }
            // 交换
            if (dfs(start1, start2 + length - subLength, subLength)
                && dfs(start1 + subLength, start2, length - subLength)) {
                dp[start1][start2][length] = 1;
                return true;
            }
        }

        // 枚举结果:两子序列不可匹配
        dp[start1][start2][length] = -1;
        return false;
    }

    bool check(int start1, int start2, int length) {
        vector<int> counter1(26), counter2(26);
        for (int i = start1; i < start1 + length; i++) {
            counter1[word1[i] - 'a']++;
        }
        for (int j = start2; j < start2 + length; j++) {
            counter2[word2[j] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counter1[i] != counter2[i]) {
                return false;
            }
        }
        return true;
    }
};
