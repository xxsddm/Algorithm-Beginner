//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 450 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] container = new int[n][n];
        int temp = 1;
        for (int i = 0; i < n >> 1; i++){
            for (int up = i; up < n - i - 1; up++) {
                container[i][up] = temp++;
            }
            for (int right = i; right < n - i - 1; right++) {
                container[right][n - i - 1] = temp++;
            }
            for (int down = n - i - 1; down > i; down--) {
                container[n - i - 1][down] = temp++;
            }
            for (int left = n - i - 1; left > i; left--) {
                container[left][i] = temp++;
            }
        }
        if (n % 2 == 1) {
            container[n >> 1][n >> 1] = n * n;
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
