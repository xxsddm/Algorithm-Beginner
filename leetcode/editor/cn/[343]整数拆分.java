//给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
//
// 示例 1: 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 说明: 你可以假设 n 不小于 2 且不大于 58。 
// Related Topics 数学 动态规划 👍 577 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int integerBreak(int n) {
        int[] nums = new int[n];    // i索引表示 数i+1 对应最大组合乘积
        for (int i = 1; i < n; i++) {
            int maxnum = 0;
            // 可能是DP数组前部分碎片化乘积(i+1-j-1)和剩余部分值(j)的乘积
            for (int j = 1; j < i; j++) {
                maxnum = Math.max(maxnum, nums[i - j] * j);
            }
            // 也可能是不采用碎片化乘积, 而拆成两部分乘积(i+1-j,j)
            for (int j = 1; j <= Math.sqrt(i + 1) + 1; j++) {
                maxnum = Math.max(maxnum, (i - j + 1) * j);
            }
            nums[i] = maxnum;
        }
        return nums[n - 1];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
