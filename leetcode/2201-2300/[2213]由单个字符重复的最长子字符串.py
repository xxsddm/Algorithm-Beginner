# 给你一个下标从 0 开始的字符串 s 。另给你一个下标从 0 开始、长度为 k 的字符串 queryCharacters ，一个下标从 0 开始、长度也是 
# k 的整数 下标 数组 queryIndices ，这两个都用来描述 k 个查询。 
# 
#  第 i 个查询会将 s 中位于下标 queryIndices[i] 的字符更新为 queryCharacters[i] 。 
# 
#  返回一个长度为 k 的数组 lengths ，其中 lengths[i] 是在执行第 i 个查询 之后 s 中仅由 单个字符重复 组成的 最长子字符串 的
#  长度 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
# 输出：[3,3,4]
# 解释：
# - 第 1 次查询更新后 s = "bbbacc" 。由单个字符重复组成的最长子字符串是 "bbb" ，长度为 3 。
# - 第 2 次查询更新后 s = "bbbccc" 。由单个字符重复组成的最长子字符串是 "bbb" 或 "ccc"，长度为 3 。
# - 第 3 次查询更新后 s = "bbbbcc" 。由单个字符重复组成的最长子字符串是 "bbbb" ，长度为 4 。
# 因此，返回 [3,3,4] 。 
# 
#  示例 2： 
# 
#  
# 输入：s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
# 输出：[2,3]
# 解释：
# - 第 1 次查询更新后 s = "abazz" 。由单个字符重复组成的最长子字符串是 "zz" ，长度为 2 。
# - 第 2 次查询更新后 s = "aaazz" 。由单个字符重复组成的最长子字符串是 "aaa" ，长度为 3 。
# 因此，返回 [2,3] 。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 10⁵ 
#  s 由小写英文字母组成 
#  k == queryCharacters.length == queryIndices.length 
#  1 <= k <= 10⁵ 
#  queryCharacters 由小写英文字母组成 
#  0 <= queryIndices[i] < s.length 
#  
#  Related Topics 线段树 数组 字符串 有序集合 👍 13 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def longestRepeating(self, s: str, queryCharacters: str, queryIndices: List[int]) -> List[int]:
        length = len(s)
        self.word = list(s)
        self.maxnum = [0] * (length << 2)
        self.left = [0] * (length << 2)
        self.right = [0] * (length << 2)
        self.build(1, 1, length)
        ans = [0] * len(queryCharacters)
        for i in range(len(queryCharacters)):
            self.change(1, 1, length, queryIndices[i] + 1, queryCharacters[i])
            ans[i] = self.maxnum[1]
        return ans

    def merge(self, node, start, end):
        mid = (start + end) >> 1
        nextNode1 = node << 1
        nextNode2 = nextNode1 ^ 1
        self.left[node], self.right[node] = self.left[nextNode1], self.right[nextNode2]
        self.maxnum[node] = max(self.maxnum[nextNode1], self.maxnum[nextNode2])
        if self.word[mid - 1] == self.word[mid]:
            self.maxnum[node] = max(self.maxnum[node], self.right[nextNode1] + self.left[nextNode2])
            if self.maxnum[nextNode1] == mid - start + 1:
                self.left[node] += self.left[nextNode2]
            if self.maxnum[nextNode2] == end - mid:
                self.right[node] += self.right[nextNode1]

    def build(self, node, start, end):
        if start == end:
            self.maxnum[node] = self.left[node] = self.right[node] = 1
            return
        mid = (start + end) >> 1
        nextNode1 = node << 1
        nextNode2 = nextNode1 ^ 1
        self.build(nextNode1, start, mid)
        self.build(nextNode2, mid + 1, end)
        self.merge(node, start, end)

    def change(self, node, start, end, idx, letter):
        if idx < start or idx > end:
            return
        if start == end:
            self.word[idx - 1] = letter
            return
        mid = (start + end) >> 1
        nextNode1 = node << 1
        nextNode2 = nextNode1 ^ 1
        self.change(nextNode1, start, mid, idx, letter)
        self.change(nextNode2, mid + 1, end, idx, letter)
        self.merge(node, start, end)

# leetcode submit region end(Prohibit modification and deletion)
