//给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。 
//
// 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses",
//"rat","ratcatdogcat"]
//输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
//解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成; 
//     "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成; 
//     "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
// 
//
// 示例 2： 
//
// 
//输入：words = ["cat","dog","catdog"]
//输出：["catdog"] 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 10⁴ 
// 0 <= words[i].length <= 1000 
// words[i] 仅由小写字母组成 
// 0 <= sum(words[i].length) <= 10⁵ 
// 
// Related Topics 深度优先搜索 字典树 数组 字符串 动态规划 👍 187 👎 0

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Node {
        boolean val = false;
        Node[] next = new Node[26];
    }
    boolean[] visited;
    Node root = new Node();
    List<String> ans = new LinkedList<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words.length <= 1) {
            return ans;
        }
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int minLen = words[0].length() << 1;
        for (String word : words) {
            if (word.length() >= minLen) {
                // 当前word[0~i]可构造, 不必重复
                visited = new boolean[word.length()];
                if (dfs(0, word)) {
                    continue;
                }
            }
            add(root, 0, word);
        }
        return ans;
    }

    Node add(Node node, int idx, String word) {
        if (node == null) {
            node = new Node();
        }
        if (idx == word.length()) {
            node.val = true;
            return node;
        }
        int temp = word.charAt(idx) - 'a';
        node.next[temp] = add(node.next[temp], idx + 1, word);
        return node;
    }

    boolean dfs(int start, String word) {
        int length = word.length(), idx = start;
        Node node = root;
        while (idx < length) {
            int temp = word.charAt(idx++) - 'a';
            node = node.next[temp];
            if (node == null) {
                return false;
            }
            if (node.val && !visited[idx - 1]) {
                visited[idx - 1] = true;
                if (dfs(idx, word)) {
                    if (start == 0) {
                        ans.add(word);
                    }
                    return true;
                }
            }
        }
        return node.val;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
