//给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,1,3,2,5]
//输出：[3,5]
//解释：[5, 3] 也是有效的答案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1,0]
//输出：[-1,0]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,1]
//输出：[1,0]
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 3 * 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 除两个只出现一次的整数外，nums 中的其他数字都出现两次 
// 
// Related Topics 位运算 数组 👍 467 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        int XOR = 0;
        for (int num: nums) {
            XOR ^= num;
        }
        int back = XOR;
        // 异或和XOR最右侧1表示两个不重复的数最右侧出现不一致的位置(一个是0一个是1)
        XOR = XOR == INT32_MIN ? XOR : XOR & (-XOR);    // 防止溢出(可long)
        int ans1 = 0;
        for (int num: nums) {
            // 这里选择该位置为0的(选1也可以)
            if ((num & XOR) == 0) {
                ans1 ^= num;
            }
        }
        return vector<int> {ans1, back ^ ans1};
    }
};

//leetcode submit region end(Prohibit modification and deletion)
