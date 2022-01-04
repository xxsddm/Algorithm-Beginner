//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 657 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numDistinct(string &s, string &t) { // DP
        if (s.size() < t.size()) {
            return 0;
        }
        int len2 = (int) t.size();
        int dp[len2], mod = INT_MAX;
        vector<int> container['z' - 'A' + 1];
        memset(dp, 0, sizeof(dp));
        for (int i = len2 - 1; i >= 0; i--) {
            container[t[i] - 'A'].push_back(i);
        }
        for (char &letter : s) {
            vector<int> &idxs = container[letter - 'A'];
            if (idxs.empty()) {
                continue;
            }
            int size = (int) idxs.size();
            for (int i = 0; i < size - 1; i++) {
                int *ptr = &dp[idxs[i]];
                if (mod - *ptr < dp[idxs[i] - 1]) {
                    *ptr = *ptr - mod + dp[idxs[i] - 1];
                } else {
                    *ptr = *ptr + dp[idxs[i] - 1];
                }
            }
            if (idxs[size - 1] == 0) {
                if (dp[0] == mod) {
                    dp[0] = 1;
                } else {
                    dp[0]++;
                }
            } else {
                int *ptr = &dp[idxs[size - 1]];
                if (mod - *ptr < dp[idxs[size - 1] - 1]) {
                    *ptr = *ptr - mod + dp[idxs[size - 1] - 1];
                } else {
                    *ptr = *ptr + dp[idxs[size - 1] - 1];
                }
            }
        }
        return dp[len2 - 1];
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int numDistinct(string &s, string &t) {   // DP
        if (s.size() < t.size()) {
            return 0;
        }
        int len2 = (int) t.size();
        unsigned int dp[len2], mod = INT_MAX;
        deque<int> container['z' - 'A' + 1];
        memset(dp, 0, sizeof(dp));
        for (int i = 0; i < len2; i++) {
            container[t[i] - 'A'].push_front(i);
        }
        for (char &letter : s) {
            deque<int> &idxs = container[letter - 'A'];
            for (int &idx : idxs) {
                if (idx == 0) {
                    dp[idx]++;
                } else {
                    dp[idx] += dp[idx - 1];
                }
                dp[idx] %= mod;
            }
        }
        return dp[len2 - 1];
    }
};
