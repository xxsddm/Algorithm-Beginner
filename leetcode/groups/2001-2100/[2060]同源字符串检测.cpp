//原字符串由小写字母组成，可以按下述步骤编码： 
//
// 
// 任意将其 分割 为由若干 非空 子字符串组成的一个 序列 。 
// 任意选择序列中的一些元素（也可能不选择），然后将这些元素替换为元素各自的长度（作为一个数字型的字符串）。 
// 重新 顺次连接 序列，得到编码后的字符串。 
// 
//
// 例如，编码 "abcdefghijklmnop" 的一种方法可以描述为： 
//
// 
// 将原字符串分割得到一个序列：["ab", "cdefghijklmn", "o", "p"] 。 
// 选出其中第二个和第三个元素并分别替换为它们自身的长度。序列变为 ["ab", "12", "1", "p"] 。 
// 重新顺次连接序列中的元素，得到编码后的字符串："ab121p" 。 
// 
//
// 给你两个编码后的字符串 s1 和 s2 ，由小写英文字母和数字 1-9 组成。如果存在能够同时编码得到 s1 和 s2 原字符串，返回 true ；否则，
//返回 false。 
//
// 注意：生成的测试用例满足 s1 和 s2 中连续数字数不超过 3 。 
//
// 
//
// 示例 1： 
//
// 输入：s1 = "internationalization", s2 = "i18n"
//输出：true
//解释："internationalization" 可以作为原字符串
//- "internationalization" 
//  -> 分割：      ["internationalization"]
//  -> 不替换任何元素
//  -> 连接：      "internationalization"，得到 s1
//- "internationalization"
//  -> 分割：      ["i", "nternationalizatio", "n"]
//  -> 替换：      ["i", "18",                 "n"]
//  -> 连接：      "i18n"，得到 s2
// 
//
// 示例 2： 
//
// 输入：s1 = "l123e", s2 = "44"
//输出：true
//解释："leetcode" 可以作为原字符串
//- "leetcode" 
//  -> 分割：       ["l", "e", "et", "cod", "e"]
//  -> 替换：       ["l", "1", "2",  "3",   "e"]
//  -> 连接：       "l123e"，得到 s1
//- "leetcode" 
//  -> 分割：       ["leet", "code"]
//  -> 替换：       ["4",    "4"]
//  -> 连接：       "44"，得到 s2
// 
//
// 示例 3： 
//
// 输入：s1 = "a5b", s2 = "c5b"
//输出：false
//解释：不存在这样的原字符串
//- 编码为 s1 的字符串必须以字母 'a' 开头
//- 编码为 s2 的字符串必须以字母 'c' 开头
// 
//
// 示例 4： 
//
// 输入：s1 = "112s", s2 = "g841"
//输出：true
//解释："gaaaaaaaaaaaas" 可以作为原字符串
//- "gaaaaaaaaaaaas"
//  -> 分割：       ["g", "aaaaaaaaaaaa", "s"]
//  -> 替换：       ["1", "12",           "s"]
//  -> 连接：       "112s"，得到 s1
//- "gaaaaaaaaaaaas"
//  -> 分割：       ["g", "aaaaaaaa", "aaaa", "s"]
//  -> 替换：       ["g", "8",        "4",    "1"]
//  -> 连接         "g841"，得到 s2
// 
//
// 示例 5： 
//
// 输入：s1 = "ab", s2 = "a2"
//输出：false
//解释：不存在这样的原字符串
//- 编码为 s1 的字符串由两个字母组成
//- 编码为 s2 的字符串由三个字母组成
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 40 
// s1 和 s2 仅由数字 1-9 和小写英文字母组成 
// s1 和 s2 中连续数字数不超过 3 
// 
// Related Topics 字符串 动态规划 👍 14 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string s1, s2;
    int length1, length2;
    const int benchmark = -1000;
    // 最多三个连续数字,且优先消除数字,则累积数字的差介于[-999,999],减去-1000即可
    bool visited[41][41][2000];

    bool possiblyEquals(string &s1, string &s2) { // 回溯+记忆化
        this->s1 = s1;
        this->s2 = s2;
        length1 = (int) s1.length();
        length2 = (int) s2.length();
        memset(visited, false, sizeof(visited));
        return backtrack(0, 0, 0, 0);
    }

    // 不确定性在于数字,这里优先抵消数字,出现两个数字则同时消去较小值再进入下一轮
    // idx1:s1待匹配索引, idx2:s2待匹配索引, cum1:s1累计数字, cum2:s2累计数字
    bool backtrack(int idx1, int idx2, int cum1, int cum2) {
        if (visited[idx1][idx2][cum1 - cum2 - benchmark]) {
            return false;
        }
        visited[idx1][idx2][cum1 - cum2 - benchmark] = true;
        // 两个字符串均扫完则判断累计数字是否为0
        if (idx1 == length1 && idx2 == length2) {
            return cum1 == 0 && cum2 == 0;
        }
        // 若存在一个扫完的字符串且其累计数字为0,则无法与另一字符串匹配
        if (idx1 == length1 && cum1 == 0 || idx2 == length2 && cum2 == 0) {
            return false;
        }
        if (cum1 > 0) { // 若s1累计数字为正
            if (s2[idx2] >= 'a') {  // 若s2匹配到字符
                return backtrack(idx1, idx2 + 1, cum1 - 1, cum2);
            } else {    // 若s2匹配到数字
                int nextCum = 0;
                while (idx2 < length2 && s2[idx2] < 'a') {
                    nextCum = nextCum * 10 + s2[idx2] - '0';
                    int minCum = min(nextCum, cum1);
                    if (backtrack(idx1, ++idx2, cum1 - minCum, nextCum - minCum)) {
                        return true;
                    }
                }
                return false;
            }
        } else if (cum2 > 0) {  // 同上
            if (s1[idx1] >= 'a') {
                return backtrack(idx1 + 1, idx2, cum1, cum2 - 1);
            } else {
                int nextCum = 0;
                while (idx1 < length1 && s1[idx1] < 'a') {
                    nextCum = nextCum * 10 + s1[idx1] - '0';
                    int minCum = min(nextCum, cum2);
                    if (backtrack(++idx1, idx2, nextCum - minCum, cum2 - minCum)) {
                        return true;
                    }
                }
                return false;
            }
        }
        if (idx1 == length1 || idx2 == length2) {
            return false;
        }
        if (s1[idx1] < 'a') {
            int nextCum = 0;
            while (idx1 < length1 && s1[idx1] < 'a') {
                nextCum = nextCum * 10 + s1[idx1] - '0';
                int minCum = min(nextCum, cum2);
                if (backtrack(++idx1, idx2, nextCum - minCum, cum2 - minCum)) {
                    return true;
                }
            }
        } else if (s2[idx2] < 'a') {
            int nextCum = 0;
            while (idx2 < length2 && s2[idx2] < 'a') {
                nextCum = nextCum * 10 + s2[idx2] - '0';
                int minCum = min(nextCum, cum1);
                if (backtrack(idx1, ++idx2, cum1 - minCum, nextCum - minCum)) {
                    return true;
                }
            }
        } else if (s1[idx1] == s2[idx2]) {
            return backtrack(idx1 + 1, idx2 + 1, cum1, cum2);
        }
        return false;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
