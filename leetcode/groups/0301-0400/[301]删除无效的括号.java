//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()())()"
//输出：["(())()","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
// 
//
// 示例 3： 
//
// 
//输入：s = ")("
//输出：[""]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 25 
// s 由小写英文字母以及括号 '(' 和 ')' 组成 
// s 中至多含 20 个括号 
// 
// Related Topics 广度优先搜索 字符串 回溯 👍 499 👎 0

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int leftLimit = 0, rightLimit;
    int[] counter;
    String str;
    StringBuilder sb = new StringBuilder();
    List<String> ans = new LinkedList<>();
    // 没有想到不用hash的去重方法
    HashSet<String> container = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {    // 回溯
        // 跳过左右端必须删除的括号
        str = s;
        int remainLeft = 0, remainRight = 0;
        rightLimit = s.length() - 1;

        // 提前限制左右端索引,缩小目标范围
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ')') {
                leftLimit = i;
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '(') {
                rightLimit = i;
                break;
            }
        }

        // 可能加入的区域中,无法匹配的左右括号数量
        for (int i = leftLimit; i <= rightLimit; i++) {
            if (s.charAt(i) == '(') {
                remainLeft++;
            }
            else if (s.charAt(i) == ')') {
                if (remainLeft > 0) {
                    remainLeft--;
                }
                else {
                    remainRight++;
                }
            }
        }
        if (remainLeft == 0 && remainRight == 0) {
            ans.add(s.substring(leftLimit, rightLimit + 1));
            return ans;
        }

        int count = 0;
        // [i,rightLimit]右括号总数
        counter = new int[rightLimit + 1];
        for (int i = rightLimit; i >= leftLimit; i--) {
            if (s.charAt(i) == ')') {
                count++;
            }
            counter[i] = count;
        }

        backtrack(leftLimit, 0, 0, remainLeft, remainRight);
        ans.addAll(container);

        return ans;
    }

    // 当前索引,包含左括号数量,包含右括号数量,左括号剩余,右括号剩余
    void backtrack(int idx, int numLeft, int numRight, int remainLeft, int remainRight) {
        if (idx == rightLimit + 1) {
            container.add(sb.toString());
            return;
        }
        if (str.charAt(idx) == '(') {
            if (remainLeft > 0) {   // 可以考虑不加入该左括号
                backtrack(idx + 1, numLeft, numRight, remainLeft - 1, remainRight);
            }
            if (numLeft < numRight + counter[idx]) {    // 只有 右括号剩余数量充足时 可以考虑加入左括号
                sb.append('(');
                backtrack(idx + 1, numLeft + 1, numRight, remainLeft, remainRight);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        else if (str.charAt(idx) == ')') {
            if (remainRight > 0) {  // 可以考虑不加入该右括号
                backtrack(idx + 1, numLeft, numRight, remainLeft, remainRight - 1);
            }
            if (numLeft > numRight) {   // 只有 左括号数量大于右括号数量时 可以考虑加入右括号
                sb.append(')');
                backtrack(idx + 1, numLeft, numRight + 1, remainLeft, remainRight);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        else {
            sb.append(str.charAt(idx));
            backtrack(idx + 1, numLeft, numRight, remainLeft, remainRight);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
