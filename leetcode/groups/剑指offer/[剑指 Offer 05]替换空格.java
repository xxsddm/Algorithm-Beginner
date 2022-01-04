//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 
// 👍 145 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {
        if (s == null)  return null;
        StringBuilder container = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (" ".equals(Character.toString(s.charAt(i)))){
                container.append("%20");
            } else {
                container.append(s.charAt(i));
            }
        }
        return container.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
