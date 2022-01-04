//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1134 👎 0


import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> container = new HashSet<>(wordDict);
        int length = s.length();
        boolean[] bag = new boolean[length + 1];    // 是否可以作为下一个起点(前一个点为止被覆盖)
        bag[0] = true;
        for (int left = 0; left < length; left++) {
            if (!bag[left]) {
                continue;
            }
            for (int right = left; right < length; right++) {
                if (container.contains(s.substring(left, right + 1))) {
                    bag[right + 1] = true;      // s[left: right]均可覆盖, 则下一个起点为right+1
                }
            }
        }
        return bag[length];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
