# 给你一个字符串 s，找到 s 中最长的回文子串。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "babad"
# 输出："bab"
# 解释："aba" 同样是符合题意的答案。
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "cbbd"
# 输出："bb"
#  
# 
#  示例 3： 
# 
#  
# 输入：s = "a"
# 输出："a"
#  
# 
#  示例 4： 
# 
#  
# 输入：s = "ac"
# 输出："a"
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 1000 
#  s 仅由数字和英文字母（大写和/或小写）组成 
#  
#  Related Topics 字符串 动态规划 
#  👍 3875 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def longestPalindrome(self, s: str) -> str:
        length, maxlength, maxstr = len(s), 1, s[0]
        for left in range(length):  # 左指针
            # 两种情况的右指针同时考虑, 不可if, elif判断哪种情况成立, 因为可能同时满足
            # 跳过不可能使子串比已有最长子串更长的情况
            if left - maxlength // 2 >= 0 and left + maxlength // 2 + 1 < length and \
                    s[left - maxlength // 2] == s[left + maxlength // 2 + 1] and \
                    left + 1 < length and s[left] == s[left + 1]:
                right = left + 2  # 右指针
                backleft = left - 1
                while backleft >= 0 and right < length and s[backleft] == s[right]:
                    backleft -= 1
                    right += 1

                if maxlength < right - backleft - 1:
                    maxlength = right - backleft - 1
                    maxstr = s[backleft + 1: right]

            if left - (maxlength + 1) // 2 + 1 >= 0 and left + (maxlength + 1) // 2 + 1 < length and \
                    s[left - (maxlength + 1) // 2 + 1] == s[left + (maxlength + 1) // 2 + 1] and \
                    left + 2 < length and s[left] == s[left + 2]:
                right = left + 3  # 右指针
                backleft = left - 1
                while backleft >= 0 and right < length and s[backleft] == s[right]:
                    backleft -= 1
                    right += 1

                if maxlength < right - backleft - 1:
                    maxlength = right - backleft - 1
                    maxstr = s[backleft + 1: right]

            if length - left - 1 < maxlength // 2:  # 提前终止循环
                break
        return maxstr

# leetcode submit region end(Prohibit modification and deletion)
