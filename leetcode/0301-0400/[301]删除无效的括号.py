# 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 
# 
#  返回所有可能的结果。答案可以按 任意顺序 返回。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "()())()"
# 输出：["(())()","()()()"]
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "(a)())()"
# 输出：["(a())()","(a)()()"]
#  
# 
#  示例 3： 
# 
#  
# 输入：s = ")("
# 输出：[""]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 25 
#  s 由小写英文字母以及括号 '(' 和 ')' 组成 
#  s 中至多含 20 个括号 
#  
#  Related Topics 广度优先搜索 字符串 回溯 👍 542 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        self.target = s
        self.container = set()  # 哈希去重
        # 初始化可能的范围
        start, self.end = 0, len(s) - 1
        restLeft = restRight = 0
        countRight = 0
        ans = []
        while start < self.end and s[start] == ')':
            start += 1
        while self.end >= 0 and s[self.end] == '(':
            self.end -= 1
        for i in range(start, self.end + 1):
            if s[i] == ')':
                countRight += 1
        # 计算可能范围内多余的左(restLeft)右(restRight)括号数
        for i in range(start, self.end + 1):
            if s[i] == '(':
                restLeft += 1
            elif s[i] == ')':
                if restLeft > 0:
                    restLeft -= 1
                else:
                    restRight += 1
        self.backtrack(start, 0, 0, restLeft, restRight, countRight, [])
        for string in self.container:
            ans.append(string)
        return ans

    # idx:当前待匹配索引, numLeft:当前左括号数, numRight:当前右括号数
    # restLeft:剩余部分多余左括号数, restRight:剩余部分多余右括号数, countRight:剩余右括号数, cur:当前字符列表
    def backtrack(self, idx, numLeft, numRight, restLeft, restRight, countRight, cur):
        # 能够扫到末尾的一定可以加入
        if idx == self.end + 1:
            self.container.add("".join(cur))
            return
        temp = self.target[idx]
        if temp == '(':
            # 不加入左括号
            if restLeft > 0:
                self.backtrack(idx + 1, numLeft, numRight, restLeft - 1, restRight, countRight, cur)
            # 加入左括号
            if numLeft < numRight + countRight:
                cur.append(temp)
                self.backtrack(idx + 1, numLeft + 1, numRight, restLeft, restRight, countRight, cur)
                cur.pop()
        elif temp == ')':
            # 不加入右括号
            if restRight > 0:
                self.backtrack(idx + 1, numLeft, numRight, restLeft, restRight - 1, countRight - 1, cur)
            # 加入右括号
            if numLeft > numRight:
                cur.append(temp)
                self.backtrack(idx + 1, numLeft, numRight + 1, restLeft, restRight, countRight - 1, cur)
                cur.pop()
        else:
            cur.append(temp)
            self.backtrack(idx + 1, numLeft, numRight, restLeft, restRight, countRight, cur)
            cur.pop()

# leetcode submit region end(Prohibit modification and deletion)
