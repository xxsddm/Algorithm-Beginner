//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 假设你总是可以到达数组的最后一个位置。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 贪心 数组 动态规划 👍 1130 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] >= nums.length - 1) {
            return 1;
        }
        int maxloc = 0, count = 0, nextStart = 0;
        while (maxloc < nums.length - 1) {      // 更新到下一步
            count++;

            int end = maxloc + nums[maxloc];
            int start = nextStart;      // 跳过上一轮已比较范围
            nextStart = end + 1;

            for (int loc = start; loc <= end; loc++) {
                if (loc + nums[loc] > maxloc + nums[maxloc]) {      // 选择下一步位置, 其作为新起点可跳跃范围要大
                    maxloc = loc;
                    // 下一步可直接跳到末端, 则提前结束
                    if (maxloc + nums[maxloc] >= nums.length - 1) {
                        return count + 1;
                    }
                }
            }
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
