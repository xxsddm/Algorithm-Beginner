//返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。 
//
// 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同 
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
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 118 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String smallestSubsequence(String s) {   // 单调栈
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
