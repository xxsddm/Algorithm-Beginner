//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 1820 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {     // DP(空间优化)
        int[] nums = new int[2];
        int temp = n - 1;
        nums[0] = 1;
        nums[1] = 2;
        while (temp > 1) {
            int back = nums[1];
            nums[1] += nums[0];
            nums[0] = back;
            temp--;
        }
        return nums[temp];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int climbStairs(int n) {     // DP
        if (n == 1) {
            return 1;
        }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 2; i < n; i++) {
            // 最后一步+1, 则与i-1数量相同; 最后一步+2, 则与i-2数量相同. 全部两种情况相加
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n - 1];
    }
}


class Solution {
    public int climbStairs(int n) {     // DP(完全背包)
        int[] bag = new int[n + 1];
        bag[0] = 1;
        for (int num = 0; num < n + 1; num++) {
            for (int step = 1; step <= 2; step++) {
                if (num - step >= 0) {
                    bag[num] += bag[num - step];
                }
            }
        }
        return bag[n];
    }
}
