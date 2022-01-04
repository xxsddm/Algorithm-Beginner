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
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {    // 不直接从右上角开始, 二分搜索查找起始"右"上角(二分可省略)
        int width = matrix.size(), length = matrix[0].size();
        int left = 0, right = length - 1;
        vector<int>& first = matrix[0];
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (first[mid] > target) {
                right = mid - 1;
            }
            else if (first[mid] < target) {
                left = mid + 1;
            }
            else {
                return true;
            }
        }
        int row = 0, column = left - 1; // 第left列均大于target
        while (row < width && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            }
            if (matrix[row][column] < target) {     // 小了只有向下
                row++;
            }
            else {      // 大了只有向左
                column--;
            }
        }
        return false;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
