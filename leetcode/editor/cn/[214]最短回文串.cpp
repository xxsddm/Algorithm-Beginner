//给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aacecaaa"
//输出："aaacecaaa"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd"
//输出："dcbabcd"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 字符串匹配 哈希函数 滚动哈希 👍 384 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string shortestPalindrome(string s) {   // KMP
        int length = (int) s.length();
        int idxPreffix = 0, idxSuffix = length - 1;
        // i -> [0,i]最长相同前后真子序列的长度
        vector<int> next(length);
        for (int idxSu = 1, idxPre = 0; idxSu < length; idxSu++) {
            char temp = s[idxSu];
            while (idxPre > 0 && s[idxPre] != temp) {
                idxPre = next[idxPre - 1];
            }
            if (s[idxPre] == temp) {
                idxPre++;
            }
            next[idxSu] = idxPre;
        }
        // idxPreffix:待匹配前缀索引, idxSuffix:后缀索引
        while (idxPreffix < idxSuffix) {
            char temp = s[idxSuffix];
            while (idxPreffix > 0 && s[idxPreffix] != temp) {
                // 不相同则尝试以原idxPreffix-1为后缀末尾的前缀长度(作为待匹配索引)
                idxPreffix = next[idxPreffix - 1];
            }
            if (s[idxPreffix] == temp) {
                idxPreffix++;
            }
            idxSuffix--;
        }
        int maxLength = (idxPreffix == idxSuffix) ? (2 * idxSuffix + 1) : (2 * idxSuffix + 2);
        if (maxLength == length) {
            return s;
        }
        string ans;
        for (int i = length - 1; i >= maxLength; i--) {
            ans += s[i];
        }
        ans.append(s);
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
