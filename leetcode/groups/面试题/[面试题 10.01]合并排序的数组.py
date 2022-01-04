# 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。 
# 
#  初始化 A 和 B 的元素数量分别为 m 和 n。 
# 
#  示例: 
# 
#  输入:
# A = [1,2,3,0,0,0], m = 3
# B = [2,5,6],       n = 3
# 
# 输出: [1,2,2,3,5,6] 
# 
#  说明: 
# 
#  
#  A.length == n + m 
#  
#  Related Topics 数组 双指针 排序 
#  👍 111 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        """
        Do not return anything, modify A in-place instead.
        """
        loc1, loc2 = m - 1, n - 1    # A, B从右往左填入, loc1: A元素索引, loc2: B元素索引
        for i in range(m + n - 1, -1, -1):
            if loc1 >= 0 and loc2 >= 0:
                if A[loc1] > B[loc2]:
                    A[i] = A[loc1]
                    loc1 -= 1
                else:
                    A[i] = B[loc2]
                    loc2 -= 1
            elif loc2 < 0:
                A[i] = A[loc1]
                loc1 -= 1
            else:
                A[i] = B[loc2]
                loc2 -= 1

# leetcode submit region end(Prohibit modification and deletion)
