//有效数字（按顺序）可以分成以下几个部分： 
//
// 
// 一个 小数 或者 整数 
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数 
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分有效数字列举如下： 
//
// 
// ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
// "53.5e93", "-123.456e789"] 
// 
//
// 部分无效数字列举如下： 
//
// 
// ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"] 
// 
//
// 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = ".1"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。 
// 
// Related Topics 字符串 👍 273 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isNumber(String s) {
        // 提前考虑末尾情况, 末尾必须是数字或小数点(不含指数时)
        if (!Character.isDigit(s.charAt(s.length() - 1)) && s.charAt(s.length() - 1) != '.') {
            return false;
        }
        // 是否有小数(整数)部分符号, 是否有指数部分符号, 是否有小数点, 是否有数字, 是否取指数
        // 出现 '.', 数字, 'e', 'E' 均代表已含 小数点, 小数(整数)部分符号
        boolean firstSign = false, secondSign = false, point = false, number = false, pow = false;

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            // 出现 小数点
            if (temp == '.') {
                if (point) {
                    return false;
                }
                firstSign = true;
                point = true;
            }
            // 出现 正负号
            else if (temp == '+' || temp =='-') {
                if (firstSign && !pow || secondSign) {
                    return false;
                }
                if (pow) {
                    secondSign = true;
                }
                firstSign = true;
            }
            // 出现 数字
            else if (Character.isDigit(temp)) {
                number = true;
                firstSign = true;
                if (pow) {
                    secondSign = true;
                }
            }
            // 出现 指数
            else if (temp == 'e' || temp == 'E') {
                if (pow || !number) {
                    return false;
                }
                point = true;
                pow = true;
            }
            // 其他情况, 直接false
            else {
                return false;
            }
        }

        return number;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
