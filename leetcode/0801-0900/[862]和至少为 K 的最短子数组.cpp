//返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。 
//
// 如果没有和至少为 K 的非空子数组，返回 -1 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：A = [1], K = 1
//输出：1
// 
//
// 示例 2： 
//
// 输入：A = [1,2], K = 4
//输出：-1
// 
//
// 示例 3： 
//
// 输入：A = [2,-1,2], K = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 50000 
// -10 ^ 5 <= A[i] <= 10 ^ 5 
// 1 <= K <= 10 ^ 9 
// 
// Related Topics 队列 数组 二分查找 前缀和 滑动窗口 单调队列 堆（优先队列） 👍 318 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {    // k为正,可考虑单调栈(可归并解决k<0的扩展问题,见java)
        int length = (int) nums.size(), ans = length + 1;
        long sum = 0;
        vector<long> cumsum(length + 1);
        deque<int> container;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            cumsum[i + 1] = sum;
        }
        for (int i = 0; i < length + 1; i++) {
            long temp = cumsum[i];
            // 当前元素可以与双链表首端元素组成满足条件的子数组时
            while (!container.empty() && temp >= cumsum[container.front()] + k) {
                ans = min(ans, i - container.front());
                container.pop_front();
            }
            // 当前元素不大于前面元素,则优先选择当前元素作为子序列首端使其更短且和达到k
            while (!container.empty() && temp <= cumsum[container.back()]) {
                container.pop_back();
            }
            container.push_back(i);
        }
        return ans == length + 1 ? -1 : ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
