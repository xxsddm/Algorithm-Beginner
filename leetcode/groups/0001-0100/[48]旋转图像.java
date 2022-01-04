//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：[[1]]
// 
//
// 示例 4： 
//
// 
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
// 
//
// 
//
// 提示： 
//
// 
// matrix.length == n 
// matrix[i].length == n 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
// Related Topics 数组 数学 矩阵 👍 987 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[][] matrix) {    // DP
        int layer = 0;  // 第layer+1层方形
        int length = matrix.length, half = length >> 1, tempup;
        while (layer < half) {   // 逆时针修改(四个元素一组修改)
            for (int i = layer; i < length - layer - 1; i++) {
                tempup = matrix[layer][i];  // 备份上边元素
                // 被填入下一边后再修改
                matrix[layer][i] = matrix[length - i - 1][layer];   // 上(用左)
                matrix[length - i - 1][layer] = matrix[length - layer - 1][length - i - 1]; // 左(用下)
                matrix[length - layer - 1][length - i - 1] = matrix[i][length - layer - 1]; // 下(用右)
                matrix[i][length - layer - 1] = tempup; // 右(用备份元素)
            }
            layer++;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public void rotate(int[][] matrix) {    // 使用一维辅助数组(没必要)
        int layer = 0;  // 第layer+1层方形
        int length = matrix.length, half = length >> 1;
        while (layer < half) {  // 逆时针修改(逐边修改)
            // 备份上边(左含右不含)
            int[] tempup = new int[length - 2 * layer - 1];
            for (int i = 0; i < tempup.length; i++) {
                tempup[i] = matrix[layer][layer + i];
            }
            // 上
            for (int i = layer; i < layer + tempup.length; i++) {
                matrix[layer][i] = matrix[length - i - 1][layer];
            }
            // 左
            for (int i = layer; i < layer + tempup.length; i++) {
                matrix[length - i - 1][layer] = matrix[length - layer - 1][length - i - 1];
            }
            // 下
            for (int i = layer; i < layer + tempup.length; i++) {
                matrix[length - layer - 1][length - i - 1] = matrix[i][length - layer - 1];
            }
            // 右
            for (int i = layer; i < layer + tempup.length; i++) {
                matrix[i][length - layer - 1] = tempup[i - layer];
            }
            layer++;
        }
    }
}
