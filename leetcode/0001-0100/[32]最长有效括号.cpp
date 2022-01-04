//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1456 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int longestValidParentheses(string s) {
        int maxLength = 0;
        // 未匹配的左右括号索引(栈)
        stack<int> stack;
        for (int i = 0; i < s.length(); i++) {
            if (stack.empty()) {
                maxLength = i;  // 栈为空则一定为i
                stack.push(i);
                continue;
            }
            if (s[i] == '(') {
                // 栈末尾(不含)~i(不含)
                maxLength = max(maxLength, i - 1 - stack.top());
                stack.push(i);
            }
            else {
                if (s[stack.top()] == '(') {
                    stack.pop();
                    int right = stack.empty() ? -1 : stack.top();
                    // 栈末尾(不含)~i(含)
                    maxLength = max(maxLength, i - right);
                }
                else {
                    // 栈末尾(不含)~i(不含)
                    maxLength = max(maxLength, i - 1 - stack.top());
                    stack.push(i);
                }
            }
        }

        if (!stack.empty()) { // 考虑栈末端(不含)~s末端子序列(含)
            maxLength = max(maxLength, (int) s.length() - 1 - stack.top());
        }

        return maxLength;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
