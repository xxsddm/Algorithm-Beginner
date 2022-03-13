//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//
// 返回符合要求的 最少分割次数 。
//
//
//
//
//
// 示例 1：
//
//
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
//
//
// 示例 2：
//
//
//输入：s = "a"
//输出：0
//
//
// 示例 3：
//
//
//输入：s = "ab"
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 2000
// s 仅由小写英文字母组成
//
//
//
// Related Topics 字符串 动态规划 👍 477 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int minCut(string &s) { // DP + BFS
        int length = (int) s.size(), ans = -1;
        vector<int> next[length];
        bool visited[length], dp[length][length];
        memset(visited, false, sizeof(visited));
        memset(dp, false, sizeof(dp));
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
            next[i].push_back(i + 1);
        }
        for (int i = 1; i < length; i++) {
            if (s[i - 1] == s[i]) {
                dp[i - 1][i] = true;
                next[i - 1].push_back(i + 1);
            }
        }
        for (int left = length - 3; left >= 0; left--) {
            for (int right = left + 2; right < length; right++) {
                if (s[left] == s[right] && dp[left + 1][right - 1]) {
                    dp[left][right] = true;
                    next[left].push_back(right + 1);
                }
            }
        }
        queue<int> q;
        q.push(0);
        visited[0] = true;
        while (true) {
            ans++;
            for (int i = 0, size = (int) q.size(); i < size; i++) {
                int node = q.front();
                q.pop();
                for (int &nextNode : next[node]) {
                    if (nextNode == length) {
                        return ans;
                    }
                    if (visited[nextNode]) {
                        continue;
                    }
                    visited[nextNode] = true;
                    q.push(nextNode);
                }
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int minCut(string s) {  // DP两次
        int length = (int) s.length();
        // start -> 所有可以形成回文串的end,使[start,end]为回文串
        // start,end -> [start,end]是否形成回文串
        vector<vector<bool>> dp(length, vector<bool>(length));

        // DP1
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++) {
            if (s[i] == s[i + 1]) {
                dp[i][i + 1] = true;
            }
        }
        for (int start = length - 3; start >= 0; start--) {
            for (int end = start + 2; end < length; end++) {
                if (s[start] == s[end]) {
                    dp[start][end] = dp[start + 1][end - 1];
                }
            }
        }

        // DP2
        // idx -> [0,idx]最少分割次数
        vector<int> counts(length, INT32_MAX);
        for (int idx = 0; idx < length; idx++) {
            if (dp[0][idx]) {   // 这一段本身就是回文串则不需要分割
                counts[idx] = 0;
                continue;
            }
            // 分割为[0,loc-1],[loc,idx], 则当前分割次数为这两部分分割次数总和+1
            // 实际只需考虑分割后,后面部分[loc,idx]为回文的情况(因为以idx为末端的区间最终需要分割为回文串)
            for (int loc = 1; loc <= idx; loc++) {
                if (dp[loc][idx]) {
                    counts[idx] = min(counts[idx],  counts[loc - 1] + 1);
                }
            }
        }
        return counts[length - 1];
    }
};
