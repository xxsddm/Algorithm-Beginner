# 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
# 
#  
# 
#  注意： 
# 
#  
#  对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
#  如果 s 中存在这样的子串，我们保证它是唯一的答案。 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "ADOBECODEBANC", t = "ABC"
# 输出："BANC"
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "a", t = "a"
# 输出："a"
#  
# 
#  示例 3: 
# 
#  
# 输入: s = "a", t = "aa"
# 输出: ""
# 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
# 因此没有符合条件的子字符串，返回空字符串。 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length, t.length <= 105 
#  s 和 t 由英文字母组成 
#  
# 
#  
# 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
#  👍 1268 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        if len(s) < len(t):
            return ""
        container = {}
        for i in t:
            if i in container:
                container[i] += 1
            else:
                container[i] = 1
        slow = fast = 0  # 双指针
        tempslow = tempfast = 0  # 记录已有最小子串首尾位置(含)
        minlength = len(s) + 1
        charlack = len(container)   # 还缺少的t中字符种类(将t组成的container视为某元素还缺少的数量)
        while fast < len(s):
            temp = s[fast]
            if temp in container:
                container[temp] -= 1  # 右指针移动加入元素
                if container[temp] == 0:  # 更新缺少的数量后变为0, 则缺少的字符种类减少
                    charlack -= 1
            while charlack == 0:   # 不缺少元素即可作为覆盖子串
                if minlength > fast - slow + 1:
                    minlength = fast - slow + 1
                    tempslow = slow
                    tempfast = fast
                temp = s[slow]
                if temp in container:
                    container[temp] += 1
                    if container[temp] == 1:  # 更新缺少的数量后变为1, 则缺少的字符种类增加
                        charlack += 1
                slow += 1  # 继续减少元素
            fast += 1
        return s[tempslow: tempfast + 1] if minlength != len(s) + 1 else ""

# leetcode submit region end(Prohibit modification and deletion)
