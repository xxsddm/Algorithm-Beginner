# 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。 
# 
#  给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui
#  和 vi 在 nums 中相邻。 
# 
#  题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i]
# , nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。 
# 
#  返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：adjacentPairs = [[2,1],[3,4],[3,2]]
# 输出：[1,2,3,4]
# 解释：数组的所有相邻元素对都在 adjacentPairs 中。
# 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
#  
# 
#  示例 2： 
# 
#  
# 输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
# 输出：[-2,4,1,-3]
# 解释：数组中可能存在负数。
# 另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
#  
# 
#  示例 3： 
# 
#  
# 输入：adjacentPairs = [[100000,-100000]]
# 输出：[100000,-100000]
#  
# 
#  
# 
#  提示： 
# 
#  
#  nums.length == n 
#  adjacentPairs.length == n - 1 
#  adjacentPairs[i].length == 2 
#  2 <= n <= 105 
#  -105 <= nums[i], ui, vi <= 105 
#  题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums 
#  
#  Related Topics 数组 哈希表 
#  👍 34 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        n = len(adjacentPairs)
        container = {}  # 存放各元素相邻数字(key: 数组元素, value: 数组元素相邻元素)
        ans = []
        for pair in adjacentPairs:
            for temp in range(2):
                if pair[temp] not in container:
                    container[pair[temp]] = []
                container[pair[temp]].append(pair[temp == 0])
        for temp in container:
            if len(container[temp]) == 1:
                break
        ans.append(temp)
        temp = container[temp][0]
        ans.append(temp)
        while len(ans) <= n:
            for temp in container[temp]:
                if temp != ans[-2]:
                    ans.append(temp)
                    break
        return ans

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        if len(adjacentPairs) == 1:
            return adjacentPairs[0]
        container = {}  # 存放只出现一次的元素所在pair的索引(value-key)
        indexmap = {}    # 存放有相同元素pair的值和索引, 通过前value-key直接找到下一个value-key
        head = set()
        for i in range(len(adjacentPairs)):
            for j in range(2):
                temp = adjacentPairs[i][j]
                if temp not in container:
                    container[temp] = i
                else:
                    indexmap[(temp, container[temp])] = i  # 具有相同元素pair的索引, 前索引映射到后索引
                    indexmap[(temp, i)] = container[temp]  # 数组元素本身被打乱, 故逆映射也应加入
                    container.pop(temp)
        while container:
            onetimekv = container.popitem()  # 记录只出现一次的值和相应索引(value-key)
            head.add(onetimekv[0])
        beforeindex = onetimekv[1]
        ans = adjacentPairs[beforeindex]
        if ans[0] not in head:
            temp = ans[0]
            ans[0] = ans[1]
            ans[1] = temp
        beforevalue = ans[-1]
        head.remove(ans[0])
        while len(ans) < len(adjacentPairs):    # 查找下一个连接的pair
            beforeindex = indexmap.pop((beforevalue, beforeindex))
            if beforevalue == adjacentPairs[beforeindex][0]:
                beforevalue = adjacentPairs[beforeindex][1]
                ans.append(beforevalue)
            else:
                beforevalue = adjacentPairs[beforeindex][0]
                ans.append(beforevalue)
        ans.append(head.pop())
        return ans

