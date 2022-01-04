//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 分治 矩阵 👍 709 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int target, length, width;
    int[][] nums;
    boolean[][] used;

    // DFS(从左上角开始, 有右, 下两种更新路径, 效果差)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix[0][0] > target) {
            return false;
        }
        length = matrix[0].length;
        width = matrix.length;
        this.target = target;
        nums = matrix;
        used = new boolean[width][length];
        return search(0, 0);
    }

    private boolean search(int row, int column) {
        if (nums[row][column] == target) {
            return true;
        }
        used[row][column] = true;
        boolean ans = false;
        if (column < length - 1 && nums[row][column + 1] <= target && !used[row][column + 1]) {
            ans = search(row, column + 1);
        }
        if (ans) {
            return true;
        }
        if (row < width - 1 && nums[row + 1][column] <= target && !used[row + 1][column]) {
            ans = search(row + 1, column);
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
