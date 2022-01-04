//给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。 
//
// 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。 
//
// 由于答案可能很大，只需要返回 答案 mod 10⁹ + 7 的值。 
//
// 示例 1: 
//
// 
//输入: n = 3, k = 0
//输出: 1
//解释: 
//只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
// 
//
// 示例 2: 
//
// 
//输入: n = 3, k = 1
//输出: 2
//解释: 
//数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
// 
//
// 说明: 
//
// 
// n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。 
// 
// Related Topics 动态规划 👍 137 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int kInversePairs(int n, int k) {    // DP(前缀和减少重复计算)
        int mod = (int) 1e9 + 7;
        // j -> 逆序数为j的组合数量
        int[] dp = new int[k + 1];
        dp[0] = 1;
        // 考虑1~i共i个数
        for (int i = 2; i <= n; i++) {
            int[] cumsum = new int[k + 1];
            for (int j = 0, sum = 0; j <= k; j++) {
                sum = (sum + dp[j]) % mod;
                cumsum[j] = sum;
            }
            for (int j = 1; j <= k; j++) {
                // 当前加入数字i,在之前的1~(i-1)中选择位置加入,可能产生0~(i-1)个逆序
                // 则更新dp[j]为前轮dp中可以通过i的位置增加逆序数达到j逆序的组合数
                // 即j=dx+(j-dx),1<=dx<=i-1
                int start = Math.max(j - i + 1, 0), end = j - 1;    // start~end可通过i的位置叠加到j个逆序
                dp[j] += cumsum[end] - (start == 0 ? 0 : cumsum[start - 1]);
                if (dp[j] < 0) {
                    dp[j] += mod;
                }
                dp[j] %= mod;
            }
        }
        return dp[k];
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int kInversePairs(int n, int k) {    // DP(含重复计算)
        int mod = (int) 1e9 + 7;
        // j -> 逆序数为j的组合数量
        int[] dp = new int[k + 1];
        dp[0] = 1;
        // 考虑1~i共i个数
        for (int i = 2; i <= n; i++) {
            int[] temp = dp.clone();
            for (int j = 0; j <= k; j++) {
                // 新加入较大数i,其左侧较小数数量numSmaller即新增逆序数
                // 新增逆序数上限为之前的数字数量i-1, 或要求逆序数-当前逆序数
                int limit = Math.min(i - 1, k - j);
                for (int numSmaller = 1; numSmaller <= limit; numSmaller++) {   // 重复计算较多
                    temp[j + numSmaller] += dp[j];
                    temp[j + numSmaller] %= mod;
                }
            }
            dp = temp;
        }
        return dp[k];
    }
}

class Solution {
    int mod = (int) 1e9 + 7;
    int[][] container;

    public int kInversePairs(int n, int k) {    // 记忆化搜索
        container = new int[n + 1][k + 1];
        return dfs(n, k);
    }

    // dfs(n,k)到dfs(n,1)有重复计算,速度很慢
    int dfs(int n, int k) {     // 给定n个从小到大排序的数字,给定逆序对k,求组合数量
        if (k == 0) {
            return 1;
        }
        int maxNum = n * (n - 1) / 2;
        if (k == maxNum) {
            container[n][k] = 1;
            return 1;
        }
        if (container[n][k] != 0) {
            return container[n][k];
        }
        // n-1个剩余数最多组成(n-1)(n-2)/2个逆序对,因此k过大直接返回0,可跳过,保证下一层dfs
        // 抽出当前n个数字中的第i个作为下一个元素,但不可超过逆序对要求数量
        int ans = 0, start = Math.max(1, k + 1 - (n - 1) * (n - 2) / 2), end = Math.min(n, k + 1);
        for (int i = start; i <= end; i++) {
            // 抽出第i个数字,会和前面i-1个数字产生i-1个逆序对,下一层dfs减少i-1个需求
            ans += dfs(n - 1, k - i + 1);
            ans %= mod;
        }
        container[n][k] = ans;
        return ans;
    }
}
