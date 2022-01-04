//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 836 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int width = matrix[0].length, height = matrix.length;
        int count = 0;
        List<Integer> container = new ArrayList<>(width * height);
        while (count + 1 <= width >> 1 && count + 1 <= height >> 1){
            for (int up = count; up < width - count - 1; up++) {
                container.add(matrix[count][up]);
            }
            for (int right = count; right < height - count - 1; right++) {
                container.add(matrix[right][width - count - 1]);
            }
            for (int down = count; down < width - count - 1; down++) {
                container.add(matrix[height - count - 1][width - down - 1]);
            }
            for (int left = count; left < height - count - 1; left++) {
                container.add(matrix[height - left - 1][count]);
            }
            count++;
        }
        if (height - (count << 1) == 1) {
            for (int i = 0; i < width - 2 * count; i++) container.add(matrix[count][count + i]);
        }
        else if (width - (count << 1) == 1) {
            for (int i = 0; i < height - 2 * count; i++) container.add(matrix[count + i][count]);
        }
        return container;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
