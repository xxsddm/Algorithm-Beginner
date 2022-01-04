//你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。 
//
// 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5
//输出：2
//解释：因为第三行不完整，所以返回 2 。
// 
//
// 示例 2： 
//
// 
//输入：n = 8
//输出：3
//解释：因为第四行不完整，所以返回 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 二分查找 👍 130 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int arrangeCoins(int n) {    // 二分(解方程见cpp)
        int left = 1, right = 1 << 16;  // 考虑输入范围1~(2^31)-1
        while (left <= right) {
            int mid = (left + right) >> 1;
            long numMid = (long) (mid + 1) * mid >> 1;
            if (numMid > n) {
                right = mid - 1;
            }
            else if (numMid < n) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return right;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
