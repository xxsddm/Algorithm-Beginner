//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 506 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length;
    LinkedList<String> ans = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    ArrayList<String>[] container;

    public List<String> wordBreak(String s, List<String> wordDict) {    // 回溯+字典树
        length = s.length();
        int[] counter = new int[26];    // 记录s中所有字母出现次数
        Trie words = new Trie();
        container = (ArrayList<String>[]) new ArrayList[length];

        // 仅加入可能参与匹配的wordDict元素
        for (int i = 0; i < length; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        for (String word: wordDict) {
            if (word.length() > length) {
                continue;
            }
            int tempLength = word.length();
            int[] tempCounter = new int[26];
            for (int i = 0; i < tempLength; i++) {
                int idx = word.charAt(i) - 'a';
                tempCounter[idx]++;
                if (tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (i == tempLength - 1) {
                    words.add(word);
                }
            }
        }

        for (int i = 0; i < length; i++) {
            int idx = i;
            Node node = words.root;
            while (node != null) {
                if (node.val != null) {
                    if (container[i] == null) {
                        container[i] = new ArrayList<>();
                    }
                    container[i].add(node.val);
                }
                if (idx == length) {    // 注意不能因idx=length提前结束
                    break;
                }
                node = node.next[s.charAt(idx++) - 'a'];    // 移动到当前字母(idx)对应分支
            }
        }
        backtrack(0);
        return ans;
    }

    private void backtrack(int idx) {
        if (idx > length) {
            return;
        }
        if (idx == length) {
            sb.deleteCharAt(sb.length() - 1);
            ans.add(sb.toString());
            sb.append(' ');
            return;
        }
        ArrayList<String> subContainer = container[idx];
        if (subContainer == null) {
            return;
        }
        for (String word: subContainer) {
            int size = sb.length();
            sb.append(word);    // 加入可匹配单词
            sb.append(' ');
            backtrack(idx + word.length());
            sb.delete(size, sb.length());   // 撤销操作
        }
    }
}

class Node {
    String val; // val保存路径起点到终点对应字符串
    Node[] next; // 下一个字符的集合
    Node(int numChar) {
        next = new Node[numChar];
    }
}

class Trie { // 字典树(处理字符串, 目前仅支持小写字母, 扩大范围则hashmap更适用)
    final int numChar;
    final Node root;
    final char benchmark;

    public Trie() {
        this(26, 'a');
    }

    public Trie(int num, char letter) {
        numChar = num;
        root = new Node(numChar);
        benchmark = letter;
    }

    public void add(String target) {    // 将目标字符串放入字典树
        add(root, target, 0);
    }

    private Node add(Node node, String target, int idx) { // idx为待检查索引
        if (node == null) {
            node = new Node(numChar);
        }
        if (idx == target.length()) {   // 若已扫过整个target字符串
            node.val = target;
            return node;
        }
        int i = target.charAt(idx) - benchmark;
        node.next[i] = add(node.next[i], target, idx + 1);
        return node;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
