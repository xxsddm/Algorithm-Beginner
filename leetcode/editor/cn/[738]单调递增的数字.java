//给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。 
//
// （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。） 
//
// 示例 1: 
//
// 输入: N = 10
//输出: 9
// 
//
// 示例 2: 
//
// 输入: N = 1234
//输出: 1234
// 
//
// 示例 3: 
//
// 输入: N = 332
//输出: 299
// 
//
// 说明: N 是在 [0, 10^9] 范围内的一个整数。 
// Related Topics 贪心 数学 👍 197 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int monotoneIncreasingDigits(int n) {
        // 前一位>后一位, 则后一位改为'9', 前一位-1, 最后需要从开始修改的位置, 从前往后改为'9'
        char[] nums = String.valueOf(n).toCharArray();
        int start = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                nums[i] = '9';
                nums[i - 1] = (char) (nums[i - 1] - 1);    // '0'=48
                start = i;
            }
        }
        if (start == 0) {
            return n;
        }
        for (int i = start; i < nums.length; i++) {
            nums[i] = '9';
        }
        return Integer.valueOf(String.valueOf(nums));
    }
}

//leetcode submit region end(Prohibit modification and deletion)
