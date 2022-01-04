# 给你一个整数 n ，请你找出并返回第 n 个 丑数 。 
# 
#  丑数 就是只包含质因数 2、3 和/或 5 的正整数。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 10
# 输出：12
# 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 1
# 输出：1
# 解释：1 通常被视为丑数。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 1690 
#  
#  Related Topics 哈希表 数学 动态规划 堆（优先队列） 
#  👍 713 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        container = [1] * n
        primes = [2, 3, 5]
        idx = [0] * 3    # 各元素指针位置, 把列表拆开会更快, 节省切片时间
        for i in range(1, n):
            temp = min(container[idx[i]] * primes[i] for i in range(3))
            for j in range(3):
                if temp == container[idx[j]] * primes[j]:
                    idx[j] += 1
            container[i] = temp
        return container[-1]

# leetcode submit region end(Prohibit modification and deletion)
