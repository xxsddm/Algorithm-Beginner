//我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。 
//
// （这里，平面上两点之间的距离是欧几里德距离。） 
//
// 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。 
//
// 
//
// 示例 1： 
//
// 输入：points = [[1,3],[-2,2]], K = 1
//输出：[[-2,2]]
//解释： 
//(1, 3) 和原点之间的距离为 sqrt(10)，
//(-2, 2) 和原点之间的距离为 sqrt(8)，
//由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
//我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
// 
//
// 示例 2： 
//
// 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
//输出：[[3,3],[-2,4]]
//（答案 [[-2,4],[3,3]] 也会被接受。）
// 
//
// 
//
// 提示： 
//
// 
// 1 <= K <= points.length <= 10000 
// -10000 < points[i][0] < 10000 
// -10000 < points[i][1] < 10000 
// 
// Related Topics 几何 数组 数学 分治 快速选择 排序 堆（优先队列） 👍 276 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        int idx = 0;
        vector<vector<int>> ans;
        // 维护一个大根堆,容量超过k时移出最大距离点
        priority_queue<pair<int, int>> container;
        for (int i = 0; i < k; i++) {
            pair<int, int> point(points[i][0] * points[i][0] + points[i][1] * points[i][1], i);
            container.push(point);
        }
        for (int i = k; i < points.size(); i++) {
            pair<int, int> point(points[i][0] * points[i][0] + points[i][1] * points[i][1], i);
            if (point.first < container.top().first) {
                container.pop();
                container.push(point);
            }
        }
        while (!container.empty()) {
            ans.push_back(points[container.top().second]);
            container.pop();
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
