//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。 
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。 
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 注意：不允许旋转信封。 
// 
//
// 示例 1： 
//
// 
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。 
//
// 示例 2： 
//
// 
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 10⁴ 
// 
// Related Topics 数组 二分查找 动态规划 排序 👍 584 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {  // DP(复杂度高,二分见java)
        int length = (int) envelopes.size();
        if (length <= 1) {
            return length;
        }
        int ans = 1;
        vector<int> dp(length, 1);
        // 先按照某一个分量排序(保证end一定在prev后面)
        sort(envelopes.begin(), envelopes.end(), [](auto& o1, auto& o2) {
            return o1[0] < o2[0];
        });
        for (int end = 1; end < length; end++) {    // 枚举所有元素作为末尾元素
            vector<int> pivot = envelopes[end];
            for (int prev = end - 1; prev >= 0; prev--) {   // 枚举末尾元素的前一个元素
                if (pivot[0] > envelopes[prev][0] && pivot[1] > envelopes[prev][1]) {
                    dp[end] = max(dp[end], dp[prev] + 1);   // 选择最长子序列
                }
            }
            ans = max(ans, dp[end]);
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
