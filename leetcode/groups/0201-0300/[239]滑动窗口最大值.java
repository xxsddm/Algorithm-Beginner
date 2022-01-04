//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [9,11], k = 2
//输出：[11]
// 
//
// 示例 5： 
//
// 
//输入：nums = [4,-2], k = 2
//输出：[4] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1113 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k)    return new int[0];
        LinkedList<Integer> container = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < k; i++) {
            while (!container.isEmpty() && nums[container.peekLast()] <= nums[i])    container.pollLast();
            container.add(i);
        }
        ans[index++] = nums[container.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!container.isEmpty() && nums[container.peekLast()] <= nums[i])    container.pollLast();
            container.add(i);
            while (container.peekFirst() <= i - k)    container.pollFirst();
            ans[index++] = nums[container.peekFirst()];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
