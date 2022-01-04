//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 2626 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {     // DP
        // 从左往右当前最高点覆盖较低点
        // 从右往左当前最高点覆盖较低点
        // 较小的一方为最大高度
        int[] dp = new int[height.length];

        // 从左往右
        int peak = 0, count = 0;
        for (int i = 0; i < height.length; i++) {
            peak = Math.max(peak, height[i]);
            dp[i] = peak;
        }

        // 从右往左
        peak = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            peak = Math.max(peak, height[i]);
            if (peak >= dp[i]) {    // 已经跨过最高点, 不会再更新dp, 可提前结束
                break;
            }
            dp[i] = peak;
        }

        for (int i = 0; i < height.length; i++) {
            count += dp[i] - height[i];
        }

        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int trap(int[] height) {     // 双指针
        int left = 0, right = height.length - 1, count = 0;
        int leftMax = height[left], rightMax = height[right];
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 遇到最大高度时一定会选择另一个指针的高度差
            if (height[left] < height[right]) {
                count += leftMax - height[left++];
            }
            else {
                count += rightMax - height[right--];
            }
        }
        return count;
    }
}


class Solution {
    public int trap(int[] height) {     // 单调栈
        // 每个接水池由凹槽(可多个)构成, 逐行从下到上, 填充整个凹槽, eg:
        // 第3层      |////////////|
        // 第2层          |////////|
        // 第1层          |////|
        int count = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        // 凹槽某右侧索引
        for (int right = 0; right < height.length; right++) {
            // 若相等则不断while循环直到左侧出现更高边, 否则积累水量为0
            // 出现比栈顶大的数字则可能出现凹槽
            while (!stack.isEmpty() && height[stack.peekLast()] < height[right]) {
                int bottom = stack.pollLast();  // 凹槽某一层底边索引(不一定填充底端)
                if (stack.isEmpty()) {          // 删掉一个就空栈, 可能为最左边
                    break;
                }
                int left = stack.peekLast();    // 凹槽某左侧索引
                if (height[left] == height[bottom]) {       // left高度 >= bottom高度
                    continue;
                }
                int width = right - left - 1;
                count += (Math.min(height[left], height[right]) - height[bottom]) * width;
            }
            stack.add(right);
        }
        return count;
    }
}
