//给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。 
//
// “最近的”定义为两个整数差的绝对值最小。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = "123"
//输出: "121"
// 
//
// 示例 2: 
//
// 
//输入: n = "1"
//输出: "0"
//解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n.length <= 18 
// n 只由数字组成 
// n 不含前导 0 
// n 代表在 [1, 10¹⁸ - 1] 范围内的整数 
// 
// Related Topics 数学 字符串 👍 199 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length;

    string nearestPalindromic(string &n) {  // 模拟, 分类讨论
        length = (int) n.size();
        if (length == 1) {
            n[0]--;
            return n;
        }
        string word = n;
        for (int left = 0, right = length - 1; left < right; left++, right--) {
            word[right] = word[left];
        }
        long long num0 = stoll(n), num = stoll(word);
        string num1, num2;
        if (num < num0) {
            num1 = word;
            num2 = up(word);
        } else if (num > num0) {
            num1 = down(word);
            num2 = word;
        } else {
            num1 = down(word);
            num2 = up(word);
        }
        long long numDown = stoll(num1), numUp = stoll(num2);
        if (numUp - num0 < num0 - numDown) {
            return num2;
        }
        return num1;
    }

    string down(string &word) {
        string ans;
        if (word[0] == '1') {   // 检查101型
            bool all0 = true;
            for (int i = 1, limit = (length - 1) >> 1; i <= limit; i++) {
                if (word[i] != '0') {
                    all0 = false;
                    break;
                }
            }
            if (all0) {
                for (int i = 1; i < length; i++) {
                    ans += '9';
                }
                return ans;
            }
        }
        ans = word;
        for (int left = (length - 1) >> 1; left >= 0; left--) {
            if (ans[left] != '0') {
                ans[left]--;
                if (length - 1 - left != left) {
                    ans[length - 1 - left]--;
                }
                break;
            }
        }
        return ans;
    }

    string up(string &word) {
        string ans;
        bool all9 = true;
        for (int i = 0, limit = (length - 1) >> 1; i <= limit; i++) {  // 检查99型
            if (word[i] != '9') {
                all9 = false;
                break;
            }
        }
        if (all9) {
            ans += '1';
            for (int i = 1; i < length; i++) {
                ans += '0';
            }
            ans += '1';
            return ans;
        }
        ans = word;
        for (int left = (length - 1) >> 1; left >= 0; left--) {
            if (ans[left] != '9') {
                ans[left]++;
                if (length - 1 - left != left) {
                    ans[length - 1 - left]++;
                }
                break;
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
