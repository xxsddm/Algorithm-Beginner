//给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。 
//
// 实现 SummaryRanges 类： 
//
// 
// 
// 
// SummaryRanges() 使用一个空数据流初始化对象。 
// void addNum(int val) 向数据流中加入整数 val 。 
// int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", 
//"addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//[[], [1], [], [3], [], [7], [], [2], [], [6], []]
//输出：
//[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
// null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//解释：
//SummaryRanges summaryRanges = new SummaryRanges();
//summaryRanges.addNum(1);      // arr = [1]
//summaryRanges.getIntervals(); // 返回 [[1, 1]]
//summaryRanges.addNum(3);      // arr = [1, 3]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
//summaryRanges.addNum(7);      // arr = [1, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
//summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
//summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= val <= 10⁴ 
// 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次 
// 
// 
// 
//
// 
//
// 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办? 
// Related Topics 设计 二分查找 有序集合 👍 87 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SummaryRanges {
public:
    // 数字区间
    vector<vector<int>> intervals;

    SummaryRanges() {
    }

    void addNum(int val) {
        if (intervals.empty()) {
            intervals.push_back(vector<int> {val, val});
            return;
        }
        // 二分查找返回当前值应该所在的区间索引curIdx
        int curIdx = bisect(val), prevIdx = curIdx - 1, length = (int) intervals.size();
        // 无需操作的情况
        if (curIdx == -1 ||
            prevIdx >= 0 && intervals[prevIdx][0] <= val && val <= intervals[prevIdx][1]) {
            return;
        }
        // 可能扩充区间列表容积
        else if (curIdx == length) {
            if (intervals[prevIdx][1] < val - 1) {
                intervals.push_back(vector<int> {val, val});
            }
            else if (intervals[prevIdx][1] == val - 1) {
                intervals[prevIdx][1] = val;
            }
        }
        else if (prevIdx >= 0 && intervals[prevIdx][1] == val - 1) {
            // 前后合并
            if (intervals[curIdx][0] == val + 1) {
                intervals[prevIdx][1] = intervals[curIdx][1];
                intervals.erase(intervals.begin() + curIdx);
            }
            // 向前合并
            else {
                intervals[prevIdx][1] = val;
            }
        }
        // 向后合并
        else if (intervals[curIdx][0] == val + 1) {
            intervals[curIdx][0] = val;
        }
        // 插入新区间
        else {
            intervals.push_back(vector<int> {0, 0});
            for (int i = length; i > curIdx; i--) {
                intervals[i] = intervals[i - 1];
            }
            intervals[curIdx] = vector<int> {val, val};
        }
    }

    vector<vector<int>> getIntervals() {
        return intervals;
    }

    int bisect(int num) {
        int left = 0, right = (int) intervals.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (num > intervals[mid][0]) {
                left = mid + 1;
            }
            else if (num < intervals[mid][0]) {
                right = mid - 1;
            }
            else {
                return -1;
            }
        }
        return left;
    }
};

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges* obj = new SummaryRanges();
 * obj->addNum(val);
 * vector<vector<int>> param_2 = obj->getIntervals();
 */
//leetcode submit region end(Prohibit modification and deletion)
