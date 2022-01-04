//给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。 
//
// 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。 
//
// 注意: 
//假设字符串的长度不会超过 1010。 
//
// 示例 1: 
//
// 
//输入:
//"abccccdd"
//
//输出:
//7
//
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
// Related Topics 贪心 哈希表 字符串 👍 332 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int longestPalindrome(string s) {
        int length = s.size();
        int ans = 0;
        vector<int> counter(52, 0);
        for (int i = 0; i < length; i++) {
            if (s[i] < 'a') {
                counter[s[i] - 'A']++;
            }
            else {
                counter[s[i] - 'a' + 26]++;
            }
        }
        for (int num: counter) {
            ans += num - ((num & 1) == 0 ? 0 : 1);
        }
        if (ans < length) {
            ans++;
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
