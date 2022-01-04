//给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。 
//
// 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。 
//
// 
//
// 示例 1： 
//
// 输入：mat = [[1,3,11],[2,4,6]], k = 5
//输出：7
//解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
//[1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。  
//
// 示例 2： 
//
// 输入：mat = [[1,3,11],[2,4,6]], k = 9
//输出：17
// 
//
// 示例 3： 
//
// 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
//输出：9
//解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
//[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。 
// 
//
// 示例 4： 
//
// 输入：mat = [[1,1,10],[2,2,9]], k = 7
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == mat.length 
// n == mat.length[i] 
// 1 <= m, n <= 40 
// 1 <= k <= min(200, n ^ m) 
// 1 <= mat[i][j] <= 5000 
// mat[i] 是一个非递减数组 
// 
// Related Topics 数组 二分查找 矩阵 堆（优先队列） 👍 75 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int limit, numRow, numCol;
    vector<vector<int>> nums;

    int kthSmallest(vector<vector<int>>& mat, int k) {  // 二分+DFS
        nums = mat;
        limit = k, numRow = (int) mat.size(), numCol = (int) mat[0].size();
        int left = 0, right = 0;
        for (int i = 0; i < numRow; i++) {
            left += mat[i][0];
            right += mat[i][numCol - 1];
        }
        int cur = left;
        while (left <= right) {
            int mid = (left + right) >> 1, count = 1;
            dfs(0, cur, mid, count);
            if (count >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    void dfs(int idx, int cumsum, int target, int &count) {
        if (idx == numRow) {
            return;
        }
        dfs(idx + 1, cumsum, target, count);
        for (int i = 1; i < numCol && count <= limit; i++) {
            cumsum += nums[idx][i] - nums[idx][i - 1];
            if (cumsum > target) {
                return;
            }
            dfs(idx + 1, cumsum, target, ++count);
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
