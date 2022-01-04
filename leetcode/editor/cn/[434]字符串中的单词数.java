//统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。 
//
// 请注意，你可以假定字符串里不包括任何不可打印的字符。 
//
// 示例: 
//
// 输入: "Hello, my name is John"
//输出: 5
//解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
// 
// Related Topics 字符串 👍 105 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSegments(String s) {
        int ans = 0, idx = 0, length = s.length();
        while (idx < length) {
            if (s.charAt(idx) != ' ') {
                ans++;
                while (idx < length && s.charAt(idx) != ' ') {
                    idx++;
                }
            }
            idx++;
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
