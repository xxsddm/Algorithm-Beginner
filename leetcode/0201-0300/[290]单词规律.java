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


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean wordPattern(String pattern, String s) {
        int idx = 0, length = s.length();
        // pattern字符 -> s子字符串
        String[] patToTxt = new String[26];  // 构建时默认值为null
        // s子字符串 -> pattern字符
        HashMap<String, Integer> txtToPat = new HashMap<>(40);
        for (int i = 0; i < pattern.length(); i++) {
            int temp = idx;
            while (idx < length && s.charAt(idx) != ' ') {
                idx++;
            }
            // 若pattern已匹配完而s未匹配完
            if (i != pattern.length() - 1 && idx >= length - 1) {
                return false;
            }
            String txt = s.substring(temp, idx);
            idx++;
            temp = pattern.charAt(i) - 'a';
            if (patToTxt[temp] == null) {
                patToTxt[temp] = txt;
            }
            if (!txtToPat.containsKey(txt)) {
                txtToPat.put(txt, temp);
            }
            if (!patToTxt[temp].equals(txt) || txtToPat.get(txt) != temp) {
                return false;
            }
        }
        // 检查pattern是否匹配完
        return idx == length + 1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
