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
// Related Topics 动态规划 👍 183 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int kInversePairs(int n, int k) {   // DP(前缀和减少重复计算)(记忆化搜索很慢,见java)
        int mod = (int) 1e9 + 7;
        // j -> 逆序数为j的组合数量
        int dp[k + 1], cumsum[k + 1];
        memset(dp, 0, sizeof(dp));
        memset(cumsum, 0, sizeof(cumsum));
        dp[0] = 1;
        // 考虑1~i共i个数
        for (int i = 2; i <= n; i++) {
            for (int j = 0, sum = 0; j <= k; j++) {
                sum = (sum + dp[j]) % mod;
                cumsum[j] = sum;
            }
            for (int j = 1; j <= k; j++) {
                // 当前加入数字i,在之前的1~(i-1)中选择位置加入,可能产生0~(i-1)个逆序
                // 则更新dp[j]为前轮dp中可以通过i的位置增加逆序数达到j逆序的组合数
                // 即j=dx+(j-dx),1<=dx<=i-1
                int start = max(j - i + 1, 0), end = j - 1;     // start~end可通过i的位置叠加到j个逆序
                dp[j] += cumsum[end] - (start == 0 ? 0 : cumsum[start - 1]);
                if (dp[j] < 0) {
                    dp[j] += mod;
                }
                dp[j] %= mod;
            }
        }
        return dp[k];
    }
};


//leetcode submit region end(Prohibit modification and deletion)
