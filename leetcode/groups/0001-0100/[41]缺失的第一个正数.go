//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1187 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
func firstMissingPositive(nums []int) int {
    limit := len(nums)
    ans , idx := limit + 1, 0
    for idx, value := range nums {
        if value >= limit + 1 || value <= 0 {
            nums[idx] = limit + 1
        }
    }

    // 用相应索引对应数字表示该数字是否出现(出现的数字应在 数字-1 的索引位置)
    for idx < limit {
        numsidx := nums[idx]
        if numsidx == limit + 1 || idx == numsidx - 1 {
            idx++
        } else if numsidx != nums[numsidx - 1] {
            nums[idx] = nums[numsidx - 1]
            nums[numsidx - 1] = numsidx
        } else {
            idx++
            nums[idx] = limit + 1
        }
    }

    for i := 0; i < limit ; i++ {
        if nums[i] == limit + 1 {
            ans = i + 1
            break
        }
    }

    return ans
}

//leetcode submit region end(Prohibit modification and deletion)
