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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        vector<vector<string>> ans;
        vector<string> sublist;
        sublist.push_back(beginWord);
        int length = (int) beginWord.length();
        if (beginWord == endWord) {
            ans.push_back(sublist);
            return ans;
        }
        queue<vector<string>> queueWord;
        unordered_set<string> container;
        for (const string& word: wordList) {
            container.insert(word);
        }
        if (container.find(endWord) == container.end()) {
            return ans;
        }
        container.erase(beginWord);
        queueWord.push(sublist);
        while (!queueWord.empty()) {
            int num = (int) queueWord.size();
            queue<string> used;   // 记录本轮新加入列表的单词, 本轮结束时删除(避免路径重复)
            for (int i = 0; i < num; i++) {
                vector<string> words = queueWord.front();
                queueWord.pop();
                string word = words[words.size() - 1];
                string temp = word;
                bool skip = false;
                // 枚举所有可能修改的索引和所有可能修改的字符
                for (int idx = 0; idx < length; idx++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        temp[idx] = c;
                        if (container.find(temp) != container.end()) {
                            used.push(temp);
                            words.push_back(temp);
                            if (temp == endWord) {
                                ans.push_back(words);
                                skip = true;
                                break;
                            }
                            queueWord.push(vector<string>(words));  // 复制后加入
                            words.pop_back();   // 撤销操作
                        }
                    }
                    if (skip) {
                        break;
                    }
                    temp[idx] = word[idx];
                }
            }
            while (!used.empty()) {
                container.erase(used.front());
                used.pop();
            }
            if (!ans.empty()) { // 本轮相应路径长度已结束, 之后加入的路径长度不是最短
                return ans;
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
