//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
// Related Topics 栈 数组 单调栈 👍 1509 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {        // 单调栈
        LinkedList<Integer> stack = new LinkedList<>();

//        第i个数字左, 右侧最近的相对heights[i]更低的矩形索引
//        不低的中间部分可以作为heights[i]宽度一部分
        int[] dpLeft = new int[heights.length];
        int[] dpRight = new int[heights.length];
        int ans = 0;

        // 利用单调栈查找i右端, 小于height[i]的最左侧索引
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                dpRight[stack.pollLast()] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            dpRight[stack.pollLast()] = heights.length;     // 右端无更小高度, 即取到边界外
        }

        // 利用单调栈查找i左端, 小于height[i]的最右侧索引
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                dpLeft[stack.pollLast()] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            dpLeft[stack.pollLast()] = - 1;     // 左端无更小高度, 即取到边界外
        }

        for (int i = 0; i < heights.length; i++) {
            // 不小于height[i]的部分即可作为height[i]的宽度(左, 右索引不含在内)
            ans = Math.max(ans, heights[i] * (dpRight[i] - dpLeft[i] - 1));
        }

        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
