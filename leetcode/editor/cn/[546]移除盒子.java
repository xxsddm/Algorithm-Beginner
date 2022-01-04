//给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。 
//
// 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 
//个积分。 
//
// 当你将所有盒子都去掉之后，求你能获得的最大积分和。 
//
// 
//
// 示例 1： 
//
// 
//输入：boxes = [1,3,2,2,2,3,4,3,1]
//输出：23
//解释：
//[1, 3, 2, 2, 2, 3, 4, 3, 1] 
//----> [1, 3, 3, 4, 3, 1] (3*3=9 分) 
//----> [1, 3, 3, 3, 1] (1*1=1 分) 
//----> [1, 1] (3*3=9 分) 
//----> [] (2*2=4 分)
// 
//
// 示例 2： 
//
// 
//输入：boxes = [1,1,1]
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：boxes = [1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= boxes.length <= 100 
// 1 <= boxes[i] <= 100 
// 
// Related Topics 记忆化搜索 数组 动态规划 👍 326 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeBoxes(int[] boxes) {   // DP(边缘元素最后移除不影响结果)
        int length = boxes.length;
        // dp: [start, end] -> [start最后移除时的start元素数量, 分数]
        int[][][] dp = new int[length][length][2];
        for (int start = length - 1; start >= 0; start--) {
            dp[start][start][0] = 1;
            dp[start][start][1] = 1;
            for (int end = start + 1; end < length; end++) {
                // start~end元素相同可直接判断
                if (boxes[start] == boxes[start + 1] && dp[start + 1][end][0] == end - start) {
                    dp[start][end][0] = end - start + 1;
                    dp[start][end][1] = (end - start + 1) * (end - start + 1);
                    continue;
                }
                // [start, mid], [mid+1, end]
                for (int mid = start; mid < end; mid++) {
                    int num, temp;
                    if (boxes[start] == boxes[mid + 1]) {   // 若两部分start元素相同, 考虑合并消除
                        int num1 = dp[start][mid][0], num2 = dp[mid + 1][end][0];
                        num = num1 + num2;
                        temp = (dp[start][mid][1] - num1 * num1)
                                + (dp[mid + 1][end][1] - num2 * num2)
                                + num * num;
                    } else {
                        num = dp[start][mid][0];
                        temp = dp[start][mid][1] + dp[mid + 1][end][1];
                    }
                    if (dp[start][end][1] < temp) {
                        dp[start][end][0] = num;
                        dp[start][end][1] = temp;
                    }
                }
            }
        }
        return dp[0][length - 1][1];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
