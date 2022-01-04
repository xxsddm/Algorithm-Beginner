//给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中
// i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。 
//
// 返回平面上所有回旋镖的数量。 
// 
//
// 示例 1： 
//
// 
//输入：points = [[0,0],[1,0],[2,0]]
//输出：2
//解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：points = [[1,1]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == points.length 
// 1 <= n <= 500 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// 所有点都 互不相同 
// 
// Related Topics 数组 哈希表 数学 👍 157 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        if (points.size() < 3) {
            return 0;
        }
        int count = 0;
        // 枚举中间点
        for (vector<int> point1: points) {
            // 到点1的距离 -> 到点1距离取值相同点的数量
            unordered_map<int, int> counter;
            for (vector<int> point2: points) {
                int diff1 = point1[0] - point2[0], diff2 = point1[1] - point2[1];
                int square = diff1 * diff1 + diff2 * diff2;
                counter[square]++;
            }
            // 到1的距离相同, 则这些点任意有序组合均可以作为后两个点
            for (auto squareNum: counter) {
                count += squareNum.second * (squareNum.second - 1);
            }
        }
        return count;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
