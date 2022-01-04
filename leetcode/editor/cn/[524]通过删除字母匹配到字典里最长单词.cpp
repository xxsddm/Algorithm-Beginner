//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。 
//
// 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
// 
//
// 示例 2： 
//
// 
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 1000 
// s 和 dictionary[i] 仅由小写英文字母组成 
// 
// Related Topics 数组 双指针 字符串 排序 👍 169 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {  // 可先排序, 再从右向左遍历(见java)
        string ans;
        for (string word: dictionary) {
            int slow = 0;   // s使用快指针, word使用慢指针
            for (char fast : s) {
                if (fast == word[slow]) {
                    slow++;
                }
                if (slow == word.length()) {
                    if (word.length() > ans.length()) {
                        ans = word;
                    }
                    else if (word.length() == ans.length()) {
                        ans = min(ans, word);
                    }
                    break;
                }
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
