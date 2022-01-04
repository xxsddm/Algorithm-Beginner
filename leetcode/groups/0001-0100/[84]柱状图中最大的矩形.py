# 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
# 
#  求在该柱状图中，能够勾勒出来的矩形的最大面积。 
# 
#  
# 
#  示例 1: 
# 
#  
# 
#  
# 输入：heights = [2,1,5,6,2,3]
# 输出：10
# 解释：最大的矩形为图中红色区域，面积为 10
#  
# 
#  示例 2： 
# 
#  
# 
#  
# 输入： heights = [2,4]
# 输出： 4 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= heights.length <=10⁵ 
#  0 <= heights[i] <= 10⁴ 
#  
#  Related Topics 栈 数组 单调栈 👍 1509 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:      # 单调栈
        length, ans = len(heights), 0
        stack = []
        # 第i个数字左, 右侧最近的相对heights[i]更低的矩形索引
        # 不低的中间部分可以作为heights[i]宽度一部分

        # 左端较小值索引
        dp = [[0] * 2 for _ in range(length)]
        for i in range(length - 1, - 1, - 1):
            leftheight = heights[i]
            while stack and leftheight < heights[stack[- 1]]:
                dp[stack.pop()][0] = i
            stack.append(i)
        while stack:
            dp[stack.pop()][0] = - 1

        # 右端较小值索引
        for i in range(length):
            rightheight = heights[i]
            while stack and rightheight < heights[stack[- 1]]:
                dp[stack.pop()][1] = i
            stack.append(i)
        while stack:
            dp[stack.pop()][1] = length

        for i in range(length):
            ans = max(ans, heights[i] * (dp[i][1] - dp[i][0] - 1))

        return ans

# leetcode submit region end(Prohibit modification and deletion)
