//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 538 👎 0

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {  // 快一些(利用单词长度相同的性质)
        LinkedList<Integer> container = new LinkedList<>();
        int totalLength = words[0].length() * words.length; // 单词长度相同
        if (totalLength > s.length()) { // 排除不可能匹配的情况
            return container;
        }
        int[] nums = new int[26], wordNums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }
        for (String word: words) {  // 排除不可能匹配的情况(这段删了反而更快)
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                wordNums[idx]++;
                if (wordNums[idx] > nums[idx]) {
                    return container;
                }
            }
        }
        HashMap<String, Integer> counter = new HashMap<>(); // 记录每种单词的数量
        for (String word: words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        int length = words[0].length();
        // match记录每个s索引开始, 可以匹配的单词(无法匹配则为null)
        String[] match = new String[s.length()];
        for (int i = 0; i <= s.length() - length; i++) {
            String temp = s.substring(i, i + length);
            for (String word: words) {
                if (temp.equals(word)) {
                    match[i] = word;
                    break;
                }
            }
        }
        // 枚举所有可能的起点
        for (int i = 0; i <= s.length() - totalLength; i++) {
            boolean skip = true;
            // 利用单词相等长度的性质, 每隔length索引检查一次match的值是否为null
            for (int j = i; j <= i + totalLength - length; j += length) {
                if (match[j] == null) {
                    break;
                }
                if (j == i + totalLength - length) {
                    skip = false;
                    break;
                }
            }
            if (skip) {
                continue;
            }
            // match通过初步判定, 可以进一步判断是否可以完整拼接
            HashMap<String, Integer> tempCounter = (HashMap<String, Integer>) counter.clone();
            for (int j = i; j <= i + totalLength - length; j += length) {
                String word = match[j]; // match[j]表示当前索引j下匹配的单词
                tempCounter.put(word, tempCounter.get(word) - 1);
                if (tempCounter.get(word) < 0) {    // 若s匹配的单词已超出单词数量, 则不可能匹配
                    break;
                }
                if (j == i + totalLength - length) {    // 完成匹配, 记录起点i
                    container.add(i);
                }
            }
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int totalLength = 0;
    int count = 0;
    String txt;
    String[] words;
    HashMap<String, Integer> counter = new HashMap<>(); // 换成used的boolean数组会超时(有大量重复单词)
    LinkedList<Integer> container;

    public List<Integer> findSubstring(String s, String[] words) {  // 回溯(很慢)
        container = new LinkedList<>();
        totalLength = words[0].length() * words.length; // 单词长度相同
        if (totalLength > s.length()) { // 排除不可能匹配的情况
            return container;
        }
        int[] count = new int[26], wordCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (String word: words) {  // 排除不可能匹配的情况
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                wordCount[idx]++;
                if (wordCount[idx] > count[idx]) {
                    return container;
                }
            }
        }

        txt = s;
        this.words = words;
        for (String word: words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        for (int idx = 0; idx <= s.length() - totalLength; idx++) {
            backtrack(idx, idx);
        }
        return container;
    }

    private void backtrack(int idx, int start) {
        if (count == words.length) {
            container.add(idx - totalLength);
            return;
        }
        for (String word: counter.keySet()) {
            int num = counter.get(word);
            if (num > 0 && check(idx, word)) {
                count++;
                counter.put(word, num - 1);
                backtrack(idx + word.length(), start);
                count--;
                counter.put(word, num);
                if (!container.isEmpty() && container.peekLast() == start) {
                    return;
                }
            }
        }
    }

    private boolean check(int idx, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (txt.charAt(idx++) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
