//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。 
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -100 <= matrix[i][j] <= 100 
// -10⁵ <= k <= 10⁵ 
// 
//
// 
//
// 进阶：如果行数远大于列数，该如何设计解决方案？ 
// Related Topics 数组 二分查找 动态规划 矩阵 有序集合 👍 365 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int ans = INT_MIN, limit, length;

    int maxSumSubmatrix(vector<vector<int>>& matrix, int k) {   // 二维前缀和+归并
        limit = k;
        int numRow = (int) matrix.size(), numCol = (int) matrix[0].size();
        // 转换成[0][0]~[row][col]的矩形区域和
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                matrix[row][col] += (row > 0 ? matrix[row - 1][col] : 0)
                                    + (col > 0 ? matrix[row][col - 1] : 0)
                                    - (row > 0 && col > 0 ? matrix[row - 1][col - 1] : 0);
            }
        }
        // 给定行(列)的范围,计算列(行)的前缀和
        // 若左右两部分已排序,则容易计算连续部分和不大于k的最大值
        if (numRow < numCol) {
            length = numCol;
            for (int startRow = 0; startRow < numRow; startRow++) {
                for (int endRow = startRow; endRow < numRow; endRow++) {
                    int nums[numCol + 1], aux[numCol + 1];
                    nums[0] = 0;
                    for (int i = 0; i < numCol; i++) {
                        nums[i + 1] = matrix[endRow][i] - (startRow > 0 ? matrix[startRow - 1][i] : 0);
                    }
                    sort(nums, aux, 0, length);
                    if (ans == k) {
                        return k;
                    }
                }
            }
        } else {
            length = numRow;
            for (int startCol = 0; startCol < numCol; startCol++) {
                for (int endCol = startCol; endCol < numCol; endCol++) {
                    int nums[numRow + 1], aux[numRow + 1];
                    nums[0] = 0;
                    for (int i = 0; i < numRow; i++) {
                        nums[i + 1] = matrix[i][endCol] - (startCol > 0 ? matrix[i][startCol - 1] : 0);
                    }
                    sort(nums, aux, 0, length);
                    if (ans == k) {
                        return k;
                    }
                }
            }
        }
        return ans;
    }

    void sort(int *nums, int *aux, int start, int end) {    // 归并
        if (start >= end || ans == limit) {
            return;
        }
        int mid = (start + end) >> 1;
        sort(nums, aux, start, mid);
        sort(nums, aux, mid + 1, end);

        // 查找最大差值(不超过k)
        for (int right = mid + 1, left = start; right <= end; right++) {
            while (left <= mid && nums[right] - nums[left] > limit) {
                left++;
            }
            if (left <= mid && nums[right] - nums[left] > ans) {
                ans = nums[right] - nums[left];
                if (ans == limit) {
                    return;
                }
            }
        }

        if (start == 0 && end == length) {
            return;
        }
        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
        }
        int left = start, right = mid + 1, idx = start;
        while (true) {
            if (left == mid + 1) {
                while (idx <= end) {
                    nums[idx++] = aux[right++];
                }
                return;
            }
            if (right == end + 1) {
                while (idx <= end) {
                    nums[idx++] = aux[left++];
                }
                return;
            }
            if (aux[left] <= aux[right]) {
                nums[idx++] = aux[left++];
            } else {
                nums[idx++] = aux[right++];
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
