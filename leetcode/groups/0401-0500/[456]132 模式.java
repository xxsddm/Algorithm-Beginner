//给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足
//：i < j < k 和 nums[i] < nums[k] < nums[j] 。 
//
// 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4]
//输出：false
//解释：序列中不存在 132 模式的子序列。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,1,4,2]
//输出：true
//解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,3,2,0]
//输出：true
//解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 2 * 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
// Related Topics 栈 数组 二分查找 有序集合 单调栈 👍 570 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean find132pattern(int[] nums) { // 单调栈(未找到左侧比该元素大的数字入栈)(严格递减)
        if (nums.length < 3) {
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int secondMax = Integer.MIN_VALUE;  // 维护当前第二大数字(不是栈内第二大,是左侧有更大值的第二大)
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (num < secondMax) {
                return true;
            }
            // 出现比栈顶数字大的数(存在更大数),可以移除栈顶元素(一定大于现有secondMax)并更新secondMax
            while (!stack.isEmpty() && num > stack.peekLast()) {
                secondMax = stack.pollLast();
            }
            // 重复数字不必加入
            if (stack.isEmpty() || num < stack.peekLast()) {
                stack.add(num);
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
