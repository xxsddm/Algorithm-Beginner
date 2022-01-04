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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int num;
    vector<string> ans;

    vector<string> generateParenthesis(int n) {
        string sb;
        num = n;
        backtrack(0, 0, sb);
        return ans;
    }

    void backtrack(int numLeft, int numRight, string& sb) {
        if (numRight == num) {
            ans.push_back(sb);
            return;
        }
        // 加入左括号
        if (numLeft < num) {
            sb += '(';
            backtrack(numLeft + 1, numRight, sb);
            sb.erase(sb.length() - 1);
        }
        // 加入右括号
        if (numLeft > numRight) {
            sb += ')';
            backtrack(numLeft, numRight + 1, sb);
            sb.erase(sb.length() - 1);
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
