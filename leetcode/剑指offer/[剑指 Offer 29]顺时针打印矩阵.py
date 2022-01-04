# 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
# 
#  
# 
#  示例 1： 
# 
#  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,3,6,9,8,7,4,5]
#  
# 
#  示例 2： 
# 
#  输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
# 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
#  
# 
#  
# 
#  限制： 
# 
#  
#  0 <= matrix.length <= 100 
#  0 <= matrix[i].length <= 100 
#  
# 
#  注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
#  Related Topics 数组 矩阵 模拟 
#  👍 282 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        container = []
        if not matrix:
            return container
        width, height = len(matrix[0]), len(matrix)
        count = 0
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
