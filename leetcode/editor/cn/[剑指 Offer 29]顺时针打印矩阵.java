//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 矩阵 模拟 
// 👍 282 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int width = matrix[0].length, height = matrix.length;
        int count = 0, size = 0;
        int[] container = new int[width * height];
        while (count + 1 <= width >> 1 && count + 1 <= height >> 1){
            for (int up = count; up < width - count - 1; up++) {
                container[size++] = matrix[count][up];
            }
            for (int right = count; right < height - count - 1; right++) {
                container[size++] = matrix[right][width - count - 1];
            }
            for (int down = count; down < width - count - 1; down++) {
                container[size++] = matrix[height - count - 1][width - down - 1];
            }
            for (int left = count; left < height - count - 1; left++) {
                container[size++] = matrix[height - left - 1][count];
            }
            count++;
        }
        if (height - (count << 1) == 1) {
            for (int i = 0; i < width - 2 * count; i++) container[size++] = matrix[count][count + i];
        }
        else if (width - (count << 1) == 1) {
            for (int i = 0; i < height - 2 * count; i++) container[size++] = matrix[count + i][count];
        }
        return container;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
