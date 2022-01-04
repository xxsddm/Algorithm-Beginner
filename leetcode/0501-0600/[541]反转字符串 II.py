# 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。 
# 
#  
#  如果剩余字符少于 k 个，则将剩余字符全部反转。 
#  如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "abcdefg", k = 2
# 输出："bacdfeg"
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "abcd", k = 2
# 输出："bacd"
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 104 
#  s 仅由小写英文组成 
#  1 <= k <= 104 
#  
#  Related Topics 双指针 字符串 
#  👍 138 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        length1 = len(s)
        start = 0
        container = ''
        while start + 2 * k - 1 < length1:
            for i in range(k):
                container += s[start + k - 1 - i]
            container += s[start + k: start + 2 * k]
            start += 2 * k
        length2 = len(container)
        if length1 - length2 > k:
            for i in range(k):
                container += s[start + k - 1 - i]
            container += s[start + k: start + length1 - length2]
        else:
            for i in range(length1 - length2):
                container += s[start + length1 - length2 - 1 - i]
        return container

# leetcode submit region end(Prohibit modification and deletion)
