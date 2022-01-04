//给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。 
//
// 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "banana"
//输出："ana"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd"
//输出：""
// 
//
// 
//
// 提示： 
//
// 
// 2 <= s.length <= 3 * 10⁴ 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 二分查找 后缀数组 滑动窗口 哈希函数 滚动哈希 👍 173 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int start = 0, maxLen = 0;

    string longestDupSubstring(string s) {  // 随便写的
        int left = 0, right = (int) s.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, s)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return s.substr(start, maxLen);
    }

    bool check(int length, string &s) {
        if (length == 0) {
            return true;
        }
        int cumsum = 0;
        unordered_map<int, vector<int>> container;
        for (int i = 0; i < length; i++) {
            cumsum += s[i] - 'a';
        }
        container[cumsum].push_back(0);
        for (int i = length, limit = (int) s.size(); i < limit; i++) {
            cumsum -= s[i - length] - 'a';
            cumsum += s[i] - 'a';
            if (container.count(cumsum) && check(i - length + 1, length, container[cumsum], s)) {
                return true;
            }
            container[cumsum].push_back(i - length + 1);
        }
        return false;
    }

    bool check(int idx, int length, vector<int> &begins, string &s) {
        for (int &begin : begins) {
            for (int i = 0, idx1 = idx, idx2 = begin; i < length; i++) {
                if (s[idx1++] != s[idx2++]) {
                    break;
                }
                if (i == length - 1) {
                    start = idx, maxLen = length;
                    return true;
                }
            }
        }
        return false;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {    // Rabin-Karp玄学(变化不大)
public:
    int start = 0, maxLen = 0, mod = 100007;

    string longestDupSubstring(string s) {
        int left = 0, right = (int) s.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, s)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return s.substr(start, maxLen);
    }

    bool check(int length, string &s) {
        if (length == 0) {
            return true;
        }
        int cumsum = 0, temp = 1, move = 97;
        vector<int> container[mod];
        for (int i = 0; i < length; i++) {
            cumsum = (cumsum * move + s[i]) % mod;
        }
        for (int i = 0; i < length - 1; i++) {
            temp = (temp * move) % mod;
        }
        container[cumsum].push_back(0);
        for (int i = length, limit = (int) s.size(); i < limit; i++) {
            cumsum = (cumsum + mod - (s[i - length]) * temp % mod) % mod;
            cumsum = (cumsum * move + s[i]) % mod;
            if (!container[cumsum].empty() && check(i - length + 1, length, container[cumsum], s)) {
                return true;
            }
            container[cumsum].push_back(i - length + 1);
        }
        return false;
    }

    bool check(int idx, int length, vector<int> &begins, string &s) {
        for (int &begin : begins) {
            for (int i = 0, idx1 = idx, idx2 = begin; i < length; i++) {
                if (s[idx1++] != s[idx2++]) {
                    break;
                }
                if (i == length - 1) {
                    start = idx, maxLen = length;
                    return true;
                }
            }
        }
        return false;
    }
};
