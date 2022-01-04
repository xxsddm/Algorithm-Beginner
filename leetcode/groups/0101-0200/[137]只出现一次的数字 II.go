//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 数组 👍 711 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
func singleNumber(nums []int) int {   // 两个二进制数表示三进制数(但不考虑进位 0->1->2->0)
    once, twice := 0, 0    // once: 1表示取1次, twice: 1表示取2次
    for _, num := range nums {
        // ~twice&num: num中twice为0的部分(即按位非)与num相同, 其余为0, 确保之前出现0或1次
        //      且使 twice的0 -> once的1, twice的1 -> once的0 (0->1, 2->0)
        // ^once: 出现过0或1次的, 需要进一步要求只出现过0次, 即once取0, 异或
        once = ^ twice & num ^ once

        // ~once&num: num中once为0的部分(即按位非)与num相同, 其余为0, 确保之前出现0次
        //      且使 once的1 -> twice的1 (1->2)
        // ^twice: 出现过1次的, 若num相同位置也为1, 需要进一步转换为twice的1, 异或合并两个部分
        twice = ^ once & num ^ twice
    }
    return once
}

//leetcode submit region end(Prohibit modification and deletion)
