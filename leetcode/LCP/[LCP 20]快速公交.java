//小扣打算去秋日市集，由于游客较多，小扣的移动速度受到了人流影响：
//- 小扣从 `x` 号站点移动至 `x + 1` 号站点需要花费的时间为 `inc`；
//- 小扣从 `x` 号站点移动至 `x - 1` 号站点需要花费的时间为 `dec`。
//
//现有 `m` 辆公交车，编号为 `0` 到 `m-1`。小扣也可以通过搭乘编号为 `i` 的公交车，从 `x` 号站点移动至 `jump[i]*x` 号站点
//，耗时仅为 `cost[i]`。小扣可以搭乘任意编号的公交车且搭乘公交次数不限。
//
//假定小扣起始站点记作 `0`，秋日市集站点记作 `target`，请返回小扣抵达秋日市集最少需要花费多少时间。由于数字较大，最终答案需要对 10000000
//07 (1e9 + 7) 取模。
//
//注意：小扣可在移动过程中到达编号大于 `target` 的站点。
//
//**示例 1：**
//>输入：`target = 31, inc = 5, dec = 3, jump = [6], cost = [10]`
//>
//>输出：`33`
//>
//>解释：
//>小扣步行至 1 号站点，花费时间为 5；
//>小扣从 1 号站台搭乘 0 号公交至 6 * 1 = 6 站台，花费时间为 10；
//>小扣从 6 号站台步行至 5 号站台，花费时间为 3；
//>小扣从 5 号站台搭乘 0 号公交至 6 * 5 = 30 站台，花费时间为 10；
//>小扣从 30 号站台步行至 31 号站台，花费时间为 5；
//>最终小扣花费总时间为 33。
//
//
//**示例 2：**
//>输入：`target = 612, inc = 4, dec = 5, jump = [3,6,8,11,5,10,4], cost = [4,7,6,3
//,7,6,4]`
//>
//>输出：`26`
//>
//>解释：
//>小扣步行至 1 号站点，花费时间为 4；
//>小扣从 1 号站台搭乘 0 号公交至 3 * 1 = 3 站台，花费时间为 4；
//>小扣从 3 号站台搭乘 3 号公交至 11 * 3 = 33 站台，花费时间为 3；
//>小扣从 33 号站台步行至 34 站台，花费时间为 4；
//>小扣从 34 号站台搭乘 0 号公交至 3 * 34 = 102 站台，花费时间为 4；
//>小扣从 102 号站台搭乘 1 号公交至 6 * 102 = 612 站台，花费时间为 7； 
//>最终小扣花费总时间为 26。
//
//
//**提示：**
//- `1 <= target <= 10^9`
//- `1 <= jump.length, cost.length <= 10`
//- `2 <= jump[i] <= 10^6`
//- `1 <= inc, dec, cost[i] <= 10^6` Related Topics 记忆化搜索 数组 动态规划 👍 30 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int inc, dec;
    int[] jump, cost;
    final int MOD = 1000000007;
    HashMap<Long, Long> container = new HashMap<>();

    // 从目标点target回退到0
    public int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        this.inc = inc;
        this.dec = dec;
        this.jump = jump;
        this.cost = cost;
        return (int) (dfs(target) % MOD);
    }

    private long dfs(long loc) {
        if (loc == 0) {
            return 0;
        }
        if (loc == 1) {
            return inc;
        }
        if (container.containsKey(loc)) {
            return container.get(loc);
        }
        // 考虑从原点0步行到loc
        long minCost = loc * inc;

        // 考虑公交车(可能搭配步行)到loc(不一定从原点0)
        for (int bus = 0; bus < jump.length; bus++) {
            int step = jump[bus], fare = cost[bus];
            if (loc % step == 0) {  // 刚好该位置可由某公交抵达
                // 倒推出前一站位置
                minCost = Math.min(minCost, dfs(loc / step) + fare);
            }
            else {  // 不可由公交刚好抵达则步行剩余路程,不可能乘公交完成剩余路程,这种情况已在其他公交(bus其他取值)时考虑
                long before = loc % step, after = step - loc % step;
                minCost = Math.min(minCost,
                        Math.min(dfs(loc / step) + before * inc + fare, // 在loc前下车
                                dfs(loc / step + 1) + after * dec + fare)); // 在loc后下车
            }
        }
        container.put(loc, minCost);
        return minCost;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
