//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列： 
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
//"log"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 👍 845 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {  // BFS(硬刚)
        int count = 1, length = beginWord.length();
        if (beginWord.equals(endWord)) {
            return count;
        }
        LinkedList<String> queueWord = new LinkedList<>();
        HashSet<String> container = new HashSet<>(wordList);
        if (!container.contains(endWord)) {
            return 0;
        }
        container.remove(beginWord);
        queueWord.add(beginWord);
        while (!queueWord.isEmpty()) {
            int num = queueWord.size();
            count++;
            for (int i = 0; i < num; i++) {
                String word = queueWord.pollFirst();
                StringBuilder sb = new StringBuilder(word);
                // 枚举所有可能修改的索引和所有可能修改的字符(很慢)
                for (int idx = 0; idx < length; idx++) {
                    for (char c = 'a'; c <= 'z'; c++) {
//                        if (c == word.charAt(idx)) {
//                            continue;
//                        }
                        sb.setCharAt(idx, c);
                        String temp = sb.toString();
                        if (container.contains(temp)) {
                            if (temp.equals(endWord)) {
                                return count;
                            }
                            container.remove(temp); // 移除, 防止路径重复
                            queueWord.addLast(temp);
                        }
                    }
                    sb.setCharAt(idx, word.charAt(idx));    // 撤销修改
                }
            }
        }
        return 0;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
