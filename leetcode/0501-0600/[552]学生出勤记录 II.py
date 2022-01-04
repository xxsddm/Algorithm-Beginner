# 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
#  
#  'A'：Absent，缺勤 
#  'L'：Late，迟到 
#  'P'：Present，到场 
#  
# 
#  如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励： 
# 
#  
#  按 总出勤 计，学生缺勤（'A'）严格 少于两天。 
#  学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。 
#  
# 
#  给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 10⁹ + 7 
# 取余 的结果。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 2
# 输出：8
# 解释：
# 有 8 种长度为 2 的记录将被视为可奖励：
# "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" 
# 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 1
# 输出：3
#  
# 
#  示例 3： 
# 
#  
# 输入：n = 10101
# 输出：183236316
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 10⁵ 
#  
#  Related Topics 动态规划 👍 148 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def checkRecord(self, n: int) -> int:
        container = [[0] * 2 for _ in range(3)]        # 当前连续迟到数, 缺课数
        mod = 10 ** 9 + 7
        container[0][0] = 1
        container[1][0] = 1
        container[0][1] = 1
        for i in range(n - 1):
            temp = [[0] * 2 for _ in range(3)]      # 一定由前一天更新后一天, 故数组天数维度可省略
            for j in range(3):
                for k in range(2):
                    if j <= 1:
                        temp[j + 1][k] = (temp[j + 1][k] + container[j][k]) % mod      # 若选择迟到
                    if k == 0:
                        temp[0][k + 1] = (temp[0][k + 1] + container[j][k]) % mod      # 若选择缺课
                    temp[0][k] = (temp[0][k] + container[j][k]) % mod                  # 若选择按时上课
            container = temp

        ans = 0
        for j in range(3):
            for k in range(2):
                ans = (ans + container[j][k]) % mod
        return ans

# leetcode submit region end(Prohibit modification and deletion)
