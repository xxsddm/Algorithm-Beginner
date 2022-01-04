# 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
# 
#  返回被除数 dividend 除以除数 divisor 得到的商。 
# 
#  整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
# 
#  
# 
#  示例 1: 
# 
#  输入: dividend = 10, divisor = 3
# 输出: 3
# 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
# 
#  示例 2: 
# 
#  输入: dividend = 7, divisor = -3
# 输出: -2
# 解释: 7/-3 = truncate(-2.33333..) = -2 
# 
#  
# 
#  提示： 
# 
#  
#  被除数和除数均为 32 位有符号整数。 
#  除数不为 0。 
#  假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
#  
#  Related Topics 位运算 数学 
#  👍 628 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def divide(self, a: int, b: int) -> int:    # python不用考虑溢出, 用绝对值  |...|  做除法再判断即可
        if (a > 0 and b < 0) or (a < 0 and b > 0):
            negative = True
        else:
            negative = False
        count = 31
        maxpositive = (1 << 31) - 1
        temp1, temp2 = abs(a), abs(b)
        temptemp2 = temp2 << count    # 除数依此乘以较高2的幂, 计算 |被除数| 最多能不小于多少个 |除数|
        ans = 0
        while temp1 >= temp2:    # 还可以添加 |除数|
            while temp1 < temptemp2:
                count -= 1
                temptemp2 >>= 1
            temp1 -= temptemp2
            ans += 1 << count    # 1 << count 即本轮添加 |除数| 的数量
        if ans > maxpositive and not negative:
            ans = maxpositive
        elif ans > maxpositive + 1 and negative:
            ans = maxpositive + 1
        ans = - ans if negative else ans
        return ans

# leetcode submit region end(Prohibit modification and deletion)
