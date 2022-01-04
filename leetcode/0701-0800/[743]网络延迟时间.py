# 有 n 个网络节点，标记为 1 到 n。 
# 
#  给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
# i 是一个信号从源节点传递到目标节点的时间。 
# 
#  现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  
# 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
# 输出：2
#  
# 
#  示例 2： 
# 
#  
# 输入：times = [[1,2,1]], n = 2, k = 1
# 输出：1
#  
# 
#  示例 3： 
# 
#  
# 输入：times = [[1,2,1]], n = 2, k = 2
# 输出：-1
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= k <= n <= 100 
#  1 <= times.length <= 6000 
#  times[i].length == 3 
#  1 <= ui, vi <= n 
#  ui != vi 
#  0 <= wi <= 100 
#  所有 (ui, vi) 对都 互不相同（即，不含重复边） 
#  
#  Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 
#  👍 297 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import math


class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:  # Dijkstra算法
        timematrix = [[-1] * n for _ in range(n)]   # 记录 行节点 -> 列节点 所需时间
        for time in times:
            timematrix[time[0] - 1][time[1] - 1] = time[2]    # -1 使序数变为索引
        visited = [False] * n
        timelist = [t for t in timematrix[k - 1]]  # 初始化从k节点出发连接到其他点的时间
        timelist[k - 1] = 0
        visited[k - 1] = True   # 省略从k节点自身出发的情况
        maxtime = minindex = 0  # 更新完后, 从k节点出发到达目标点的最长时间即所需时间

        for count in range(n - 1):  # 记录连接其他节点(n-1个)再寻找最优路径的次数
            mintime = math.inf

            for end in range(n):    # 找到 k -> end 所需时间最少且尚未被访问的点index
                if not visited[end] and timelist[end] != -1 and timelist[end] < mintime:  # 跳过找过的和无法直接连接的
                    mintime = timelist[end]
                    minindex = end
            visited[minindex] = True    # 即移出小根堆

            for end in range(n):    # 所需时间: minindex -> end
                if visited[end]:  # 已访问的点不可能被更新, 可跳过
                    continue
                temp = timematrix[minindex][end]
                if temp != -1:
                    # 即放入小根堆, 或更新小根堆中的值
                    if timelist[end] == -1:
                        timelist[end] = timelist[minindex] + temp  # 原先的k节点无法直接连到end点, 所需时间为两段时间的和
                    elif timelist[minindex] + temp < timelist[end]:
                        timelist[end] = timelist[minindex] + temp   # 不可能更新之前已被访问的点, 因为这里取不到 小于

        for time in timelist:
            if time == -1:  # 存在无法连接到的点
                return -1
            elif time > maxtime:  # 更新最大时间
                maxtime = time

        return maxtime
# leetcode submit region end(Prohibit modification and deletion)
