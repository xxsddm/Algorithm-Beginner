# 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：x = 2.00000, n = 10
# 输出：1024.00000
#  
# 
#  示例 2： 
# 
#  
# 输入：x = 2.10000, n = 3
# 输出：9.26100
#  
# 
#  示例 3： 
# 
#  
# 输入：x = 2.00000, n = -2
# 输出：0.25000
# 解释：2-2 = 1/22 = 1/4 = 0.25
#  
# 
#  
# 
#  提示： 
# 
#  
#  -100.0 < x < 100.0 
#  -231 <= n <= 231-1 
#  -104 <= xn <= 104 
#  
#  Related Topics 递归 数学 
#  👍 697 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    """
    举个例子, n = 10, 即二进制中: n = (1010), 则二进制中结果为 x^(1010) = x^(1000) * x^(10),
    其中 x^(10) = x^(1) * x^(1), x^(1000) = x^(100) * x^(100), 即平方运算使 幂<<=1.
    所以遇到二进制1就要累乘, 即 ans *= temp; 遇到二进制0就要继续使当前值取平方, 即 temp *= temp.
    """
    def myPow(self, x: float, n: int) -> float:
        if n < 0:
            count = - n
            temp = 1 / x
        else:
            count = n
            temp = x
        ans = 1
        while count > 0:
            if count & 1:
                ans *= temp
            temp *= temp
            count >>= 1
        return ans
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def myPow(self, x: float, n: int) -> float:
        return x ** n   # 真香
