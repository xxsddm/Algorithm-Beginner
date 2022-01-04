# 给你1个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。 
# 
#  如果1个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。 
# 
#  
#  例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。 
#  再例如，[1, 1, 2, 5, 7] 不是等差序列。 
#  
# 
#  数组中的子序列是从数组中删除1些元素（也可能不删除）得到的1个序列。 
# 
#  
#  例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的1个子序列。 
#  
# 
#  题目数据保证答案是1个 32-bit 整数。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [2,4,6,8,10]
# 输出：7
# 解释：所有的等差子序列为：
# [2,4,6]
# [4,6,8]
# [6,8,10]
# [2,4,6,8]
# [4,6,8,10]
# [2,4,6,8,10]
# [2,6,10]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [7,7,7,7,7]
# 输出：16
# 解释：数组中的任意子序列都是等差子序列。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 1000 
#  -231 <= nums[i] <= 231 - 1 
#  
#  Related Topics 数组 动态规划 
#  👍 124 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        container = [{} for _ in nums]    # 各索引对应一个哈希表, 保存该索引为第2个点, 以相应差值所能构建等差数列的数量
        count = 0
        for first in range(len(nums) - 1):                # first指第1个扫描点, 不是数列中第1个元素
            for second in range(first + 1, len(nums)):    # second指第2个扫描点, 不是数列中第1个元素
                diff = nums[second] - nums[first]
                if diff not in container[first]:
                    container[first][diff] = 0
                if diff not in container[second]:
                    container[second][diff] = 0    # 第1次扫过该点, 其作为second, 说明作为第2个点无相应组合, 即0

                # 当first没有配对的second, 将永远为0, 若被second扫过, 说明其若作为第2个点而非初始点, 可按其相应值增加总数
                count += container[first][diff]

                # 若以first为中点, 有num种组合, 那么以second为第2个点会额外增加num+1种(多1个初始点为first的情况)
                container[second][diff] += container[first][diff] + 1
        return count
# leetcode submit region end(Prohibit modification and deletion)
