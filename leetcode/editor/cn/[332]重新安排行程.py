# 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。 
# 
# 
#  所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。 
# 
# 
#  
#  例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。 
#  
# 
#  假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
# 输出：["JFK","MUC","LHR","SFO","SJC"]
#  
# 
#  示例 2： 
# 
#  
# 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL",
# "SFO"]]
# 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
# 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= tickets.length <= 300 
#  tickets[i].length == 2 
#  fromi.length == 3 
#  toi.length == 3 
#  fromi 和 toi 由大写英文字母组成 
#  fromi != toi 
#  
#  Related Topics 深度优先搜索 图 欧拉回路 👍 439 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        tickets_dict = defaultdict(list)
        for item in tickets:
            tickets_dict[item[0]].append(item[1])
        path = ["JFK"]

        def backtrack(start_point):
            if len(path) == len(tickets) + 1:
                return True
            tickets_dict[start_point].sort()
            for _ in tickets_dict[start_point]:
                end_point = tickets_dict[start_point].pop(0)
                path.append(end_point)
                if backtrack(end_point):
                    return True
                path.pop()
                tickets_dict[start_point].append(end_point)

        backtrack("JFK")
        return path

# leetcode submit region end(Prohibit modification and deletion)
