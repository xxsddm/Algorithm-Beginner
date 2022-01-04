//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 605 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeDuplicateLetters(String s) {    // 单调栈
        int length = s.length(), used = 0;
        int[] counter = new int[26];
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> container = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < length; i++) {
            char temp = s.charAt(i);
            int idx = temp - 'a';
            // 跳过已经出现的字母
            if ((used & (1 << idx)) != 0) {
                counter[idx]--;
                continue;
            }
            while (!container.isEmpty() &&
                    container.peekLast() > temp &&
                    counter[container.peekLast() - 'a'] > 0) {
                used ^= 1 << (container.pollLast() - 'a');
            }
            container.add(temp);
            used ^= (1 << idx);
            counter[idx]--;
        }
        for (char letter: container) {
            sb.append(letter);
        }
        return sb.toString();
    }
}


//leetcode submit region end(Prohibit modification and deletion)
