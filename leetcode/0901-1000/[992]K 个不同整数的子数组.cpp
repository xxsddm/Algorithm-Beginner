//给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。 
//
// （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。） 
//
// 返回 A 中好子数组的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：A = [1,2,1,2,3], K = 2
//输出：7
//解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// 示例 2： 
//
// 
//输入：A = [1,2,1,3,4], K = 3
//输出：3
//解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// 
// Related Topics 数组 哈希表 计数 滑动窗口 👍 311 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int subarraysWithKDistinct(vector<int>& nums, int k) {
        int ans = 0, maxnum = 1, minnum = nums.size();
        int left1 = 0, left2 = 0, count1 = 0, count2 = 0;
        for (int num: nums) {
            maxnum = max(maxnum, num);
            minnum = min(minnum, num);
        }
        vector<int> counter1(maxnum - minnum + 1);
        vector<int> counter2(maxnum - minnum + 1);
        // 给定右端数字num, 左端起点left1则包含k种数字, 左端起点left2则包含k-1种数字(若包含种类不足, 则left=0)
        // 则left1~left2-1到num(含)包含k种数字, 共left2-left1种
        for (int num : nums) {
            counter1[num - minnum]++;
            if (counter1[num - minnum] == 1) {
                count1++;
            }
            counter2[num - minnum]++;
            if (counter2[num - minnum] == 1) {
                count2++;
            }
            while (count1 > k) {
                counter1[nums[left1] - minnum]--;
                if (counter1[nums[left1] - minnum] == 0) {
                    count1--;
                }
                left1++;
            }
            while (count2 > k - 1) {
                counter2[nums[left2] - minnum]--;
                if (counter2[nums[left2] - minnum] == 0) {
                    count2--;
                }
                left2++;
            }
            ans += left2 - left1;
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
