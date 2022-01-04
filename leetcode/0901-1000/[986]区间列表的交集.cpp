//给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 
//secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。 
//
// 返回这 两个区间列表的交集 。 
//
// 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。 
//
// 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。 
//
// 
//
// 示例 1： 
//
// 
//输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,
//24],[25,26]]
//输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// 
//
// 示例 2： 
//
// 
//输入：firstList = [[1,3],[5,9]], secondList = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：firstList = [], secondList = [[4,8],[10,12]]
//输出：[]
// 
//
// 示例 4： 
//
// 
//输入：firstList = [[1,7]], secondList = [[3,10]]
//输出：[[3,7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= firstList.length, secondList.length <= 1000 
// firstList.length + secondList.length >= 1 
// 0 <= starti < endi <= 10⁹ 
// endi < starti+1 
// 0 <= startj < endj <= 10⁹ 
// endj < startj+1 
// 
// Related Topics 数组 双指针 👍 191 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> intervalIntersection(vector<vector<int>>& firstList, vector<vector<int>>& secondList) {
        int idx1 = 0, idx2 = 0;
        int length1 = (int) firstList.size(), length2 = (int) secondList.size();
        vector<vector<int>> ans;
        while (idx1 < length1 && idx2 < length2) {
            // 根据指针找到两个列表当前待匹配区间
            auto block1 = firstList[idx1], block2 = secondList[idx2];
            // 不可能有交点则根据边界情况判断哪个指针移动
            if (block1[1] < block2[0]) {
                idx1++;
                continue;
            }
            if (block2[1] < block1[0]) {
                idx2++;
                continue;
            }

            // 区间下界较大值确定交集下界
            int left = max(block1[0], block2[0]), right;
            // 区间上界较小值确定交集上界,但相交后也需根据区间上界的对比判断那个指针移动,可一起判断
            if (block1[1] <= block2[1]) {
                right = block1[1];
                idx1++;
            }
            else {
                right = block2[1];
                idx2++;
            }
            ans.push_back(vector<int> {left, right});
        }

        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
