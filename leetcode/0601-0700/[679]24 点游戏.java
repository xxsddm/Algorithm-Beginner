//你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。 
//
// 示例 1: 
//
// 输入: [4, 1, 8, 7]
//输出: True
//解释: (8-4) * (7-1) = 24
// 
//
// 示例 2: 
//
// 输入: [1, 2, 1, 2]
//输出: False
// 
//
// 注意: 
//
// 
// 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。 
// 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允
//许的。 
// 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。 
// 
// Related Topics 数组 数学 回溯 👍 320 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 回溯(硬刚)
    private static final double TARGET = 24;
    private static final double EPISLON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        return backtrack(new double[] {cards[0], cards[1], cards[2], cards[3]});
    }

    private boolean backtrack(double[] nums) {
        if (nums.length == 1) {
            return Math.abs(nums[0] - TARGET) < EPISLON;
        }
        // 每次选择2个不同的数参与运算进行回溯
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 将选择出来的两个数的计算结果和原数组剩下的数加入 next 数组
                double[] next = new double[nums.length - 1];
                for (int k = 0, idx = 0; k < nums.length; k++) {
                    if (k != i && k != j) {
                        next[idx++] = nums[k];
                    }
                }
                // 两个数的所有可能运算均考虑作为next最后一个元素
                for (double num : calculate(nums[i], nums[j])) {
                    next[next.length - 1] = num;
                    if (backtrack(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Double> calculate(double a, double b) {
        List<Double> list = new ArrayList<>();
        // 考虑不同运算和不同运算顺序(可作为剪枝)
        list.add(a + b);
        list.add(a - b);
        list.add(b - a);
        list.add(a * b);
        // 防止除数为0
        if ((Math.abs(b) > EPISLON)) {
            list.add(a / b);
        }
        if ((Math.abs(a) > EPISLON)) {
            list.add(b / a);
        }
        return list;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {    // 回溯(硬刚)
    boolean ans = false;
    int used = 0;
    int[] cards;
    double[] nums = new double[4];
    // 所有的运算顺序组合
    int[][] orders = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};

    // 4个数字排列组合, 中间3个运算符号排列组合, 3个符号计算顺序排列组合(4*3*2*1*3*2*1*4*4*4=9216)
    public boolean judgePoint24(int[] cards) {
        this.cards = cards;
        backtrack(0);
        return ans;
    }

    private void backtrack(int idx) {   // 排列组合+运算符号+运算顺序
        if (idx == 4) {
            judge();    // 排列组合完成后对运算符号和计算顺序回溯
            return;
        }

        for (int i = 0; i < 4; i++) {
            int num = 1 << i;
            if ((used & num) != 0) {
                continue;
            }
            used |= num;
            nums[idx] = cards[i];
            backtrack(idx + 1);
            if (ans) {
                return;
            }
            used ^= num;
            nums[idx] = 0;
        }
    }

    private void judge() {  // 运算符号+运算顺序
        for (int[] order: orders) {
            double temp1 = nums[order[0]], temp2 = nums[order[0] + 1];
            for (int i = 0; i < 4; i++) {
                calculate(order[0], order[0] + 1, i);
                double temp3 = nums[order[1]], temp4 = nums[order[1] + 1];
                double order0 = nums[order[0]], order01 = nums[order[0] + 1];
                for (int j = 0; j < 4; j++) {
                    calculate(order[1], order[1] + 1, j);
                    // 第一个运算的符号在索引1, 2数字之间, 扩展合并范围
                    if (order[0] == 1) {
                        nums[1] = nums[order[1]];
                        nums[2] = nums[order[1]];
                    }
                    double temp5 = nums[order[2]], temp6 = nums[order[2] + 1];
                    for (int k = 0; k < 4; k++) {
                        calculate(order[2], order[2] + 1, k);
                        if (Math.abs(nums[order[2]] - 24) < 0.01) {
                            ans = true;
                            return;
                        }
                        // 第3个运算符完成后撤销操作
                        nums[order[2]] = temp5;
                        nums[order[2] + 1] = temp6;
                    }
                    // 第2个运算符完成后撤销操作
                    nums[order[1]] = temp3;
                    nums[order[1] + 1] = temp4;
                    if (order[0] == 1) {
                        nums[1] = order0;
                        nums[2] = order01;
                    }
                }
                // 第1个运算符完成后撤销操作
                nums[order[0]] = temp1;
                nums[order[0] + 1] = temp2;
            }
        }
    }

    // 计算并覆盖被计算的部分, 视为合并数字(剩余数字: 4->3->2->1)
    private void calculate(int idx1, int idx2, int operate) {
        double ans;
        if (operate == 0) {
            ans = nums[idx1] + nums[idx2];
        }
        else if (operate == 1) {
            ans = nums[idx1] - nums[idx2];
        }
        else if (operate == 2) {
            ans = nums[idx1] * nums[idx2];
        }
        else {
            // 注意除数不能为0
            if (Math.abs(nums[idx2]) > 0.01) {
                ans = nums[idx1] / nums[idx2];
            }
            else {
                return;
            }
        }
        nums[idx1] = ans;
        nums[idx2] = ans;
    }
}
