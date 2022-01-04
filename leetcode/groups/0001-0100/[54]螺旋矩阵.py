# 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,3,6,9,8,7,4,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
# 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
#  
# 
#  
# 
#  提示： 
# 
#  
#  m == matrix.length 
#  n == matrix[i].length 
#  1 <= m, n <= 10 
#  -100 <= matrix[i][j] <= 100 
#  
#  Related Topics 数组 矩阵 模拟 
#  👍 836 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        width, height = len(matrix[0]), len(matrix)
        count = 0
        container = []
        while count + 1 <= width // 2 and count + 1 <= height // 2:  # 先填充完整四边形
            for up in range(count, width - count - 1):   # 上, 右上角位置属于 右
                container.append(matrix[count][up])
            for right in range(count, height - count - 1):   # 右, 右下角属于 下
                container.append(matrix[right][width - count - 1])
            for down in range(count, width - count - 1):    # 下, 左下角属于 左
                container.append(matrix[height - count - 1][width - down - 1])
            for left in range(count, height - count - 1):    # 左, 左上角属于 上
                container.append(matrix[height - left - 1][count])
            count += 1
        if height - count * 2 == 1:  # 检查单边形区域
            for i in range(width - 2 * count):
                container.append(matrix[count][count + i])
        elif width - count * 2 == 1:
            for i in range(height - 2 * count):
                container.append(matrix[count + i][count])
        return container
# leetcode submit region end(Prohibit modification and deletion)
