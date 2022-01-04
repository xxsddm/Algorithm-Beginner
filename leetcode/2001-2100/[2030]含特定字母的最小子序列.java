//给你一个字符串 s ，一个整数 k ，一个字母 letter 以及另一个整数 repetition 。 
//
// 返回 s 中长度为 k 且 字典序最小 的子序列，该子序列同时应满足字母 letter 出现 至少 repetition 次。生成的测试用例满足 
//letter 在 s 中出现 至少 repetition 次。 
//
// 子序列 是由原字符串删除一些（或不删除）字符且不改变剩余字符顺序得到的剩余字符串。 
//
// 字符串 a 字典序比字符串 b 小的定义为：在 a 和 b 出现不同字符的第一个位置上，字符串 a 的字符在字母表中的顺序早于字符串 b 的字符。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "leet", k = 3, letter = "e", repetition = 1
//输出："eet"
//解释：存在 4 个长度为 3 ，且满足字母 'e' 出现至少 1 次的子序列：
//- "lee"（"leet"）
//- "let"（"leet"）
//- "let"（"leet"）
//- "eet"（"leet"）
//其中字典序最小的子序列是 "eet" 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：s = "leetcode", k = 4, letter = "e", repetition = 2
//输出："ecde"
//解释："ecde" 是长度为 4 且满足字母 "e" 出现至少 2 次的字典序最小的子序列。
// 
//
// 示例 3： 
//
// 
//输入：s = "bb", k = 2, letter = "b", repetition = 2
//输出："bb"
//解释："bb" 是唯一一个长度为 2 且满足字母 "b" 出现至少 2 次的子序列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= repetition <= k <= s.length <= 5 * 10⁴ 
// s 由小写英文字母组成 
// letter 是一个小写英文字母，在 s 中至少出现 repetition 次 
// 
// Related Topics 栈 贪心 字符串 单调栈 👍 9 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {   // 单调栈(空间优化)
        // count:栈内letter数量; rest:当前索引至s末端包含letter数量
        int count = 0, rest = 0, length = s.length();
        LinkedList<Character> container = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == letter) {
                rest++;
            }
        }

        for (int i = 0; i < length; i++) {
            char temp = s.charAt(i);
            boolean tempIsLetter = temp == letter;
            // 符合条件时移除栈顶元素
            while (!container.isEmpty() &&
                    container.peekLast() > temp &&              // 单调栈优先加入较小元素
                    k - container.size() < length - i &&        // 删除后数组剩余元素数量充足
                    (tempIsLetter ||                            // temp=letter 时可删除
                            container.peekLast() != letter ||   // 若temp和栈顶元素均不为letter 时可删除
                            rest > repetition - count)) {       // temp!=letter,栈顶元素=letter 时需考虑剩余letter数量
                if (container.pollLast() == letter) {
                    count--;
                }
            }

            if (container.size() == k) {
                if (tempIsLetter) {
                    rest--;
                }
                continue;
            }
            // 若temp!=letter则需考虑加入后栈内剩余空位是否足够存放相差的letter
            if (!tempIsLetter) {
                if (k - container.size() > repetition - count) {
                    container.add(temp);
                }
                continue;
            }
            // temp=letter,若相差均为letter可提前结束
            if (rest == repetition - count && k - container.size() == repetition - count) {
                for (char word: container) {
                    sb.append(word);
                }
                int lack = k - sb.length();
                for (int j = 0; j < lack; j++) {
                    sb.append(letter);
                }
                return sb.toString();
            }
            // temp=letter且不属于上述情况
            container.add(temp);
            count++;
            rest--;
        }

        for (char word: container) {
            sb.append(word);
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {   // 单调栈
        int count = 0, length = s.length();
        int[] counter = new int[length];    // 索引i -> [i,length-1]中letter数量 (可空间优化)
        LinkedList<Character> container = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == letter) {
                count++;
            }
            counter[i] = count;
        }
        count = 0;

        for (int i = 0; i < length; i++) {
            char temp = s.charAt(i);
            // 符合条件时移除栈顶元素
            while (!container.isEmpty() &&
                    container.peekLast() > temp &&              // 单调栈优先加入较小元素
                    k - container.size() < length - i &&        // 删除后数组剩余元素数量充足
                    (temp == letter ||                          // temp=letter 时可删除
                            container.peekLast() != letter ||   // 若temp和栈顶元素均不为letter 时可删除
                            counter[i] > repetition - count)) { // temp!=letter,栈顶元素=letter 时需考虑剩余letter数量
                if (container.pollLast() == letter) {
                    count--;
                }
            }

            if (container.size() == k) {
                continue;
            }
            // 若temp!=letter则需考虑加入后栈内剩余空位是否足够存放相差的letter
            if (temp != letter) {
                if (k - container.size() > repetition - count) {
                    container.add(temp);
                }
                continue;
            }
            // temp=letter,若相差均为letter可提前结束
            if (counter[i] == repetition - count && k - container.size() == repetition - count) {
                for (char word: container) {
                    sb.append(word);
                }
                int lack = k - sb.length();
                for (int j = 0; j < lack; j++) {
                    sb.append(letter);
                }
                return sb.toString();
            }
            // temp=letter且不属于上述情况
            container.add(temp);
            count++;
        }

        for (char word: container) {
            sb.append(word);
        }
        return sb.toString();
    }
}
