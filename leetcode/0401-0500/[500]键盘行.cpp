//给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。 
//
// 美式键盘 中： 
//
// 
// 第一行由字符 "qwertyuiop" 组成。 
// 第二行由字符 "asdfghjkl" 组成。 
// 第三行由字符 "zxcvbnm" 组成。 
// 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["Hello","Alaska","Dad","Peace"]
//输出：["Alaska","Dad"]
// 
//
// 示例 2： 
//
// 
//输入：words = ["omk"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：words = ["adsdf","sfd"]
//输出：["adsdf","sfd"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 20 
// 1 <= words[i].length <= 100 
// words[i] 由英文字母（小写和大写字母）组成 
// 
// Related Topics 数组 哈希表 字符串 👍 177 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        vector<int> letter2Idx('z' - 'A' + 1);
        vector<string> lines = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        vector<string> ans;
        for (int i = 0; i < 3; i++) {
            string line = lines[i];
            for (char letter : line) {
                letter2Idx[letter - 'A'] = i;
                letter2Idx[letter - 'a'] = i;
            }
        }
        for (string word: words) {
            int line = letter2Idx[word[0] - 'A'];
            for (int i = 0; i < word.length(); i++) {
                if (line != letter2Idx[word[i] - 'A']) {
                    break;
                }
                if (i == word.length() - 1) {
                    ans.push_back(word);
                }
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
