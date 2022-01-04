# 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。 
# 
#  
# 
#  示例 1: 
# 
#  
# 
#  
# 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
# 输出: 4
# 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
#  
# 
#  示例 2: 
# 
#  
# 
#  
# 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
# 输出: 10
#  
# 
#  
# 
#  提示: 
# 
#  
#  m == heightMap.length 
#  n == heightMap[i].length 
#  1 <= m, n <= 200 
#  0 <= heightMap[i][j] <= 2 * 10⁴ 
#  
# 
#  
#  Related Topics 广度优先搜索 数组 矩阵 堆（优先队列） 👍 373 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def trapRainWater(self, heightMap: List[List[int]]) -> int:
        numRow, numCol = len(heightMap), len(heightMap[0])
        if numRow <= 2 or numCol <= 2:
            return 0
        ans, rest = 0, (numRow - 2) * (numCol - 2)
        dirs = [1, 0, -1, 0, 1]
        visited = [[False] * numCol for _ in range(numRow)]
        container = []
        for i in range(1, numCol - 1):
            container.append([heightMap[0][i], 0, i])
            container.append([heightMap[numRow - 1][i], numRow - 1, i])
        for i in range(1, numRow - 1):
            container.append([heightMap[i][0], i, 0])
            container.append([heightMap[i][numCol - 1], i, numCol - 1])
        heapq.heapify(container)
        while rest > 0:
            height, row, col = heapq.heappop(container)
            for i in range(4):
                nextRow, nextCol = row + dirs[i], col + dirs[i + 1]
                if 0 < nextRow < numRow - 1 and 0 < nextCol < numCol - 1 \
                        and not visited[nextRow][nextCol]:
                    curHeight = heightMap[nextRow][nextCol]
                    nextHeight = max(height, curHeight)
                    heapq.heappush(container, [nextHeight, nextRow, nextCol])
                    visited[nextRow][nextCol] = True
                    rest -= 1
                    if nextHeight == height:
                        ans += height - curHeight
        return ans

# leetcode submit region end(Prohibit modification and deletion)
from queue import PriorityQueue


class Solution:
    def trapRainWater(self, heightMap: List[List[int]]) -> int:
        length, width = len(heightMap[0]), len(heightMap)
        ans = 0
        dirs = [[1, 0], [- 1, 0], [0, 1], [0, - 1]]
        used = [[False] * length for _ in range(width)]
        pq = PriorityQueue()
        for i in range(length - 1):
            pq.put((heightMap[0][i], 0, i))
            used[0][i] = True
        for i in range(width - 1):
            pq.put((heightMap[i][length - 1], i, length - 1))
            used[i][length - 1] = True
        for i in range(1, length):
            pq.put((heightMap[width - 1][i], width - 1, i))
            used[width - 1][i] = True
        for i in range(1, width):
            pq.put((heightMap[i][0], i, 0))
            used[i][0] = True
        while not pq.empty():
            minheight, x, y = pq.get()
            for dx, dy in dirs:
                row = x + dx
                column = y + dy
                if 0 <= row < width \
                        and 0 <= column < length \
                        and not used[row][column]:
                    if minheight > heightMap[row][column]:
                        ans += minheight - heightMap[row][column]
                    pq.put((max(minheight, heightMap[row][column]), row, column))
                    used[row][column] = True
        return ans
