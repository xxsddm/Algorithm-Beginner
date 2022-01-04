//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 👍 364 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkValidString(String s) { // 栈
        LinkedList<Integer> stackLeft = new LinkedList<>(), stackStar = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == '(') {
                stackLeft.add(i);
            }
            else if (temp == '*') {
                stackStar.add(i);
            }
            // ')'优先匹配'('
            else {
                if (!stackLeft.isEmpty()) {
                    stackLeft.pollLast();
                }
                else if (!stackStar.isEmpty()) {
                    stackStar.pollLast();
                }
                else {
                    return false;
                }
            }
        }
        // 最右侧'('和左侧第一个'*'搭配, 与最右侧的'('和最右侧的'*'搭配, 判断结果一致
        while (!stackLeft.isEmpty()) {
            // 若右侧不再存在'*', 则'('无法匹配
            if (stackStar.isEmpty() || stackLeft.peekLast() > stackStar.peekLast()) {
                return false;
            }
            else {
                stackLeft.pollLast();
                stackStar.pollLast();
            }
        }
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {    // 官方题解, 没看懂
    public boolean checkValidString(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == '(') {
                left++;
                right++;
            }
            else if (temp == ')') {
                left--;
                right--;
            }
            else {
                left--;
                right++;
            }
            left = Math.max(left, 0);
            if (left > right) {
                return false;
            }
        }
        return left == 0;
    }
}
