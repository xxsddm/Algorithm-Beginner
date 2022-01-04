# 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
# 
#  回文串 是正着读和反着读都一样的字符串。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "aab"
# 输出：[["a","a","b"],["aa","b"]]
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "a"
# 输出：[["a"]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 16 
#  s 仅由小写英文字母组成 
#  
#  Related Topics 字符串 动态规划 回溯 👍 808 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def partition(self, s: str) -> List[List[str]]:     # 回溯+DP
        length = len(s)
        judge = [[False] * length for _ in range(length)]
        sublist = []
        container = []
        for i in range(length):
            judge[i][i] = True
        for start in range(length - 1, 0, -1):
            for end in range(start, length):
                judge[start - 1][end] = (start >= end - 1 or judge[start][end - 1]) \
                                        and s[start - 1] == s[end]

        def backtrack(start=0):
            if start == length:
                container.append(sublist[:])
            for end in range(start, length):
                if judge[start][end]:
                    sublist.append(s[start: end + 1])
                    backtrack(end + 1)
                    sublist.pop()

        backtrack()
        return container

# leetcode submit region end(Prohibit modification and deletion)
