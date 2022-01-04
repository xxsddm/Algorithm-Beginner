//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 说明： 
//
// 为什么返回数值是整数，但输出的答案是数组呢？ 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,2,2,3]
//输出：5, nums = [1,1,2,2,3]
//解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,1,2,3,3]
//输出：7, nums = [0,0,1,1,2,3,3]
//解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的
//元素。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 已按升序排列 
// 
// Related Topics 数组 双指针 👍 562 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int slow = 2, fast = 2;     // slow: 当前待修改元素索引; fast: 扫描数组的指针
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {        // 每个重复子区间最多保留k个数(本题k=2)
    public int removeDuplicates(int[] nums) {
        if (nums.length <= k) {
            return nums.length;
        }
        // 可以保证[0: slow-1]部分符合要求, 用于填充前面位置的数, 会被后面符合要求的数覆盖
        int slow = k, fast = k;     // slow: 当前待修改元素索引; fast: 扫描数组的指针
        while (fast < nums.length) {
            if (nums[fast] != nums[slow - k]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }
}
