# 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。 
# 
#  每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示： 
# 
# 
#  
#  lefti 是第 i 座建筑物左边缘的 x 坐标。 
#  righti 是第 i 座建筑物右边缘的 x 坐标。 
#  heighti 是第 i 座建筑物的高度。 
#  
# 
#  天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
# 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。 
# 
#  注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
# 案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...] 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
# 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
# 解释：
# 图 A 显示输入的所有建筑物的位置和高度，
# 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。 
# 
#  示例 2： 
# 
#  
# 输入：buildings = [[0,2,3],[2,5,3]]
# 输出：[[0,3],[5,0]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= buildings.length <= 104 
#  0 <= lefti < righti <= 231 - 1 
#  1 <= heighti <= 231 - 1 
#  buildings 按 lefti 非递减排序 
#  
#  Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列） 
#  👍 431 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        pos_height = []     # position-height
        ans = []
        counter = {0: 1}    # 计算当前高度相应建筑数量, 配合小根堆
        container = [0]     # 高度的小根堆, 加入右端点0, 不受建筑出入影响

        for left, right, height in buildings:
            pos_height.append((left, -height))          # 负数代表建筑左端(即入堆)
            pos_height.append((right, height))

        pos_height.sort(key=lambda x: (x[0], x[1]))     # 保证重合位置先进后出
        for pos, height in pos_height:
            if height < 0:
                heapq.heappush(container, height)       # 新点入堆
                if - height not in counter:
                    counter[-height] = 0
                counter[- height] += 1
            else:
                counter[height] -= 1                    # 堆中无法直接移出相应点, 先计数-1
            while counter[- container[0]] == 0:         # 不可直接出堆, 可能还在堆中, 0一定在堆中
                heapq.heappop(container)                # counter数量为0, 即该高度已经被移出, 但仍在小根堆中, 需出堆
            highest = - container[0]  # 若小根堆非空, 则
            if ans:
                if highest != ans[-1][1]:
                    ans.append([pos, highest])
            else:
                ans.append([pos, highest])
        return ans

# leetcode submit region end(Prohibit modification and deletion)

