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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 回溯(硬刚)
private:
    const double TARGET = 24;
    const double EPSILON = 1e-3;

    bool backtrack(vector<double>& nums) {
        int length = nums.size();
        if (length == 1) {
            return abs(nums[0] - TARGET) < EPSILON;
        }
        // 选择两个不同元素, 参与各种可能的计算, 将新的数组作为参数进行回溯
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                vector<double> next(length - 1);
                vector<double> temp = calculate(nums[i], nums[j]);
                for (int k = 0, idx = 0; k < length; k++) {
                    if (k != i && k != j) {
                        next[idx++] = nums[k];
                    }
                }
                for (double newNum: temp) {
                    next[length - 2] = newNum;
                    if (backtrack(next)) {
                        return true;
                    }
                }
                temp.clear();
            }
        }
        return false;
    }

    vector<double> calculate(double num1, double num2) {
        vector<double> container;
        container.push_back(num1 + num2);
        container.push_back(num1 - num2);
        container.push_back(num2 - num1);
        container.push_back(num1 * num2);
        // 考虑除数为0的情况
        if (abs(num2) > EPSILON) {
            container.push_back(num1 / num2);
        }
        if (abs(num1) > EPSILON) {
            container.push_back(num2 / num1);
        }
        return container;
    }

public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> nums = vector<double>(4);
        for (int i = 0; i < 4; i++) {
            nums[i] = cards[i];
        }
        return backtrack(nums);
    }
};

//leetcode submit region end(Prohibit modification and deletion)
