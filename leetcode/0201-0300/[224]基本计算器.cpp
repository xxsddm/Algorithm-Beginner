//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 递归 数学 字符串 👍 643 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length, ans = 0, idx = 0;
    string target;

    int calculate(string s) {   // DFS
        length = (int) s.length();
        target = s;
        while (idx < length) {
            ans += dfs();
            idx++;
        }
        return ans;
    }

    // 返回 当前位置idx到相应右括号(或末尾)区域内的值,同时更新idx
    int dfs() {
        int num = 0, sign = 1;     // sign记录正负号
        while (idx < length && target[idx] != ')') {
            char temp = target[idx];
            // 遇到符号
            if (temp == '+') {
                sign = 1;
            }
            else if (temp == '-') {
                sign = -1;
            }
                // 遇到数字
            else if (temp >= '0' && temp <= '9'){
                int prevIdx = idx;
                while (idx < length && target[idx] >= '0' && target[idx] <= '9') {
                    idx++;
                }
                num += sign * stoi(target.substr(prevIdx, idx - prevIdx));
                continue;
            }
                // 递归计算与start不匹配的括号部分
            else if (temp == '(') {
                idx++;  // 先跳过当前idx
                num += sign * dfs();
            }
            idx++;
        }

        return num;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
