# 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
# 
#  给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
# 
#  返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
# 
#  
# 
#  
#  
# 
#  示例 1： 
# 
#  
# 输入：graph = [[1,2,3],[0],[0],[0]]
# 输出：4
# 解释：一种可能的路径为 [1,0,2,0,3] 
# 
#  示例 2： 
# 
#  
# 
#  
# 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
# 输出：4
# 解释：一种可能的路径为 [0,1,4,2,3]
#  
# 
#  
# 
#  提示： 
# 
#  
#  n == graph.length 
#  1 <= n <= 12 
#  0 <= graph[i].length < n 
#  graph[i] 不包含 i 
#  如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
#  输入的图总是连通图 
#  
#  Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
#  👍 141 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        length = len(graph)
        container = deque((i, 1 << i, 0) for i in range(length))
        node_visited = {(i, 1 << i) for i in range(length)}    # 当前节点位置, 已访问节点bool数组

        while container:
            prev, visited, distance = container.popleft()    # 前节点索引, 前节点路径已访问节点, 前街店路径距离
            # 搜索相邻节点
            for next in graph[prev]:
                visitednext = visited | (1 << next)   # 二进制已访问节点(节点索引对应位置1或0)
                if visitednext == (1 << length) - 1:  # 若所有节点均已访问
                    return distance + 1
                if (next, visitednext) not in node_visited:    # 若已经存在, 则说明在较短距离内已经有其他路径, 无需添加本轮的较长路径
                    container.append((next, visitednext, distance + 1))    # 移动到后一节点, 则距离+1
                    node_visited.add((next, visitednext))

        return 0    # 默认路径存在, 考虑graph为空的情况默认返回0

# leetcode submit region end(Prohibit modification and deletion)
