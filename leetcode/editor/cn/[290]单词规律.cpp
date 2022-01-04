//给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。 
//
// 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。 
//
// 示例1: 
//
// 输入: pattern = "abba", str = "dog cat cat dog"
//输出: true 
//
// 示例 2: 
//
// 输入:pattern = "abba", str = "dog cat cat fish"
//输出: false 
//
// 示例 3: 
//
// 输入: pattern = "aaaa", str = "dog cat cat dog"
//输出: false 
//
// 示例 4: 
//
// 输入: pattern = "abba", str = "dog dog dog dog"
//输出: false 
//
// 说明: 
//你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
// Related Topics 哈希表 字符串 👍 378 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool wordPattern(string pattern, string s) {
        int idx = 0, length = s.length();
        // pattern字符 -> s子字符串
        vector<string> patToTxt(26);  // 构建时默认值为""
        // s子字符串 -> pattern字符
        unordered_map<string, int> txtToPat;
        for (char i : pattern) {
            // 若pattern已匹配完而s未匹配完
            if (idx >= length) {
                return false;
            }
            int temp = idx;
            while (idx < length && s[idx] != ' ') {
                idx++;
            }
            string txt = s.substr(temp, idx - temp);    // 不同于java, cpp第二个参数是截取长度
            idx++;
            temp = i - 'a';
            if (patToTxt[temp].empty()) {
                patToTxt[temp] = txt;
            }
            if (txtToPat.find(txt) == txtToPat.end()) {
                txtToPat[txt] = temp;
            }
            if (patToTxt[temp] != txt || txtToPat[txt] != temp) {
                return false;
            }
        }
        // 检查pattern是否匹配完
        return idx == length + 1;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
