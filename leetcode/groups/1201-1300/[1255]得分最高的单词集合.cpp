//你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。 
//
// 请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的 任意 属于 words 单词子集中，分数最高的单词集合的
//得分。 
//
// 单词拼写游戏的规则概述如下： 
//
// 
// 玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。 
// 可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。 
// 单词表 words 中每个单词只能计分（使用）一次。 
// 根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分分别为 score[0], score[1], ..., 
//score[25]。 
// 本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。 
// 
//
// 
//
// 示例 1： 
//
// 输入：words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d",
//"g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
//输出：23
//解释：
//字母得分为  a=1, c=9, d=5, g=3, o=2
//使用给定的字母表 letters，我们可以拼写单词 "dad" (5+1+5)和 "good" (3+2+2+5)，得分为 23 。
//而单词 "dad" 和 "dog" 只能得到 21 分。 
//
// 示例 2： 
//
// 输入：words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], 
//score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
//输出：27
//解释：
//字母得分为  a=4, b=4, c=4, x=5, z=10
//使用给定的字母表 letters，我们可以组成单词 "ax" (4+5)， "bx" (4+5) 和 "cx" (4+5) ，总得分为 27 。
//单词 "xxxz" 的得分仅为 25 。 
//
// 示例 3： 
//
// 输入：words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,
//1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
//输出：0
//解释：
//字母 "e" 在字母表 letters 中只出现了一次，所以无法组成单词表 words 中的单词。 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 14 
// 1 <= words[i].length <= 15 
// 1 <= letters.length <= 100 
// letters[i].length == 1 
// score.length == 26 
// 0 <= score[i] <= 10 
// words[i] 和 letters[i] 只包含小写的英文字母。 
// 
// Related Topics 位运算 数组 字符串 动态规划 回溯 状态压缩 👍 35 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maxScore = 0;
    vector<int> counter = vector<int>(26);
    vector<int> score;
    vector<vector<int>> counters;

    int maxScoreWords(vector<string>& words, vector<char>& letters, vector<int>& score) {   // 回溯(暴力破解)
        this->score = score;
        for (char letter: letters) {    // 计算letters中字母数量
            counter[letter - 'a']++;
        }

        for (string word: words) {  // 仅考虑可由letters单独组成的单词
            if (word.length() > letters.size()) {
                continue;
            }
            vector<int> tempCounter(26);
            for (int i = 0; i < word.length(); i++) {
                int idx = word[i] - 'a';
                tempCounter[idx]++;
                if (tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (i == word.length() - 1) {
                    counters.push_back(tempCounter);    // 记录所有可以单独组成的单词的各字母数量
                }
            }
        }
        vector<int> curCounter(26);
        backtrack(0, 0, curCounter);
        return maxScore;
    }

    void backtrack(int start, int tempScore, vector<int>& curCounter) {
        if (start == counters.size()) {
            maxScore = max(maxScore, tempScore);
            return;
        }
        int numSkip = 0;
        // 仅考虑从start索引开始的单词, 避免重复
        for (int i = start; i < counters.size(); i++) {
            bool skip = false;
            int temp = tempScore;
            vector<int> tempCounter(curCounter);
            for (int j = 0; j < 26; j++) {
                int num = counters[i][j];
                tempCounter[j] += num;
                if (tempCounter[j] > counter[j]) {
                    numSkip++;
                    skip = true;
                    break;
                }
                temp += num * score[j];
            }
            if (skip) {
                continue;
            }
            backtrack(i + 1, temp, tempCounter);
        }
        if (numSkip == counters.size() - start) {   // 考虑无法加入剩余单词的情况
            maxScore = max(maxScore, tempScore);
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
