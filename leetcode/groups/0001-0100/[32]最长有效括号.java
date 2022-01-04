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


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        // 未匹配的左右括号索引(栈)
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                maxLength = i;  // 栈为空则一定为i
                stack.add(i);
                continue;
            }
            if (s.charAt(i) == '(') {
                // 栈末尾(不含)~i(不含)
                maxLength = Math.max(maxLength, i - 1 - stack.peekLast());
                stack.add(i);
            }
            else {
                if (s.charAt(stack.peekLast()) == '(') {
                    stack.pollLast();
                    int right = stack.isEmpty() ? -1 : stack.peekLast();
                    // 栈末尾(不含)~i(含)
                    maxLength = Math.max(maxLength, i - right);
                }
                else {
                    // 栈末尾(不含)~i(不含)
                    maxLength = Math.max(maxLength, i - 1 - stack.peekLast());
                    stack.add(i);
                }
            }
        }

        if (!stack.isEmpty()) { // 考虑栈末端(不含)~s末端子序列(含)
            maxLength = Math.max(maxLength, s.length() - 1 - stack.peekLast());
        }

        return maxLength;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int longestValidParentheses(String s) {
        int maxLength;
        // 未匹配的左括号索引(栈), 未匹配的右括号索引(栈)
        LinkedList<Integer> stackLeft = new LinkedList<>(), stackRight = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stackLeft.add(i);
            }
            else {
                if (!stackLeft.isEmpty()) {
                    stackLeft.pollLast();
                }
                else {
                    stackRight.add(i);
                }
            }
        }

        if (stackLeft.isEmpty() && stackRight.isEmpty()) {
            return s.length();
        }

        // 归并排序(间隔部分为有效字符串)
        int[] idxs = new int[stackLeft.size() + stackRight.size()];
        int idx = 0;
        while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
            if (stackLeft.peekFirst() < stackRight.peekFirst()) {
                idxs[idx++] = stackLeft.pollFirst();
            }
            else {
                idxs[idx++] = stackRight.pollFirst();
            }
        }
        while (!stackLeft.isEmpty()) {
            idxs[idx++] = stackLeft.pollFirst();
        }
        while (!stackRight.isEmpty()) {
            idxs[idx++] = stackRight.pollFirst();
        }

        maxLength = Math.max(idxs[0], s.length() - 1 - idxs[idxs.length - 1]);
        for (int i = 0; i < idxs.length - 1; i++) {
            maxLength = Math.max(maxLength, idxs[i + 1] - idxs[i] - 1);
        }

        return maxLength;
    }
}
