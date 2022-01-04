//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 👍 988 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    void sortColors(vector<int>& nums) {    // 三项排序(从右往左)(选择排序, idx从左往右见java)
        int left = 0, right = nums.size() - 1, idx = right, temp;
        while (idx >= left) {
            if (nums[idx] == 2) {
                temp = nums[idx];
                nums[idx--] = nums[right];
                nums[right--] = temp;
            }
            else if (nums[idx] == 0) {
                temp = nums[idx];
                nums[idx] = nums[left];
                nums[left++] = temp;
            }
            else {
                idx--;
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
public:
    void sortColors(vector<int>& nums) {    // 计数
        int num0 = 0, num1 = 0, num2 = 0;
        for (int num: nums) {
            if (num == 0) {
                num0++;
            }
            else if (num == 1) {
                num1++;
            }
            else {
                num2++;
            }
        }
        for (int i = 0; i < num0; i++) {
            nums[i] = 0;
        }
        for (int i = num0; i < num0 + num1; i++) {
            nums[i] = 1;
        }
        for (int i = num0 + num1; i < nums.size(); i++) {
            nums[i] = 2;
        }
    }
};
