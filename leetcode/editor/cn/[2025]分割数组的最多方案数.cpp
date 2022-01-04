//给你一个下标从 0 开始且长度为 n 的整数数组 nums 。分割 数组 nums 的方案数定义为符合以下两个条件的 pivot 数目： 
//
// 
// 1 <= pivot < n 
// nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + 
//... + nums[n - 1] 
// 
//
// 同时给你一个整数 k 。你可以将 nums 中 一个 元素变为 k 或 不改变 数组。 
//
// 请你返回在 至多 改变一个元素的前提下，最多 有多少种方法 分割 nums 使得上述两个条件都满足。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,-1,2], k = 3
//输出：1
//解释：一个最优的方案是将 nums[0] 改为 k 。数组变为 [3,-1,2] 。
//有一种方法分割数组：
//- pivot = 2 ，我们有分割 [3,-1 | 2]：3 + -1 == 2 。
// 
//
// 示例 2： 
//
// 输入：nums = [0,0,0], k = 1
//输出：2
//解释：一个最优的方案是不改动数组。
//有两种方法分割数组：
//- pivot = 1 ，我们有分割 [0 | 0,0]：0 == 0 + 0 。
//- pivot = 2 ，我们有分割 [0,0 | 0]: 0 + 0 == 0 。
// 
//
// 示例 3： 
//
// 输入：nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
//输出：4
//解释：一个最优的方案是将 nums[2] 改为 k 。数组变为 [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14] 。
//
//有四种方法分割数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 10⁵ 
// -10⁵ <= k, nums[i] <= 10⁵ 
// 
// Related Topics 数组 哈希表 计数 枚举 前缀和 👍 14 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int waysToPartition(vector<int>& nums, int k) {
        // i -> 修改当前i元素为k的方案数
        vector<int> counter(nums.size());
        // 左部分和,右部分和
        long leftSum = 0, rightSum = 0;
        int ans = 0;
        for (int num: nums) {
            rightSum += num;
        }

        // 左部分可行的变化幅度和相应位置
        unordered_map<long, vector<int>> changeLeft;
        // 划入左部分前,该部分归属右部分时,右部分所需的变化幅度和数量
        unordered_map<long, int> changeRight;

        for (int i = 0; i < nums.size(); i++) { // 修改索引i
            if (i > 0) {
                changeRight[leftSum - rightSum]++;
            }
            int num = nums[i];
            // 修改当前num为k,则变化delta=k-num
            long delta = k - num;
            counter[i] += changeRight[delta];
            if (i == nums.size() - 1) {
                break;
            }
            leftSum += num;
            rightSum -= num;
            if (leftSum == rightSum) {
                ans++;
            }
            if (changeLeft.find(delta) != changeLeft.end()) {
                changeLeft[delta].push_back(i);
            }
            else {
                changeLeft[delta] = vector<int>{i};
            }
            // 当前左侧所需变化幅度和可行的数量
            if (changeLeft.find(rightSum - leftSum) != changeLeft.end()) {
                for (int idx: changeLeft[rightSum - leftSum]) {
                    counter[idx]++;
                }
            }
        }

        for (int count: counter) {
            ans = max(ans, count);
        }

        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
