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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int leftLimit = 0, rightLimit;
    vector<int> counter;
    string str;
    vector<string> ans;
    // 没有想到不用hash的去重方法
    unordered_set<string> container;

    vector<string> removeInvalidParentheses(string s) { // 回溯
        // 跳过左右端必须删除的括号
        str = s;
        int remainLeft = 0, remainRight = 0;
        rightLimit = (int) s.length() - 1;

        // 提前限制左右端索引,缩小目标范围
        for (int i = 0; i < s.length(); i++) {
            if (s[i] != ')') {
                leftLimit = i;
                break;
            }
        }
        for (int i = (int) s.length() - 1; i >= 0; i--) {
            if (s[i] != '(') {
                rightLimit = i;
                break;
            }
        }

        // 可能加入的区域中,无法匹配的左右括号数量
        for (int i = leftLimit; i <= rightLimit; i++) {
            if (s[i] == '(') {
                remainLeft++;
            }
            else if (s[i] == ')') {
                if (remainLeft > 0) {
                    remainLeft--;
                }
                else {
                    remainRight++;
                }
            }
        }
        if (remainLeft == 0 && remainRight == 0) {
            ans.push_back(s.substr(leftLimit, rightLimit - leftLimit + 1));
            return ans;
        }

        int count = 0;
        // [i,rightLimit]右括号总数
        counter = vector<int>(rightLimit + 1);
        for (int i = rightLimit; i >= leftLimit; i--) {
            if (s[i] == ')') {
                count++;
            }
            counter[i] = count;
        }

        string sb;
        backtrack(leftLimit, 0, 0, remainLeft, remainRight, sb);
        for (string substr: container) {
            ans.push_back(substr);
        }

        return ans;
    }

    // 当前索引,包含左括号数量,包含右括号数量,左括号剩余,右括号剩余
    void backtrack(int idx, int numLeft, int numRight, int remainLeft, int remainRight, const string& sb) {
        if (idx == rightLimit + 1) {
            container.insert(sb);
            return;
        }
        if (str[idx] == '(') {
            if (remainLeft > 0) {   // 可以考虑不加入该左括号
                backtrack(idx + 1, numLeft, numRight, remainLeft - 1, remainRight, sb);
            }
            if (numLeft < numRight + counter[idx]) {    // 只有 右括号剩余数量充足时 可以考虑加入左括号
                backtrack(idx + 1, numLeft + 1, numRight, remainLeft, remainRight, sb + '(');
            }
        }
        else if (str[idx] == ')') {
            if (remainRight > 0) {  // 可以考虑不加入该右括号
                backtrack(idx + 1, numLeft, numRight, remainLeft, remainRight - 1, sb);
            }
            if (numLeft > numRight) {   // 只有 左括号数量大于右括号数量时 可以考虑加入右括号
                backtrack(idx + 1, numLeft, numRight + 1, remainLeft, remainRight, sb + ')');
            }
        }
        else {
            backtrack(idx + 1, numLeft, numRight, remainLeft, remainRight, sb + str[idx]);
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
