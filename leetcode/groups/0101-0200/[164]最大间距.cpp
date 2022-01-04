//给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。 
//
// 如果数组元素个数小于 2，则返回 0。 
//
// 示例 1: 
//
// 输入: [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。 
//
// 示例 2: 
//
// 输入: [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。 
//
// 说明: 
//
// 
// 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。 
// 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。 
// 
// Related Topics 数组 桶排序 基数排序 排序 👍 407 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maximumGap(vector<int>& nums) {
        int length = nums.size();
        if (length <= 1) {
            return 0;
        }
        int maxNum = 0, maxgap = 0;
        unsigned int divisor = 1;
        vector<int> cache1(length, 0);
        for (int i = 0; i < length; i++) {
            maxNum = max(maxNum, nums[i]);
            cache1[i] = nums[i];
        }
        while (maxNum >= divisor) {
            vector<int> counter(11, 0);
            vector<int> cache2(length, 0);
            for (int num: nums) {
                // 计算索引
                counter[(num / divisor) % 10 + 1]++;
            }
            for (int i = 1; i < 9; i++) {
                counter[i + 1] += counter[i];
            }
            for (int num: cache1) {
                cache2[counter[(num / divisor) % 10]++] = num;
            }
            cache1 = cache2;
            divisor *= 10;
        }
        for (int i = 0; i < length; i++) {
            nums[i] = cache1[i];
        }
        for (int i = 1; i < length; i++) {
            maxgap = max(maxgap, nums[i] - nums[i - 1]);
        }
        return maxgap;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
