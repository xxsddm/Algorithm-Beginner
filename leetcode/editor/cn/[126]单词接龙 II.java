//按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> 
//s1 -> s2 -> ... -> sk 这样的单词序列，并满足： 
//
// 
// 
// 
// 每对相邻的单词之间仅有单个字母不同。 
// 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单
//词。 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的
// 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log","cog"]
//输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//解释：存在 2 种最短的转换序列：
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
//"hit" -> "hot" -> "lot" -> "log" -> "cog"
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log"]
//输出：[]
//解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 7 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有单词 互不相同 
// 
// 
// 
// Related Topics 广度优先搜索 哈希表 字符串 回溯 👍 481 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {    // BFS(硬刚)
        LinkedList<List<String>> ans = new LinkedList<>();
        LinkedList<String> sublist = new LinkedList<>();
        sublist.add(beginWord);
        int length = beginWord.length();
        if (beginWord.equals(endWord)) {
            ans.add(sublist);
            return ans;
        }
        LinkedList<LinkedList<String>> queueWord = new LinkedList<>();
        HashSet<String> container = new HashSet<>(wordList);
        if (!container.contains(endWord)) {
            return ans;
        }
        container.remove(beginWord);
        queueWord.add(sublist);
        while (!queueWord.isEmpty()) {
            int num = queueWord.size();
            LinkedList<String> used = new LinkedList<>();   // 记录本轮新加入列表的单词, 本轮结束时删除(避免路径重复)
            for (int i = 0; i < num; i++) {
                LinkedList<String> words = queueWord.pollFirst();
                String word = words.peekLast();
                StringBuilder sb = new StringBuilder(word);
                boolean skip = false;
                // 枚举所有可能修改的索引和所有可能修改的字符(很慢)
                for (int idx = 0; idx < length; idx++) {
                    for (char c = 'a'; c <= 'z'; c++) {
//                        if (c == word.charAt(idx)) {
//                            continue;
//                        }
                        sb.setCharAt(idx, c);
                        String temp = sb.toString();
                        if (container.contains(temp)) {
                            used.add(temp);
                            words.addLast(temp);
                            if (temp.equals(endWord)) {
                                ans.add(words);
                                skip = true;
                                break;
                            }
                            queueWord.addLast(new LinkedList<>(words));
                            words.pollLast();   // 撤销操作
                        }
                    }
                    if (skip) {
                        break;
                    }
                    sb.setCharAt(idx, word.charAt(idx));    // 撤销修改
                }
            }
            while (!used.isEmpty()) {
                container.remove(used.poll());
            }
            if (!ans.isEmpty()) {   // 本轮相应路径长度已结束, 之后加入的路径长度不是最短
                return ans;
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
