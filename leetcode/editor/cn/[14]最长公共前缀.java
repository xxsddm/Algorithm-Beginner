//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 1780 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {  // 多指针
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < strs[0].length()) {    // 以第一个为参照选择子序列元素
            char temp = strs[0].charAt(idx);
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                if (idx >= str.length() || str.charAt(idx) != temp) {
                    return sb.toString();
                }
            }
            sb.append(temp);    // 所有单词都检验通过, 则加入
            idx++;
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
