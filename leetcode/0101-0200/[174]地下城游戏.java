//
//
// 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿
//过地下城并通过对抗恶魔来拯救公主。 
//
// 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。 
//
// 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么
//包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。 
//
// 为了尽快到达公主，骑士决定每次只向右或向下移动一步。 
//
// 
//
// 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。 
//
// 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。 
//
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
//
//
// 
//
// 说明: 
//
// 
// 
// 骑士的健康点数没有上限。 
// 
// 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。 
// Related Topics 数组 动态规划 矩阵 👍 511 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 其实是公主救骑士
    public int calculateMinimumHP(int[][] dungeon) {    // DP(从右下角向左上角更新)(原地修改见cpp, 观察可得)
        int numRow = dungeon.length, numCol = dungeon[0].length;
        // 从该节点到达目标点, 最大剩余成本(负数代表成本)(必然从后往前更新)
        int[][] dp = new int[numRow][numCol];

        // 初始化
        dp[numRow - 1][numCol - 1] = dungeon[numRow - 1][numCol - 1];
        for (int i = numRow - 1; i > 0; i--) {
            // 类似最大子序和问题, 若后面的最大剩余成本为负(值为正), 则当前最大剩余成本为当前值(temp)
            dp[i - 1][numCol - 1] = Math.min(dp[i][numCol - 1], 0)
                    + dungeon[i - 1][numCol - 1];
        }
        for (int i = numCol - 1; i > 0; i--) {
            dp[numRow - 1][i - 1] = Math.min(dp[numRow - 1][i], 0)
                    + dungeon[numRow - 1][i - 1];
        }

        for (int row = numRow - 2; row >= 0; row--) {
            for (int col = numCol - 2; col >= 0; col--) {
                dp[row][col] = Math.min(
                        Math.max(dp[row + 1][col],dp[row][col + 1]),
                        0) + dungeon[row][col];
            }
        }

        return 1 - Math.min(dp[0][0], 0);
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {    // DP(从右下角向左上角更新)(待优化)
        int numRow = dungeon.length, numCol = dungeon[0].length;
        // 从该节点到达目标点(必然从后往前更新), 最大剩余成本(负数代表成本)
        int[][][] dp = new int[numRow][numCol][2];

        dp[numRow - 1][numCol - 1][0] = dungeon[numRow - 1][numCol - 1];
        dp[numRow - 1][numCol - 1][1] = dungeon[numRow - 1][numCol - 1];
        for (int i = numRow - 1; i > 0; i--) {
            // 类似最大子序和问题(这里是最小后子序和问题), 若后面的最大剩余成本为负(值为正), 则当前最大剩余成本为当前值(temp)
            int temp = dungeon[i - 1][numCol - 1];
            dp[i - 1][numCol - 1][0] = dp[i][numCol - 1][0] + temp;
            dp[i - 1][numCol - 1][1] = Math.min(dp[i - 1][numCol - 1][0], Math.min(dp[i][numCol - 1][1], 0) + temp);
        }
        for (int i = numCol - 1; i > 0; i--) {
            int temp = dungeon[numRow - 1][i - 1];
            dp[numRow - 1][i - 1][0] = dp[numRow - 1][i][0] + temp;
            dp[numRow - 1][i - 1][1] = Math.min(dp[numRow - 1][i - 1][0], Math.min(dp[numRow - 1][i][1], 0) + temp);
        }

        for (int row = numRow - 2; row >= 0; row--) {
            for (int col = numCol - 2; col >= 0; col--) {
                int temp = dungeon[row][col], tempRow = dp[row + 1][col][1], tempCol = dp[row][col + 1][1];
                if (tempRow > tempCol) {
                    dp[row][col][0] = dp[row + 1][col][0] + temp;
                    dp[row][col][1] = Math.min(dp[row][col][0], Math.min(tempRow, 0) + temp);
                } else if (tempRow < tempCol) {
                    dp[row][col][0] = dp[row][col + 1][0] + temp;
                    dp[row][col][1] = Math.min(dp[row][col][0], Math.min(tempCol, 0) + temp);
                } else {
                    dp[row][col][0] = Math.max(dp[row + 1][col][0], dp[row][col + 1][0]) + temp;
                    dp[row][col][1] = Math.min(dp[row][col][0], Math.min(tempRow, 0) + temp);
                }
            }
        }

        return 1 - Math.min(dp[0][0][1], 0);
    }
}

// 错误思路(不可以从左上角开始, 选择方向从左/上更新到该点时无法选择非直接相邻的点, 这些点已经被剔除)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int numRow = dungeon.length, numCol = dungeon[0].length;

        // 到达该索引位置的最低初始状态(>=1)
        int[][] dp = new int[numRow][numCol];
        // 以最低初始状态抵达后的剩余值
        int[][] rest = new int[numRow][numCol];

        // 初始化
        dp[0][0] = 1 - Math.min(dungeon[0][0], 0);
        rest[0][0] = 1 + Math.max(dungeon[0][0], 0);
        for (int row = 0; row < numRow - 1; row++) {
            // 剩余足够则+0, 剩余不够则+不足部分
            dp[row + 1][0] = dp[row][0] + Math.max(0, - dungeon[row + 1][0] - rest[row][0] + 1);
            rest[row + 1][0] = dp[row + 1][0] > dp[row][0] ? 1 : (rest[row][0] + dungeon[row + 1][0]);
        }
        for (int col = 0; col < numCol - 1; col++) {
            dp[0][col + 1] = dp[0][col] + Math.max(0, - dungeon[0][col + 1] - rest[0][col] + 1);
            rest[0][col + 1] = dp[0][col + 1] > dp[0][col] ? 1 : (rest[0][col] + dungeon[0][col + 1]);
        }

        // 从左上角开始向右下角更新
        for (int row = 1; row < numRow; row++) {
            for (int col = 1; col < numCol; col++) {
                int dpLeft, dpUp;
                dpLeft = dp[row][col - 1] + Math.max(0, - dungeon[row][col] - rest[row][col - 1] + 1);
                dpUp = dp[row - 1][col] + Math.max(0, - dungeon[row][col] - rest[row - 1][col] + 1);
                // 这里选择邻点时没有考虑已经被淘汰的不相邻点的可能性, 不相邻点由于剩余值较多被淘汰, 但可能用于非相邻点
                if (dpLeft < dpUp) {
                    dp[row][col] = dpLeft;
                    rest[row][col] = dp[row][col] > dp[row][col - 1] ? 1 : (rest[row][col - 1] + dungeon[row][col]);
                }
                else if (dpLeft > dpUp) {
                    dp[row][col] = dpUp;
                    rest[row][col] = dp[row][col] > dp[row - 1][col] ? 1 : (rest[row - 1][col] + dungeon[row][col]);
                }
                else {
                    dp[row][col] = dpLeft;
                    rest[row][col] = Math.max(dp[row][col] > dp[row][col - 1] ? 1 : (rest[row][col - 1] + dungeon[row][col]),
                            dp[row][col] > dp[row - 1][col] ? 1 : (rest[row - 1][col] + dungeon[row][col]));
                }
            }
        }
        return dp[numRow - 1][numCol - 1];
    }
}
