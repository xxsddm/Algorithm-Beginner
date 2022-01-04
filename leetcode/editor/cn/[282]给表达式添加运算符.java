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
// Related Topics 数学 字符串 回溯 👍 263 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length;
    long target;
    String num;
    List<String> ans = new LinkedList<>();

    public List<String> addOperators(String num, int target) {  // 回溯暴力破解
        length = num.length();
        this.target = target;
        this.num = num;
        backtrack(new StringBuilder(), 0, 0, 0);
        return ans;
    }

    private void backtrack(StringBuilder sb, int start, long cumulate, long prev) {
        if (start == length) {
            if (cumulate == target) {
                ans.add(sb.toString());
            }
            return;
        }
        long val = 0;
        int signIdx = sb.length();  // 若加入运算符则运算符索引
        if (start == 0) {   // 初始位置无需左端符号(可并入下方循环,见cpp)
            for (int end = start; end < length; end++) {
                // 加入数字大小
                val = val * 10 + num.charAt(end) - '0';
                sb.append(num.charAt(end));
                backtrack(sb, end + 1, val, val);
                if (val == 0) {     // 防止前导0
                    break;
                }
            }
            // 初始位置无需撤销操作
            // sb.setLength(signIdx);
            return;
        }
        sb.append(' ');     // 符号位
        // 当前加入数字num[start,end],枚举该数字左端运算符
        for (int end = start; end < length; end++) {
            // 加入数字大小
            val = val * 10 + num.charAt(end) - '0';
            sb.append(num.charAt(end));
            sb.setCharAt(signIdx, '+');
            backtrack(sb, end + 1, cumulate + val, val);
            sb.setCharAt(signIdx, '-');
            backtrack(sb, end + 1, cumulate - val, - val);
            sb.setCharAt(signIdx, '*');
            backtrack(sb, end + 1, cumulate - prev + prev * val, prev * val);
            if (val == 0) {     // 防止前导0
                break;
            }
        }
        sb.setLength(signIdx);  // 撤销操作
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int length, idx = 0;
    long target;
    String num;
    String[] signal = new String[] {"+", "-", "*"};
    LinkedList<String> container = new LinkedList<>();
    List<String> ans = new LinkedList<>();

    public List<String> addOperators(String num, int target) {  // 回溯暴力破解
        length = num.length();
        this.target = target;
        this.num = num;
        build(0);
        return ans;
    }

    private void build(int start) {
        if (start == length) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < container.size() - 1; i++) {
                list.add(container.get(i));
                list.add("");
            }
            list.add(container.get(container.size() - 1));
            backtrack(1, list);
            return;
        }
        for (int end = start; end < length; end++) {
            if (num.charAt(start) == '0') {
                container.add("0");
                build(start + 1);
                container.pollLast();
                return;
            }
            container.addLast(num.substring(start, end + 1));
            build(end + 1);
            container.pollLast();
        }
    }

    private void backtrack(int next, List<String> list) {
        if (next == list.size()) {
            if (check(new ArrayList<>(list))) {
                StringBuilder sb = new StringBuilder();
                for (String subStr: list) {
                    sb.append(subStr);
                }
                ans.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            list.set(next, signal[i]);
            backtrack(next + 2, list);
        }
    }

    private boolean check(List<String> list) {
        idx = 0;
        int subLength = list.size();
        long sign = 1, cumsum = 0;
        while (idx < subLength) {   // idx指向当前待计算数字
            // 加减法(先计算idx再修改为idx+1对应符号)
            if (idx + 1 >= subLength || !list.get(idx + 1).equals("*")) {
                cumsum += sign * Long.parseLong(list.get(idx));
                if (idx + 1 < subLength) {
                    if (list.get(idx + 1).equals("+")) {
                        sign = 1;
                    }
                    else {
                        sign = -1;
                    }
                }
                idx += 2;
                continue;
            }
            // 乘法
            long mult = Long.parseLong(list.get(idx)) * Long.parseLong(list.get(idx + 2));
            idx += 4;
            while (idx < subLength && list.get(idx - 1).equals("*")) {
                mult *= Long.parseLong(list.get(idx));
                idx += 2;
            }
            cumsum += sign * mult;
            if (idx < subLength) {
                if (list.get(idx - 1).equals("+")) {
                    sign = 1;
                }
                else {
                    sign = -1;
                }
            }
        }
        return cumsum == target;
    }
}
