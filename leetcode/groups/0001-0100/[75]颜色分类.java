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
    public void sortColors(int[] nums) {    // 三项排序(从左往右)(计数法, idx从右往左见cpp)
        // left, right分别指向左右两端的1, 由idx遍历数组并与两端点交换元素
        int left = 0, right = nums.length - 1, idx = 0, temp;
        while (idx <= right) {
            if (nums[idx] == 0) {
                temp = nums[left];
                nums[left++] = nums[idx];
                nums[idx++] = temp;
            }
            else if (nums[idx] == 2) {
                temp = nums[right];
                nums[right--] = nums[idx];
                nums[idx] = temp;
            }
            else {
                idx++;
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public void sortColors(int[] nums) {    // 选择排序(O(n^2))
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            while (j > 0 && nums[j] < nums[j - 1]) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j-- - 1] = temp;
            }
        }
    }
}
