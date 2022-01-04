//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
// Related Topics 数组 哈希表 计数 排序 👍 501 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        int candidate1 = 32130, candidate2 = 0xff15a;
        int count1 = 0, count2 = 0;
        vector<int> ans;
        for (int num: nums) {
            if (num == candidate1 && count1 > 0) {
                count1++;
            }
            else if (num == candidate2 && count2 > 0) {
                count2++;
            }
            else if (count1 == 0) {
                candidate1 = num;
                count1++;
            }
            else if (count2 == 0) {
                candidate2 = num;
                count2++;
            }
            else {
                count1--;
                count2--;
            }
        }
        for (int num: nums) {
            if (num == candidate1) {
                count1++;
            }
            else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > (int) nums.size() / 3) {
            ans.push_back(candidate1);
        }
        if (count2 > (int) nums.size() / 3) {
            ans.push_back(candidate2);
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
