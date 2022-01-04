# 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 star
# ti 到 endi 的 闭区间 。 
# 
#  如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。 
# 
#  已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了
# 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
# 输出：true
# 解释：2 到 5 的每个整数都被覆盖了：
# - 2 被第一个区间覆盖。
# - 3 和 4 被第二个区间覆盖。
# - 5 被第三个区间覆盖。
#  
# 
#  示例 2： 
# 
#  
# 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
# 输出：false
# 解释：21 没有被任何一个区间覆盖。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= ranges.length <= 50 
#  1 <= starti <= endi <= 50 
#  1 <= left <= right <= 50 
#  
#  Related Topics 数组 哈希表 前缀和 
#  👍 21 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:    # 烂就烂在要排序
        ranges.sort(key=lambda x: x[0])  # 居然默认未排序, 看题目还以为排好了
        if left < ranges[0][0]:
            return False
        point = 0
        while point < len(ranges):  # 找左端点所在区间
            if ranges[point][0] <= left <= ranges[point][1]:
                break
            point += 1
        if point == len(ranges):
            return False
        rightlimit = ranges[point][1]
        while point < len(ranges):  # 找右端点所在区间, 并判断是否可以和左端点区间相连
            if rightlimit + 1 < ranges[point][0]:   # 无法延伸则结束
                return False
            elif ranges[point][0] <= right <= ranges[point][1]:
                return True
            else:
                rightlimit = max(rightlimit, ranges[point][1])  # 与左区间合并
            point += 1
        return False
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:
        # 暴力枚举, 很烂的办法, 但是一行解决就很舒服
        return all(any(limit[0] <= element <= limit[1] for limit in ranges) for element in range(left, right + 1))
