//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件： 
//
// 
// 第 i 位的数字能被 i 整除 
// i 能被第 i 位上的数字整除 
// 
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？ 
//
// 示例1: 
//
// 
//输入: 2
//输出: 2
//解释: 
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
// 
//
// 说明: 
//
// 
// N 是一个正整数，并且不会超过15。 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 172 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countArrangement(int n) {
        int maxnum = (1 << n) - 1;      // 全部被使用时的状态(即填满)
        int[] dp = new int[maxnum + 1];     // 使用状态 -> 种类数量  (已加入数字数量即为使用状态中1的数量)
        dp[0] = 1;
        for (int state = 0; state < maxnum; state++) {
            for (int num = 1; num <= n; num++) {        // 找出未使用数字
                // 待填入第temp1个数字(1<=temp<=n, 下标从1开始)
                int temp1 = Integer.bitCount(state) + 1, temp2 = 1 << num - 1;
                if ((temp1 % num == 0 || num % temp1 == 0) && (state & temp2) == 0) {
                    dp[state | temp2] += dp[state];
                }
            }
        }
        return dp[maxnum];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    int num;
    int used = 0;
    int[] container;

    public int countArrangement(int n) {            // 记忆化搜索DFS
        num = n;
        // used(二进制保存使用过的点)的相应组合数
        container = new int[(1 << n) - 1];        // 对比后面的方法, used自身已经可以记录idx(数二进制中1的个数)
        return backtrack(used);
    }

    private int backtrack(int binary_used) {
        int count = 0;
        for (int i = 0; i < num; i++) {
            int temp = binary_used;
            int idx = 1;        // 按理说应该是从idx=0开始, 但idx=1也是对的而且更快, 没想通
            while (temp > 0) {
                temp -= (temp & -temp);
                idx++;
            }
            // 填入过的数i+1, 对于其索引i: (used & 1 << i) != 0
            if ((used & 1 << i) == 0 && ((i + 1) % (idx + 1) == 0 || (idx + 1) % (i + 1) == 0)) {
                used |= 1 << i;
                if (idx + 1 < num){
                    if (container[used] == 0) {
                        container[used] = backtrack(used);
                    }
                    count += container[used];
                }
                else {
                    count += 1;     // 下一个待填入的idx=idx+1为num, 代表当前已填满, 直接返回相应组合数1, 替代终止条件
                }
                used -= 1 << i;
            }
        }
        return count;
    }
}


class Solution {
    int num;
    int used = 0;
    int[][] container;

    public int countArrangement(int n) {            // 记忆化搜索
        num = n;
        // idx, used: 相应组合数(used取(1 << n) - 1时idx必然为n, 直接返回1, 不必占用空间)
        container = new int[n][(1 << n) - 1];
        return backtrack(0);
    }

    private int backtrack(int idx) {
        int count = 0;
        for (int i = 0; i < num; i++) {
            // 填入过的数i+1, 对于其索引i: (used & 1 << i) != 0
            if ((used & 1 << i) == 0 && ((i + 1) % (idx + 1) == 0 || (idx + 1) % (i + 1) == 0)) {
                used |= 1 << i;
                if (idx + 1 < num){
                    if (container[idx + 1][used] == 0) {
                        container[idx + 1][used] = backtrack(idx + 1);
                    }
                    count += container[idx + 1][used];
                }
                else {
                    count += 1;     // 下一个待填入的idx=idx+1为num, 代表当前已填满, 直接返回相应组合数1, 替代终止条件
                }
                used -= 1 << i;
            }
        }
        return count;
    }
}
