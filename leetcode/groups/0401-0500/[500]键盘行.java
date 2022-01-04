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
// Related Topics 数组 哈希表 字符串 👍 154 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findWords(String[] words) {
        int[] letter2Idx = new int['z' - 'A' + 1];
        String[] lines = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        LinkedList<String> container = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                letter2Idx[line.charAt(j) - 'A'] = i;
                letter2Idx[line.charAt(j) - 'a'] = i;
            }
        }
        for (String word: words) {
            int line = letter2Idx[word.charAt(0) - 'A'];
            for (int i = 0; i < word.length(); i++) {
                if (line != letter2Idx[word.charAt(i) - 'A']) {
                    break;
                }
                if (i == word.length() - 1) {
                    container.add(word);
                }
            }
        }
        String[] ans = new String[container.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = container.pollFirst();
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
