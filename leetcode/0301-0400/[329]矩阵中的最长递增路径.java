//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。 
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4 
//解释：最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2： 
//
// 
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4 
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// 0 <= matrix[i][j] <= 2³¹ - 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 👍 520 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol, ans = 0;
    int[][] nums;
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {  // DFS记忆化搜索
        numRow = matrix.length;
        numCol = matrix[0].length;
        nums = matrix;
        dp = new int[numRow][numCol];
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                ans = Math.max(ans, dfs(row, col));
            }
        }
        return ans;
    }

    private int dfs(int row, int col) {
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int num = nums[row][col], maxRest = 0;  // maxRest为向四个方向移动的最长递增路径长度
        // 枚举四个移动方向
        if (row > 0 && nums[row - 1][col] > num) {
            maxRest = Math.max(maxRest, dfs(row - 1, col));
        }
        if (row < numRow - 1 && nums[row + 1][col] > num) {
            maxRest = Math.max(maxRest, dfs(row + 1, col));
        }
        if (col > 0 && nums[row][col - 1] > num) {
            maxRest = Math.max(maxRest, dfs(row, col - 1));
        }
        if (col < numCol - 1 && nums[row][col + 1] > num) {
            maxRest = Math.max(maxRest, dfs(row, col + 1));
        }
        dp[row][col] = 1 + maxRest; // 当前点算一个
        return 1 + maxRest;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
