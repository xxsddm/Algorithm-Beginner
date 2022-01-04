# 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
# 
#  
# 
#  示例 1: 
# 
#  
# 输入: s = "abcabcbb"
# 输出: 3 
# 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
#  
# 
#  示例 2: 
# 
#  
# 输入: s = "bbbbb"
# 输出: 1
# 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
#  
# 
#  示例 3: 
# 
#  
# 输入: s = "pwwkew"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
#      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
#  
# 
#  示例 4: 
# 
#  
# 输入: s = ""
# 输出: 0
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= s.length <= 5 * 104 
#  s 由英文字母、数字、符号和空格组成 
#  
#  Related Topics 哈希表 字符串 滑动窗口 
#  👍 5757 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        container = set()
        fast, length, lens = 0, 0, len(s)
        for slow in range(lens):    # 移动左指针, 缩小子字符串范围
            if length + slow >= lens:   # 不可能出现更长子字符串则结束
                break
            if slow != 0:   # 每次移动左指针, 删除左指针左侧元素
                container.remove(s[slow - 1])
            while fast < lens and s[fast] not in container:
                container.add(s[fast])
                fast += 1
            length = max(length, fast - slow)  # 出现重复字符或右指针走到底时计算长度
        return length
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        slow, fast, length = 0, 0, 0    # 快慢指针
        container = {}
        while fast < len(s):
            if s[fast] in container:
                slow = container[s[fast]] + 1
                length = max(length, len(container))
                container.clear()
                for index in range(slow, fast):
                    container[s[index]] = index
            container[s[fast]] = fast
            fast += 1
        return max(length, len(container))


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        slow, fast, length, slength = 0, 0, 0, len(s)   # 快慢指针
        container = {}
        while fast < slength:
            if s[fast] in container:
                slow = container[s[fast]] + 1
                length = max(length, len(container))
                value = list(container.values())
                index = slow - value[0]
                value = value[index:]
                container = dict(zip(list(container.keys())[index:], value))
            container[s[fast]] = fast
            fast += 1
        return max(length, len(container))
