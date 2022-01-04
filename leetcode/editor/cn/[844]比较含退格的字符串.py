# 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。 
# 
#  注意：如果对空文本输入退格字符，文本继续为空。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：S = "ab#c", T = "ad#c"
# 输出：true
# 解释：S 和 T 都会变成 “ac”。
#  
# 
#  示例 2： 
# 
#  
# 输入：S = "ab##", T = "c#d#"
# 输出：true
# 解释：S 和 T 都会变成 “”。
#  
# 
#  示例 3： 
# 
#  
# 输入：S = "a##c", T = "#a#c"
# 输出：true
# 解释：S 和 T 都会变成 “c”。
#  
# 
#  示例 4： 
# 
#  
# 输入：S = "a#c", T = "b"
# 输出：false
# 解释：S 会变成 “c”，但 T 仍然是 “b”。 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= S.length <= 200 
#  1 <= T.length <= 200 
#  S 和 T 只含有小写字母以及字符 '#'。 
#  
# 
#  
# 
#  进阶： 
# 
#  
#  你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
#  
# 
#  
#  Related Topics 栈 双指针 字符串 模拟 
#  👍 294 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        container1, container2 = [], []

        def fill(container, x):
            if x == '#':
                if container:
                    container.pop()
            else:
                container.append(x)

        for char in s:
            fill(container1, char)
        for char in t:
            fill(container2, char)

        return container1 == container2
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        i, j = len(s) - 1, len(t) - 1
        skips = skipt = 0

        while i >= 0 or j >= 0:
            while i >= 0:
                if s[i] == "#":
                    skips += 1
                    i -= 1
                elif skips > 0:
                    skips -= 1
                    i -= 1
                else:
                    break
            while j >= 0:
                if t[j] == "#":
                    skipt += 1
                    j -= 1
                elif skipt > 0:
                    skipt -= 1
                    j -= 1
                else:
                    break
            if i >= 0 and j >= 0:
                if s[i] != t[j]:
                    return False
            elif i >= 0 or j >= 0:
                return False
            i -= 1
            j -= 1

        return True
