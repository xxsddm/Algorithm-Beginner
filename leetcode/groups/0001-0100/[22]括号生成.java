//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 2114 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    StringBuilder sb = new StringBuilder();
    LinkedList<String> ans = new LinkedList<>();
    int num;

    public List<String> generateParenthesis(int n) {
        num = n;
        backtrack(0, 0);
        return ans;
    }

    private void backtrack(int numLeft, int numRight) {
        if (numRight == num) {
            ans.add(sb.toString());
            return;
        }
        // 加入左括号
        if (numLeft < num) {
            sb.append('(');
            backtrack(numLeft + 1, numRight);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 加入右括号
        if (numLeft > numRight) {
            sb.append(')');
            backtrack(numLeft, numRight + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
