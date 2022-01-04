# 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 3
# 输出：[[1,2,3],[8,9,4],[7,6,5]]
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 1
# 输出：[[1]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 20 
#  
#  Related Topics 数组 矩阵 模拟 
#  👍 450 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:    # 顺时针旋转
        container = [[None] * n for _ in range(n)]  # [[None] * n] * n涉及浅拷贝问题, 用于二维数组会导致改变一个元素就整列改变
        temp = 1    # 起始数字
        for i in range(n // 2):  # 先填充完整四边形区域
            for up in range(i, n - i - 1):   # 上, 右上角属于 右
                container[i][up] = temp
                temp += 1
            for right in range(i, n - i - 1):   # 右, 右下角属于 下
                container[right][n - i - 1] = temp
                temp += 1
            for down in range(n - i - 1, i, -1):    # 下, 左下角属于 左
                container[n - i - 1][down] = temp
                temp += 1
            for left in range(n - i - 1, i, -1):    # 左, 左上角属于 上
                container[left][i] = temp
                temp += 1
        if n % 2 == 1:   # 检查中点
            container[n // 2][n // 2] = n ** 2
        return container

# leetcode submit region end(Prohibit modification and deletion)
