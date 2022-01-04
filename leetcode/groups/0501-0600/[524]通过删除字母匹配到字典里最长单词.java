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

import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {  // 排序+双指针(可不排序, 见cpp)
        dictionary.sort((o1, o2) -> {   //
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o2.compareTo(o1);
        });
        for (int i = dictionary.size() - 1; i >= 0; i--) {
            String word = dictionary.get(i);
            int slow = 0;   // s使用快指针, word使用慢指针
            for (int fast = 0; fast < s.length(); fast++) {
                if (s.charAt(fast) == word.charAt(slow)) {
                    slow++;
                }
                if (slow == word.length()) {
                    return word;
                }
            }
        }
        return "";
    }
}

//leetcode submit region end(Prohibit modification and deletion)
