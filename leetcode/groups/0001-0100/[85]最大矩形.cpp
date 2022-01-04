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
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1034 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {    // DP+单调栈(空间优化, 原版见java)
        if (matrix.empty()) {
            return 0;
        }
        int numRow = matrix.size(), numCol = matrix[0].size(), maxSquare = 0;
        // 以该点为底的柱状图形(单列)高度
        vector<int> dp(numCol);
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
            maxSquare = max(maxSquare, square);
        }
        // 1行~numRow-1行
        for (int row = 1; row < numRow; row++) {
            // 更新以该row层为底的高度dp
            for (int col = 0; col < numCol; col++) {
                dp[col] = matrix[row][col] == '1' ? 1 + dp[col] : 0;
            }
            // 更新以该row层为底的面积(单调栈)
            vector<int> rightLow(numCol);
            vector<int> leftLow(numCol);
            stack<int> stack;
            for (int col = 0; col < numCol; col++) {
                if (!stack.empty()) {
                    while (!stack.empty() && dp[col] < dp[stack.top()]) {
                        rightLow[stack.top()] = col;
                        stack.pop();
                    }
                }
                stack.push(col);
            }
            while (!stack.empty()) {
                rightLow[stack.top()] = numCol;
                stack.pop();
            }
            for (int col = numCol - 1; col >= 0; col--) {
                if (!stack.empty()) {
                    while (!stack.empty() && dp[col] < dp[stack.top()]) {
                        leftLow[stack.top()] = col;
                        stack.pop();
                    }
                }
                stack.push(col);
            }
            while (!stack.empty()) {
                leftLow[stack.top()] = -1;
                stack.pop();
            }
            for (int col = 0; col < numCol; col++) {
                maxSquare = max(maxSquare, (rightLow[col] - leftLow[col] - 1) * dp[col]);
            }
        }

        return maxSquare;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
