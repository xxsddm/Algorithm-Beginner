//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1033 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {  // DP+单调栈(空间优化, 原版观察可得)
        if (matrix.length == 0) {
            return 0;
        }
        int numRow = matrix.length, numCol = matrix[0].length, maxSquare = 0;
        // 以该点为底的柱状图形(单列)高度
        int[] dp = new int[numCol];
        // 初始化第0行和第0行柱状面积
        for (int col = 0; col < numCol; col++) {
            dp[col] = matrix[0][col] == '1' ? 1 : 0;
        }
        for (int col = 0, step; col < numCol; col += step == 0 ? 1 : step) {
            step = 0;
            int square = 0;
            while (col < numCol && matrix[0][col] == '1') {
                square++;
                col++;
                step++;
            }
            maxSquare = Math.max(maxSquare, square);
        }
        // 1行~numRow-1行
        for (int row = 1; row < numRow; row++) {
            // 更新以该row层为底的高度dp
            for (int col = 0; col < numCol; col++) {
                dp[col] = matrix[row][col] == '1' ? 1 + dp[col] : 0;
            }
            // 更新以该row层为底的面积(单调栈)
            int[] rightLow = new int[numCol], leftLow = new int[numCol];
            LinkedList<Integer> stack = new LinkedList<>();
            for (int col = 0; col < numCol; col++) {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && dp[col] < dp[stack.peekLast()]) {
                        rightLow[stack.pollLast()] = col;
                    }
                }
                stack.add(col);
            }
            while (!stack.isEmpty()) {
                rightLow[stack.pollLast()] = numCol;
            }
            for (int col = numCol - 1; col >= 0; col--) {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && dp[col] < dp[stack.peekLast()]) {
                        leftLow[stack.pollLast()] = col;
                    }
                }
                stack.add(col);
            }
            while (!stack.isEmpty()) {
                leftLow[stack.pollLast()] = -1;
            }
            for (int col = 0; col < numCol; col++) {
                maxSquare = Math.max(maxSquare, (rightLow[col] - leftLow[col] - 1) * dp[col]);
            }
        }

        return maxSquare;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int maximalRectangle(char[][] matrix) {  // DP+单调栈(类似84, 但多次按行分割求面积)
        if (matrix.length == 0) {
            return 0;
        }
        int numRow = matrix.length, numCol = matrix[0].length, maxSquare = 0;
        // 以该点为底的柱状图形(单列)高度
        int[][] dp = new int[numRow][numCol];
        // 初始化第0行和第0行柱状面积
        for (int col = 0; col < numCol; col++) {
            dp[0][col] = matrix[0][col] == '1' ? 1 : 0;
        }
        for (int col = 0, step; col < numCol; col += step == 0 ? 1 : step) {
            step = 0;
            int square = 0;
            while (col < numCol && matrix[0][col] == '1') {
                square++;
                col++;
                step++;
            }
            maxSquare = Math.max(maxSquare, square);
        }
        // 1行~numRow-1行
        for (int row = 1; row < numRow; row++) {
            // 更新以该row层为底的高度dp
            for (int col = 0; col < numCol; col++) {
                dp[row][col] = matrix[row][col] == '1' ? 1 + dp[row - 1][col] : 0;
            }
            // 更新以该row层为底的面积(单调栈)
            int[] rightLow = new int[numCol], leftLow = new int[numCol];
            LinkedList<Integer> stack = new LinkedList<>();
            for (int col = 0; col < numCol; col++) {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && dp[row][col] < dp[row][stack.peekLast()]) {
                        rightLow[stack.pollLast()] = col;
                    }
                }
                stack.add(col);
            }
            while (!stack.isEmpty()) {
                rightLow[stack.pollLast()] = numCol;
            }
            for (int col = numCol - 1; col >= 0; col--) {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && dp[row][col] < dp[row][stack.peekLast()]) {
                        leftLow[stack.pollLast()] = col;
                    }
                }
                stack.add(col);
            }
            while (!stack.isEmpty()) {
                leftLow[stack.pollLast()] = -1;
            }
            for (int col = 0; col < numCol; col++) {
                maxSquare = Math.max(maxSquare, (rightLow[col] - leftLow[col] - 1) * dp[row][col]);
            }
        }

        return maxSquare;
    }
}
