# 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
# 
#  
# 
#  示例: 
# 
#  输入: n = 10
# 输出: 12
# 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
# 
#  说明: 
# 
#  
#  1 是丑数。 
#  n 不超过1690。 
#  
# 
#  注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
#  Related Topics 哈希表 数学 动态规划 堆（优先队列） 
#  👍 201 👎 0


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
