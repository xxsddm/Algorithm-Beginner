//给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所
//有能够得到目标值的表达式。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = "123", target = 6
//输出: ["1+2+3", "1*2*3"] 
// 
//
// 示例 2: 
//
// 
//输入: num = "232", target = 8
//输出: ["2*3+2", "2+3*2"] 
//
// 示例 3: 
//
// 
//输入: num = "105", target = 5
//输出: ["1*0+5","10-5"] 
//
// 示例 4: 
//
// 
//输入: num = "00", target = 0
//输出: ["0+0", "0-0", "0*0"]
// 
//
// 示例 5: 
//
// 
//输入: num = "3456237490", target = 9191
//输出: [] 
//
// 
//
// 提示： 
//
// 
// 1 <= num.length <= 10 
// num 仅含数字 
// -2³¹ <= target <= 2³¹ - 1 
// 
// Related Topics 数学 字符串 回溯 👍 277 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length;
    long target;
    string num;
    vector<string> ans;

    vector<string> addOperators(string num, int target) {   // 回溯暴力破解
        length = (int) num.size();
        this->target = target;
        this->num = num;
        string sb;
        backtrack(sb, 0, 0, 0);
        return ans;
    }

    void backtrack(string& sb, int start, long cumulate, long prev) {
        if (start == length) {
            if (cumulate == target) {
                ans.push_back(sb);
            }
            return;
        }
        long val = 0;
        int signIdx = (int) sb.size();
        if (signIdx > 0) {  // 符号位
            sb += ' ';
        }
        // 当前加入数字num[start,end],枚举该数字左端运算符
        for (int end = start; end < length; end++) {
            // 加入数字大小
            val = val * 10 + num[end] - '0';
            sb += num[end];
            if (start == 0) {
                backtrack(sb, end + 1, val, val);
            }
            else {
                sb[signIdx] = '+';
                backtrack(sb, end + 1, cumulate + val, val);
                sb[signIdx] = '-';
                backtrack(sb, end + 1, cumulate - val, - val);
                sb[signIdx] = '*';
                backtrack(sb, end + 1, cumulate - prev + prev * val, prev * val);
            }
            if (val == 0) {     // 防止前导0
                break;
            }
        }
        if (start > 0) {
            sb.resize(signIdx);     // 撤销操作
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
