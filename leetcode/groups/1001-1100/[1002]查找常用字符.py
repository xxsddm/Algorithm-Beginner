# 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不
# 是 4 次，则需要在最终答案中包含该字符 3 次。 
# 
#  你可以按任意顺序返回答案。 
# 
#  
# 
#  示例 1： 
# 
#  输入：["bella","label","roller"]
# 输出：["e","l","l"]
#  
# 
#  示例 2： 
# 
#  输入：["cool","lock","cook"]
# 输出：["c","o"]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= A.length <= 100 
#  1 <= A[i].length <= 100 
#  A[i][j] 是小写字母 
#  
#  Related Topics 数组 哈希表 字符串 
#  👍 230 👎 0

import math

# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        container, ans = [math.inf] * 26, []

        for word in words:
            temp = [0] * 26
            for char in word:
                temp[ord(char) - 97] += 1
            for i in range(26):
                container[i] = min(container[i], temp[i])

        for i in range(26):
            num = container[i]
            while num > 0:
                ans.append(chr(i + 97))
                num -= 1

        return ans
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        charset = set(words[0])
        container = [{} for _ in range(len(words))]
        ans = []
        for i in range(1, len(words)):
            charset = charset.intersection(set(words[i]))
        for i in range(len(words)):
            word = words[i]
            subcontainer = container[i]
            for char in word:
                if char in subcontainer:
                    subcontainer[char] += 1
                elif char in charset:
                    subcontainer[char] = 1
        for char in charset:
            minnum = math.inf
            for subcontainer in container:
                minnum = min(minnum, subcontainer[char])
            while minnum > 0:
                ans.append(char)
                minnum -= 1
        return ans