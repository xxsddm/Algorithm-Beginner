# 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
# 
#  
# 
#  示例 1： 
# 
#  输入："hello"
# 输出："holle"
#  
# 
#  示例 2： 
# 
#  输入："leetcode"
# 输出："leotcede" 
# 
#  
# 
#  提示： 
# 
#  
#  元音字母不包含字母 "y" 。 
#  
#  Related Topics 双指针 字符串 👍 168 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def reverseVowels(self, s: str) -> str:
        if len(s) <= 1:
            return s
        container = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
        charlist = list(s)
        left, right = 0, len(s) - 1
        while left < right:
            if charlist[left] in container and charlist[right] in container:
                charlist[left], charlist[right] = charlist[right], charlist[left]
                left += 1
                right -= 1
                continue
            if charlist[left] not in container:
                left += 1
            if charlist[right] not in container:
                right -= 1
        return ''.join(charlist)

# leetcode submit region end(Prohibit modification and deletion)
