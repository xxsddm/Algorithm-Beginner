//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。 
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
// 
//
// 示例 2： 
//
// 
//输入：stones = [31,26,33,21,40]
//输出：5
// 
//
// 示例 3： 
//
// 
//输入：stones = [1,2]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 327 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int target = 0;
        for (int stone: stones) {
            target += stone;
        }
        int cumsum = target;
        target >>= 1;
        // 即01背包问题, 容量为target, 重量和价值均为stones元素
        // 构造一个1/2总重量(向下取整)的bag, 无法取到部分即stones中bag外的另一部分
        // 两个部分总和相互消除, 最后保留的即为新生成的最后保留stone重量
        int[] bag = new int[target + 1];
        for (int stone : stones) {
            // 一维数组DP需从后向前, 避免状态转移时使用被修改后的前元素修改后元素
            for (int weight = target; weight >= stone; weight--) {
                bag[weight] = Math.max(bag[weight], bag[weight - stone] + stone);
            }
        }
        return cumsum - 2 * bag[target];
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int target = 0;
        for (int stone: stones) {
            target += stone;
        }
        int cumsum = target;
        target >>= 1;
        // i和weight遍历顺序可交换(空间优化后不可交换)
        int[][] bag = new int[stones.length + 1][target + 1];   // [0][xxx]代表未考虑任何石头
        for (int i = 0; i < stones.length; i++) {   // 索引i对应第i+1个石头
            for (int weight = 0; weight <= target; weight++) {
                if (weight >= stones[i]) {
                    bag[i + 1][weight] = Math.max(
                            bag[i][weight],     // 不加入石头i
                            bag[i][weight - stones[i]] + stones[i]  // 加入石头i
                    );
                }
                else {
                    bag[i + 1][weight] = bag[i][weight];    // 不考虑(因为无法加入, 不同于不加入)石头i
                }
            }
        }
        return cumsum - 2 * bag[stones.length][target];
    }
}
