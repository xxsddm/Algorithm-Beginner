# 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。 
# 
#  对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。 
# 
#  返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。 
# 
#  该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
# 满足 (i, j) 是图的一条有向边。 
# 
#  
# 
#  
#  
#  示例 1： 
# 
#  
# 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
# 输出：[2,4,5,6]
# 解释：示意图如上。
#  
# 
#  示例 2： 
# 
#  
# 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
# 输出：[4]
#  
# 
#  
# 
#  提示： 
# 
#  
#  n == graph.length 
#  1 <= n <= 104 
#  0 <= graph[i].length <= n 
#  graph[i] 按严格递增顺序排列。 
#  图中可能包含自环。 
#  图中边的数目在范围 [1, 4 * 104] 内。 
#  
#  
#  
#  Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
#  👍 147 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        visited = [0] * len(graph)
        container = []

        def ensure(index):  # 0: 未访问; 1: 已访问但不确认是否在循环; 2: 最终确认
            if visited[index] > 0:
                return visited[index] == 2  # 1状态节点, 再次被访问说明存在循环; 2状态节点, 已被确认
            visited[index] = 1
            for node in graph[index]:
                if not ensure(node):  # 子节点无法ensure则节点无法确认安全
                    return False
            visited[index] = 2  # 通过循环测验, 确认index节点(重复被访问的可确认节点, 首次作为子节点被访问调用ensure就已确认)
            return True

        for i in range(len(graph)):
            if visited[i] == 0:  # 避免重复ensure
                ensure(i)
            if visited[i] == 2:
                container.append(i)

        return container
# leetcode submit region end(Prohibit modification and deletion)
