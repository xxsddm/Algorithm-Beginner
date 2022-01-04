//一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。 
//
// 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。 
//
// 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [0,1,3,5,6,8,12,17]
//输出：true
//解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然
//后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。 
//
// 示例 2： 
//
// 
//输入：stones = [0,1,2,3,4,8,9,11]
//输出：false
//解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。 
//
// 
//
// 提示： 
//
// 
// 2 <= stones.length <= 2000 
// 0 <= stones[i] <= 2³¹ - 1 
// stones[0] == 0 
// 
// Related Topics 数组 动态规划 👍 369 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canCross(int[] stones) { // DP(二维布尔数组,第二个维度用索引表示可行的步长)
        int length = stones.length;
        for (int i = 0; i < length - 1; i++) {
            // 索引+1 即索引石块出发能跳跃最大距离
            if (stones[i + 1] - stones[i] > i + 1) {
                return false;
            }
        }
        if (length == 2) {
            return true;
        }

        // end,step -> 跳到end石块时是否可用step步长
        boolean[][] dp = new boolean[length][length];
        dp[1][1] = true;
        for (int end = 2; end < length - 1; end++) {
            for (int start = end - 1; start >= 1; start--) {
                int distance = stones[end] - stones[start];
                if (start + 1 < distance) {
                    break;
                }
                dp[end][distance] = dp[start][distance - 1] || dp[start][distance] || dp[start][distance + 1];
            }
        }

        // 终点单独考虑(也可加入上面的循环)
        for (int start = length - 2; start >= 1; start--) {
            int distance = stones[length - 1] - stones[start];
            if (start + 1 < distance) {
                break;
            }
            if (dp[start][distance - 1] || dp[start][distance] || dp[start][distance + 1]) {
                return true;
            }
        }

        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public boolean canCross(int[] stones) { // DP(一维哈希数组)
        int length = stones.length;
        for (int i = 0; i < length - 1; i++) {
            // 索引+1 即索引石块出发能跳跃最大距离
            if (stones[i + 1] - stones[i] > i + 1) {
                return false;
            }
        }
        if (length == 2) {
            return true;
        }

        // 第i-1个石头(不是石头位置) -> 跳到该石头时的(所有可行)步长
        HashSet<Integer>[] dp = (HashSet<Integer>[]) new HashSet[length];
        // 第i-1个石头(不是石头位置) -> 跳到该石头时的最大步长(用于剪枝)
        for (int i = 1; i < length; i++) {
            dp[i] = new HashSet<>();
        }
        dp[1].add(1);

        // 终点之前的石头
        for (int loc = 2; loc < length - 1; loc++) {
            for (int prev = loc - 1; prev >= 1; prev--) {
                int distance = stones[loc] - stones[prev];
                if (prev + 1 < distance) { // 不可能从更前方的石块跳到当前石块
                    break;
                }
                HashSet<Integer> steps = dp[prev];
                // 前方某石块处存在可以跳到当前石块的步长
                if (steps.contains(distance - 1)
                        || steps.contains(distance)
                        || steps.contains(distance + 1)) {
                    dp[loc].add(distance);
                }
            }
        }

        // 终点单独考虑(也可加入上面的循环)
        for (int prev = length - 1; prev >= 1; prev--) {
            int distance = stones[length - 1] - stones[prev];
            if (prev + 1 < distance) {
                break;
            }
            HashSet<Integer> steps = dp[prev];
            if (steps.contains(distance - 1)
                    || steps.contains(distance)
                    || steps.contains(distance + 1)) {
                return true;
            }
        }

        return false;
    }
}
