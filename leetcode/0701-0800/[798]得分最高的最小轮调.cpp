//给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[
//nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
//
//
// 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0
//[不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
//
//
// 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,1,4,0]
//输出：3
//解释：
//下面列出了每个 k 的得分：
//k = 0,  nums = [2,3,1,4,0],    score 2
//k = 1,  nums = [3,1,4,0,2],    score 3
//k = 2,  nums = [1,4,0,2,3],    score 3
//k = 3,  nums = [4,0,2,3,1],    score 4
//k = 4,  nums = [0,2,3,1,4],    score 3
//所以我们应当选择 k = 3，得分最高。
//
// 示例 2：
//
//
//输入：nums = [1,3,0,2,4]
//输出：0
//解释：
//nums 无论怎么变化总是有 3 分。
//所以我们将选择最小的 k，即 0。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] < nums.length
//
// Related Topics 数组 前缀和 👍 91 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int bestRotation(vector<int>& nums) {
        int length = (int) nums.size(), prev = 0, counter[length], precounter[length * 2];
        memset(counter, 0, sizeof(counter));
        memset(precounter, 0, sizeof(precounter));
        for (int i = 0; i < length; i++) {
            if (nums[i] > i) {
                prev++;
                counter[nums[i] - i]++;
            }
        }
        int maxcount = length - prev, maxnum = -1, ans = -1;    // prev记录前期数字大于索引情况的数量
        for (int i = length - 1, j = 1; i > 0; i--, j++) {
            prev -= counter[j] + precounter[j - 1];
            if (nums[i]) {
                prev++;
                precounter[nums[i] + j - 1]++;  // nums[i]轮后删除
            }
            if (length - prev >= maxnum) {
                maxnum = length - prev;
                ans = i;
            }
        }
        return maxcount >= maxnum ? 0 : ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int bestRotation(vector<int>& nums) {   // 优化到一个数组
        int length = (int) nums.size(), prev = 0, counter[length];
        memset(counter, 0, sizeof(counter));
        for (int i = 0; i < length; i++) {
            if (nums[i] > i) {
                prev++;
                counter[nums[i] - i]++;
            }
        }
        int maxcount = length - prev, maxnum = -1, ans = -1;
        for (int i = length - 1, j = 1; i > 0; i--, j++) {
            prev -= counter[j];
            if (nums[i]) {
                prev++;
                if (nums[i] + j < length) {
                    counter[nums[i] + j]++;
                }
            }
            if (length - prev >= maxnum) {
                maxnum = length - prev;
                ans = i;
            }
        }
        return maxcount >= maxnum ? 0 : ans;
    }
};
