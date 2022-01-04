//如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也
//视作摆动序列。 
//
// 
// 
// 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。 
// 
// 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一
//个差值为零。 
// 
//
// 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。 
//
// 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,7,4,9,2,5]
//输出：6
//解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,17,5,10,13,15,10,5,16,8]
//输出：7
//解释：这个序列包含几个长度为 7 摆动序列。
//其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3,4,5,6,7,8,9]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 1000 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度完成此题? 
// Related Topics 贪心 数组 动态规划 👍 487 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 拼接上升和下降子区间的末端点(前端点一定属于前部分子序列可以延伸的范围), 每次拼接长度+1
        int length = 1, start = 1, diff = 0;

        for (int i = 1; i < nums.length; i++) { // 找到初始上升或下降区间
            if (nums[i] - nums[0] != 0) {
                diff = nums[i] - nums[0];
                start = i;
                length++;
                break;
            }
        }

        for (int i = start + 1; i < nums.length; i++) {
            int tempdiff = nums[i] - nums[i - 1];
            if (tempdiff * diff < 0) {  // >=0 则说明可以将之前子序列末端改为当前点
                length++;
                diff = tempdiff;
            }
        }

        return length;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int wiggleMaxLength(int[] nums) {
        int uplength = 1, downlength = 1;       // 最末端上升(up), 下降(down)的子序列长度

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                uplength = downlength + 1;      // 前面下降子序列长度不再增加的区间到当前上升区间之间一定是单调非减
            }
            else if (nums[i] < nums[i - 1]) {
                downlength = uplength + 1;
            }
        }

        return Math.max(uplength, downlength);
    }
}
